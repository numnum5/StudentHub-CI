package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;

/**
 * A class for creating an SQL table and storing the data in it.
 */
public class SqliteFinanceDAO {
    private Connection connection;


    public SqliteFinanceDAO()
    {
        this.connection = SqliteConnection.getInstance();
        createFinanceTable();
    }

    /**
     * A method that creates that SQL table.
     */
    public void createFinanceTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS finance (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "budget INTEGER NOT NULL, " +
                    "amountSpentMon INTEGER NULL, " +
                    "amountSpentTue INTEGER NULL, " +
                    "amountSpentWed INTEGER NULL, " +
                    "amountSpentThu INTEGER NULL, " +
                    "amountSpentFri INTEGER NULL, " +
                    "amountSpentSat INTEGER NULL, " +
                    "amountSpentSun INTEGER NULL );";
            statement.execute(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * A method that stores the user's information into the created SQL table.
     * @param info The user's data received from the MockFinanceDAO class.
     */
    public void addInfo(HashMap<String, Float> info){
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO finance (budget, amountSpentMon, amountSpentTue, amountSpentWed, amountSpentThu, " +
                        "amountSpentFri, amountSpentSat, amountSpentSun) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, String.valueOf(info.get("budget")));
            statement.setString(2, String.valueOf(info.get("amountSpentMon")));
            statement.setString(3, String.valueOf(info.get("amountSpentTue")));
            statement.setString(4, String.valueOf(info.get("amountSpentWed")));
            statement.setString(5, String.valueOf(info.get("amountSpentThu")));
            statement.setString(6, String.valueOf(info.get("amountSpentFri")));
            statement.setString(7, String.valueOf(info.get("amountSpentSat")));
            statement.setString(8, String.valueOf(info.get("amountSpentSun")));
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
