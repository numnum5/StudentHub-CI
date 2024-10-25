package com.example.cab302project.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Singleton class for managing a connection to a SQLite database.
 * This class ensures that only one instance of the database connection
 * is created and reused throughout the application.
 */
public class SqliteConnection {

    private static Connection instance = null; // Singleton instance of the database connection

    /**
     * Private constructor to initialize the database connection.
     * This constructor is called only once when the connection is needed.
     */
    private SqliteConnection() {
        String url = "jdbc:sqlite:database.db"; // Database URL
        try {
            instance = DriverManager.getConnection(url); // Establish the connection
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx); // Print error message if connection fails
        }
    }

    /**
     * Method to get the singleton instance of the database connection.
     * If the instance does not exist, it creates a new instance.
     *
     * @return The connection instance to the SQLite database.
     */
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection(); // Create a new instance if it does not exist
        }
        return instance; // Return the existing connection instance
    }
}