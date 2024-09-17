package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    private Connection connection;

    public JournalDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS journals ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "entry VARCHAR NOT NULL,"
                    + "mood VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEntry(String entry, String mood)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO journals (entry, mood) VALUES (?, ?)");
            statement.setString(1, entry);
            statement.setString(2, mood);
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
