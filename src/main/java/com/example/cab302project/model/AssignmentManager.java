package com.example.cab302project.model;

import com.example.cab302project.controller.LoginController;

import java.util.ArrayList;
import java.util.List;

/**
 * AssignmentManager for managing functionalities in IAssignmentDAO
 */
public class AssignmentManager {
    private final IAssignmentDAO assignmentDAO;

    // Constructor that initializes the AssignmentManager with a DAO
    public AssignmentManager(IAssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    /**
     * Adds a new assignment.
     *
     * @param assignment The assignment to be added.
     */
    public void addAssignment(Assignment assignment) {
        assignmentDAO.addAssignment(assignment);
    }

    /**
     * Updates an existing assignment.
     *
     * @param assignment The assignment with updated data.
     */
    public void updateAssignment(Assignment assignment) {
        assignmentDAO.updateAssignment(assignment);
    }

    /**
     * Deletes an assignment.
     *
     * @param assignment The assignment to be deleted.
     */
    public void deleteAssignment(Assignment assignment) {
        assignmentDAO.deleteAssignment(assignment);
    }

    /**
     * Retrieves an assignment by its ID.
     *
     * @param id The ID of the assignment.
     * @return The assignment if found, or null.
     */
    public Assignment getAssignment(int id) {
        return assignmentDAO.getAssignment(id);
    }

    /**
     * Retrieves all assignments for a specific user.
     *
     * @param username The username of the user.
     * @return A list of assignments for the user.
     */
    public List<Assignment> getAllAssignments(String username) {
        return assignmentDAO.getAllAssignments(username);
    }

    /**
     * Searches assignments by a keyword in their names.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching assignments.
     */
    public List<Assignment> searchAssignments(String keyword) {
        List<Assignment> matchingAssignments = new ArrayList<>();
        // Return an empty list if the keyword is null or empty
        if (keyword == null || keyword.trim().isEmpty()) {
            return assignmentDAO.getAllAssignments(LoginController.username);
        }
        // Iterate through all assignments and check for the keyword
        // Check if the keyword is in the description, name, subject etc...
        for (Assignment assignment : getAllAssignments(LoginController.username)) {
            if (assignment.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    assignment.getDescription().toLowerCase().contains(keyword.toLowerCase())
            || assignment.getSubject().toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingAssignments.add(assignment);
            }
        }
        return matchingAssignments;
    }
}
