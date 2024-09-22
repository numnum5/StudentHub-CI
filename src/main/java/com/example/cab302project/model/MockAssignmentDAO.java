package com.example.cab302project.model;

import com.example.cab302project.controller.LoginController;

import java.util.ArrayList;
import java.util.List;

/**
 * MockAssignmentDAO simulates a data access object for managing Assignment objects.
 * Provides methods for adding, updating, deleting, and searching assignments
 */
public class MockAssignmentDAO implements IAssignmentDAO{

    // Static list to hold mock Assignment data
    public final ArrayList<Assignment> assignments = new ArrayList<>();
    private int autoIncrementedId = 0; // ID counter for keeping and assigning new id to assignment object

    /**
     * Searches for assignments that contain the specified keyword in their name.
     *
     * @param keyword The keyword to search for in assignment names.
     * @return A list of assignments that match the search criteria.
     */
    @Override
    public List<Assignment> searchAssignments(String keyword) {
        List<Assignment> matchingAssignments = new ArrayList<>();
        // Return an empty list if the keyword is null or empty
        if (keyword == null || keyword.trim().isEmpty()) {
            return matchingAssignments;
        }

        // Iterate through all assignments and check for the keyword
        for (Assignment assignment : getAllAssignments(LoginController.username)) {
            if (assignment.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingAssignments.add(assignment);
            }
        }
        return matchingAssignments;
    }

    /**
     * Adds a new assignment to the mock data and sets the id
     *
     * @param assignment The assignment to be added.
     */
    @Override
    public void addAssignment(Assignment assignment) {
        assignment.setId(autoIncrementedId);
        autoIncrementedId++;
        assignments.add(assignment);
    }

    /**
     * Updates an existing assignment in the mock data.
     *
     * @param assignment The assignment object containing updated data.
     */
    @Override
    public void updateAssignment(Assignment assignment) {
        // Find the assignment by ID and update it
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId() == assignment.getId()) {
                assignments.set(i, assignment);
                break;
            }
        }
    }

    /**
     * Deletes an assignment from the mock data.
     *
     * @param assignment The assignment to be deleted.
     */
    @Override
    public void deleteAssignment(Assignment assignment) {
        assignments.remove(assignment); // Remove the assignment from the list
    }

    /**
     * Retrieves a specific assignment object by its ID.
     *
     * @param id The ID of the assignment to retrieve.
     * @return The assignment with the specified ID, or null if not found.
     */
    @Override
    public Assignment getAssignment(int id) {
        for (Assignment assignment : assignments) {
            if (assignment.getId() == id) {
                return assignment;
            }
        }
        return null;
    }

    /**
     * Returns a list of all assignments in the mock data.
     *
     * @return A list containing all assignments.
     */
    @Override
    public List<Assignment> getAllAssignments(String username) {
        return new ArrayList<>(assignments);
    }
}