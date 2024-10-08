package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing goals in the database.
 * Provides methods to save, retrieve, delete, and search goals.
 */
public class GoalDAO {

    private Connection connection;

    /**
     * Constructor initializes the database connection and creates the goals table if it doesn't exist.
     */
    public GoalDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the "goals" table in the database if it doesn't already exist.
     */
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

    /**
     * Saves a goal's title and details into the database.
     *
     * @param goalTitle   The title of the goal.
     * @param goalDetails The details of the goal.
     */
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

    /**
     * Retrieves all goals from the database as a list of String arrays, each containing
     * the goal title and details.
     *
     * @return A list of goals, where each goal is represented as a String array
     *         with [goalTitle, goalDetails].
     */
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

    /**
     * Deletes a goal by its title.
     *
     * @param goalTitle The title of the goal to be deleted.
     */
    public void deleteGoal(String goalTitle) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM goals WHERE goalTitle = ?")) {
            statement.setString(1, goalTitle);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for goals that contain the keyword in the title or details.
     *
     * @param keyword The search keyword to look for in the goal title or details.
     * @return A list of goals that match the search criteria, where each goal is
     *         represented as a String array with [goalTitle, goalDetails].
     */
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
