package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteGPADAO implements IGPADAO{
    private Connection connection;

    public SqliteGPADAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS gpa ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username VARCHAR UNIQUE NOT NULL, "
                + "gpa REAL NOT NULL)";
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGPA(GPA gpa) {
        System.out.println("DS");
        String insertQuery = "INSERT INTO gpa (username, gpa) VALUES (?, ?) "
                + "ON CONFLICT(username) DO UPDATE SET gpa = excluded.gpa";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, gpa.getUsername());
            statement.setDouble(2, gpa.getGpa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getGPA(String username) {
        String selectQuery = "SELECT gpa FROM gpa WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("gpa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if GPA is not found
    }

}
