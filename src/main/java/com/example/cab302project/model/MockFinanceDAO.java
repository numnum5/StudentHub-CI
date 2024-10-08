package com.example.cab302project.model;
import java.util.ArrayList;
import java.util.HashMap;

/**
    * A class to act as a mock database
 */
public class MockFinanceDAO {

    private final float budget;
    public static final ArrayList<HashMap<String, Float>> mockData = new ArrayList<>();

    SqliteFinanceDAO sqliteFinanceDAO = new SqliteFinanceDAO();

    /**
     * A constructor that receives the data collected from the control class
     * @param budget The budget value entered with the user.
     * @param userData The users information from the daily spending.
     */
    public MockFinanceDAO(float budget, HashMap<String, Float> userData) {
        this.budget = budget;
        userData.put("budget", budget); // Stored in the HashMap
        mockData.add(userData); // Stores the HashMap to the ArrayList
        sqliteFinanceDAO.addInfo(userData);
    }
    public float getBudget() {return budget;}
}
