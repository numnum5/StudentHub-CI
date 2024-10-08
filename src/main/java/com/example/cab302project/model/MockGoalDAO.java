package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the GoalDAO to simulate database operations.
 * Stores goals in-memory rather than in a real database.
 */
public class MockGoalDAO {

    // In-memory list to simulate a database
    private List<String[]> mockDatabase;

    /**
     * Constructor initializes the mock database as an empty list.
     */
    public MockGoalDAO() {
        this.mockDatabase = new ArrayList<>();
    }

    /**
     * Saves a goal's title and details into the mock database.
     *
     * @param goalTitle   The title of the goal.
     * @param goalDetails The details of the goal.
     */
    public void saveGoal(String goalTitle, String goalDetails) {
        mockDatabase.add(new String[]{goalTitle, goalDetails});
    }

    /**
     * Retrieves all goals from the mock database as a list of String arrays.
     * Each array contains the goal title and details.
     *
     * @return A list of all goals in the mock database.
     */
    public List<String[]> getAllGoals() {
        return new ArrayList<>(mockDatabase); // Return a copy of the mock database
    }

    /**
     * Deletes a goal by its title from the mock database.
     *
     * @param goalTitle The title of the goal to be deleted.
     */
    public void deleteGoal(String goalTitle) {
        mockDatabase.removeIf(goal -> goal[0].equals(goalTitle));
    }

    /**
     * Searches for goals in the mock database that contain the keyword in the title or details.
     *
     * @param keyword The keyword to search for.
     * @return A list of goals that match the search criteria.
     */
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
