package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

public class MockGoalDAO {
    // In-memory list to simulate database
    private List<String[]> mockDatabase;

    // Constructor initializes the mock database
    public MockGoalDAO() {
        this.mockDatabase = new ArrayList<>();
    }

    // Saves a goal's title and details into the mock database
    public void saveGoal(String goalTitle, String goalDetails) {
        mockDatabase.add(new String[]{goalTitle, goalDetails});
    }

    // Retrieves all goals from the mock database as a list of String arrays
    public List<String[]> getAllGoals() {
        return new ArrayList<>(mockDatabase);
    }

    // Deletes a goal by its title from the mock database
    public void deleteGoal(String goalTitle) {
        mockDatabase.removeIf(goal -> goal[0].equals(goalTitle));
    }

    // Searches for goals that contain the keyword in the title or details in the mock database
    public List<String[]> searchGoals(String keyword) {
        List<String[]> foundGoals = new ArrayList<>();
        for (String[] goal : mockDatabase) {
            if (goal[0].contains(keyword) || goal[1].contains(keyword)) {
                foundGoals.add(goal);
            }
        }
        return foundGoals;
    }
}
