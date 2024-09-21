package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MockGoalDAO {
    private Connection connection;

    public MockGoalDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS goals ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "goalTitle VARCHAR NOT NULL, "
                    + "goalDetails VARCHAR NOT NULL)";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Save goalTitle and goalDetails to the database
    public void saveGoal(String goalTitle, String goalDetails) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO goals (goalTitle, goalDetails) VALUES (?, ?)"
            );
            statement.setString(1, goalTitle);
            statement.setString(2, goalDetails);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getAllGoals() {
        List<String[]> goals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT goalTitle, goalDetails FROM goals");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("goalTitle");
                String details = rs.getString("goalDetails");
                goals.add(new String[]{title, details}); // Add the goal as an array
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goals;
    }

    // Delete a goal by its title
    public void deleteGoal(String goalTitle) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM goals WHERE goalTitle = ?"
            );
            statement.setString(1, goalTitle);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search for goals that contain the keyword in the title or details
    public List<String[]> searchGoals(String keyword) {
        List<String[]> goals = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT goalTitle, goalDetails FROM goals WHERE goalTitle LIKE ? OR goalDetails LIKE ?"
            );
            // Use '%' wildcards to match any text before/after the keyword
            String searchPattern = "%" + keyword + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String title = rs.getString("goalTitle");
                String details = rs.getString("goalDetails");
                goals.add(new String[]{title, details});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goals;
    }

    // Update a goal's title and details by its old title
    public void updateGoal(String oldTitle, String newTitle, String newDetails) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE goals SET goalTitle = ?, goalDetails = ? WHERE goalTitle = ?"
            );
            statement.setString(1, newTitle);
            statement.setString(2, newDetails);
            statement.setString(3, oldTitle);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
