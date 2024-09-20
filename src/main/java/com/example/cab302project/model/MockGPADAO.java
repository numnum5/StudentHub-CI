package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * MockGPADAO for simulating a Data Access Object (DAO) for GPA records.
 */
public class MockGPADAO implements IGPADAO {
    private final ArrayList<GPA> gpaList; // List to store GPA records
    private int autoIncrementedId; // Auto-incremented ID for new GPAs

    public MockGPADAO() {
        gpaList = new ArrayList<>();
        autoIncrementedId = 1;
    }

    /**
     * Saves or updates a GPA record.
     *
     * @param gpa The GPA object to be saved.
     */
    @Override
    public void saveGPA(GPA gpa) {
        // Check if the GPA already exists and update it
        for (GPA existingGPA : gpaList) {
            if (existingGPA.getUsername().equals(gpa.getUsername())) {
                existingGPA.setGpa(gpa.getGpa());
                return;
            }
        }
        // If it doesn't exist, assign a new ID and add to the list
        gpa.setUsername(gpa.getUsername());
        gpaList.add(gpa);
    }

    /**
     * Retrieves a GPA record by username.
     *
     * @param username The username of the student.
     * @return The GPA value, or -1 if not found.
     */
    @Override
    public double getGPA(String username) {
        for (GPA gpa : gpaList) {
            if (gpa.getUsername().equals(username)) {
                return gpa.getGpa();
            }
        }
        return -1;
    }
}
