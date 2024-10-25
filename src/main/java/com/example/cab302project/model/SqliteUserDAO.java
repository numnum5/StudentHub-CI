package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IUserDAO interface, managing database operations for user data using SQLite.
 * Provides methods to add, update, delete, and retrieve users, as well as check if user exists and validate passwords.
 */
public class SqliteUserDAO implements IUserDAO {

    /**
     * The database connection used to interact with the SQLite database.
     */
    private Connection connection;

    /**
     * Indicates whether the added user is unique (i.e., username is not already in the database).
     */
    public boolean userUnique;

    /**
     * Stores the username of the current user (optional, currently unused in class logic).
     */
    public String userName;

    /**
     * Constructor initializes the SQLite connection and ensures the "users" table exists.
     */
    public SqliteUserDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the "users" table in the SQLite database if it does not already exist.
     * The table contains fields for id, username, password, first name, and last name.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username VARCHAR NOT NULL UNIQUE,"
                    + "password VARCHAR NOT NULL,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new user to the "users" table in the database.
     * If the user is successfully added, the user's id is set.
     *
     * @param user The user to be added.
     */
    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (firstName, lastName, username, password) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername().toLowerCase());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();

            // Retrieve the generated ID and set it to the user
            ResultSet generatedKeys = statement.getGeneratedKeys();
            userUnique = true;
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (org.sqlite.SQLiteException e) {
            userUnique = false; // Set to false if a unique constraint violation occurs
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the details of an existing user in the "users" table.
     *
     * @param user The user with updated information.
     */
    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET firstName = ?, lastName = ?, username = ?, password = ? WHERE id = ?"
            );
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user from the "users" table by their id.
     *
     * @param user The user to be deleted.
     */
    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?"
            );
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user from the "users" table by their id.
     *
     * @param id The id of the user to retrieve.
     * @return The retrieved user, or null if not found.
     */
    @Override
    public User getUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(firstName, lastName, username, password);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the "users" table.
     *
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user = new User(firstName, lastName, username, password);
                user.setId(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Checks if a user with the given username exists in the database.
     *
     * @param userName The username to check.
     * @return True if the user exists, false otherwise.
     */
    @Override
    public boolean userExists(String userName) {
        try {
            String query = "SELECT * FROM users WHERE username = '" + userName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Validates the provided password for the given username.
     *
     * @param userName The username whose password needs to be validated.
     * @param password The password to validate.
     * @return True if the password matches, false otherwise.
     */
    @Override
    public boolean passwordCorrect(String userName, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = '" + userName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

