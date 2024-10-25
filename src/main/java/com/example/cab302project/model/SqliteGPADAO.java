package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for managing GPA data access using SQLite.
 * This class provides methods to create a GPA table, save GPAs,
 * and retrieve GPA records from the database.
 */
public class SqliteGPADAO implements IGPADAO {
    private Connection connection; // Database connection instance

    /**
     * Constructor that initializes the database connection
     * and creates the GPA table if it does not already exist.
     */
    public SqliteGPADAO() {
        this.connection = SqliteConnection.getInstance(); // Get the database connection
        createTable(); // Create the GPA table
    }

    /**
     * Creates the GPA table in the database if it does not exist.
     * The table structure includes an id, username, and GPA.
     */
    private void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS gpa ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username VARCHAR UNIQUE NOT NULL, "
                + "gpa REAL NOT NULL)";
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate(); // Execute the table creation query
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs
        }
    }

    /**
     * Saves or updates the GPA record for a user in the database.
     *
     * @param gpa The GPA object containing the username and GPA value to be saved.
     */
    public void saveGPA(GPA gpa) {
        System.out.println("DS");
        String insertQuery = "INSERT INTO gpa (username, gpa) VALUES (?, ?) "
                + "ON CONFLICT(username) DO UPDATE SET gpa = excluded.gpa";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, gpa.getUsername()); // Set username parameter
            statement.setDouble(2, gpa.getGpa()); // Set GPA parameter
            statement.executeUpdate(); // Execute the insert/update query
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs
        }
    }

    /**
     * Retrieves the GPA for a specific user from the database.
     *
     * @param username The username of the user whose GPA is to be retrieved.
     * @return The GPA of the user, or -1 if the user is not found.
     */
    public double getGPA(String username) {
        String selectQuery = "SELECT gpa FROM gpa WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username); // Set username parameter
            ResultSet resultSet = statement.executeQuery(); // Execute the select query
            if (resultSet.next()) {
                return resultSet.getDouble("gpa"); // Return the GPA if found
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace if an error occurs
        }
        return -1; // Return -1 if GPA is not found
    }
}