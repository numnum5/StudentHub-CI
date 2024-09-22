package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoalDAO {
    private Connection connection;

    // Constructor initializes the database connection and creates the goals table if it doesn't exist
    public GoalDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    // Creates the goals table if not already created
    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS goals ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "goalTitle VARCHAR NOT NULL, "
                    + "goalDetails VARCHAR NOT NULL)";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Saves a goal's title and details into the database
    public void saveGoal(String goalTitle, String goalDetails) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO goals (goalTitle, goalDetails) VALUES (?, ?)")) {
            statement.setString(1, goalTitle);
            statement.setString(2, goalDetails);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieves all goals from the database as a list of String arrays
    public List<String[]> getAllGoals() {
        List<String[]> goals = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT goalTitle, goalDetails FROM goals");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                goals.add(new String[]{rs.getString("goalTitle"), rs.getString("goalDetails")});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goals;
    }

    // Deletes a goal by its title
    public void deleteGoal(String goalTitle) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM goals WHERE goalTitle = ?")) {
            statement.setString(1, goalTitle);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Searches for goals that contain the keyword in the title or details
    public List<String[]> searchGoals(String keyword) {
        List<String[]> goals = new ArrayList<>();
        String searchPattern = "%" + keyword + "%";
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT goalTitle, goalDetails FROM goals WHERE goalTitle LIKE ? OR goalDetails LIKE ?")) {
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    goals.add(new String[]{rs.getString("goalTitle"), rs.getString("goalDetails")});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goals;
    }
}
