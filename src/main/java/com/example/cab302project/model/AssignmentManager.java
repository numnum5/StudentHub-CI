package com.example.cab302project.model;

import java.util.List;

/**
 * AssignmentManager for managing functionalities in IAssignmentDAO
 * utilizing the MockAssignmentDAO for data access.
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
        return assignmentDAO.searchAssignments(keyword);
    }
}
