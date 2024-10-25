package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages database operations for journal entries using SQLite.
 * Creates a table if it doesn't exist and provides methods to add and fetch entries.
 */
public class SqliteJournalDAO {
    private Connection connection;

    /**
     * Initializes the database connection and sets up the journals table.
     */
    public SqliteJournalDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the 'journals' table if it doesn't exist already.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS journals ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "entry VARCHAR NOT NULL,"
                    + "mood VARCHAR NOT NULL,"
                    + "username VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new journal entry to the database.
     *
     *
     */
    public void addEntry(Journal journal) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO journals (entry, mood, username) VALUES (?, ?, ?)");
            statement.setString(1, journal.getEntry());
            statement.setString(2, journal.getMood());
            statement.setString(3, journal.getUsername());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches all entries for a given user.
     *
     * @param userName The username to fetch entries for (current logged in user).
     * @return A list of entries.
     */
    public List<String> getAllEntries(String userName) {
        List<String> entries = new ArrayList<>();
        String query = "SELECT entry, mood FROM journals WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String entry = resultSet.getString("entry");
                String mood = resultSet.getString("mood");
                entries.add("Mood: " + mood + "\nEntry: " + entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }
}
