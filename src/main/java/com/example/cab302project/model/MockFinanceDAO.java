package com.example.cab302project.model;
import java.util.ArrayList;
import java.util.HashMap;

// Class to act as a mock data base
public class MockFinanceDAO {

    private final float budget;
    public static final ArrayList<HashMap<String, Float>> mockData = new ArrayList<>();

    public MockFinanceDAO(float budget, HashMap<String, Float> userData)
    {
        this.budget = budget;
        userData.put("budget", budget); // Stored in the HashMap
        mockData.add(userData); // Stores the HashMap to the ArrayList

    }

    public float getBudget() {return budget;}
}
