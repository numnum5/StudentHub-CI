package com.example.cab302project.model;
import java.util.ArrayList;
import java.util.HashMap;

public class MockFinanceDAO {

    private final float budget;
    public static final ArrayList<HashMap<String, Float>> mockData = new ArrayList<>();

    public MockFinanceDAO(Float budget, HashMap<String, Float> userData)
    {
        this.budget = budget;
        // Stored in
        userData.put("budget", budget);
        mockData.add(userData);

    }

    public float getBudget() {return budget;}
}
