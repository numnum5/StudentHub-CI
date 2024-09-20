package com.example.cab302project.model;

import java.util.List;

/**
 * IAssignmentDAO for managing assignments in a data access object (DAO) or MockAssignmentDAO.
 * Provides a contract for adding, updating, deleting, and retrieving assignments.
 */
public interface IAssignmentDAO {

    /**
     * Adds a new assignment to the data source.
     *
     * @param assignment The Assignment object to be added.
     */
    void addAssignment(Assignment assignment);

    /**
     * Updates an existing assignment in the data source.
     *
     * @param assignment The Assignment object containing updated information.
     */
    void updateAssignment(Assignment assignment);

    /**
     * Deletes an assignment from the data source.
     *
     * @param assignment The Assignment object to be deleted.
     */
    void deleteAssignment(Assignment assignment);

    /**
     * Retrieves a specific assignment by its ID.
     *
     * @param id The unique identifier of the assignment to be retrieved.
     * @return The Assignment object if found, or null if not.
     */
    Assignment getAssignment(int id);

    /**
     * Retrieves all assignments in the data source.
     *
     * @return A list of all Assignment objects.
     */
    List<Assignment> getAllAssignments(String username);

    /**
     * Searches for assignments that contain the specified keyword in their name.
     *
     * @param keyword The keyword to search for in assignment names.
     * @return A list of assignments that match the search criteria.
     */
    List<Assignment> searchAssignments(String keyword);
}
