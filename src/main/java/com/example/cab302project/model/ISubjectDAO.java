package com.example.cab302project.model;

import java.util.List;

/**
 * ISubjectDAO defines the methods for managing subjects in MockSubjectDAO.
 * This interface provides a contract for adding, updating, deleting, and retrieving subjects.
 */
public interface ISubjectDAO {

    /**
     * Adds a new subject to the data source.
     *
     * @param subject The Subject object to be added.
     */
    void addSubject(Subject subject);

    /**
     * Updates an existing subject in the data source.
     *
     * @param subject The Subject object containing updated information.
     */
    void updateSubject(Subject subject);

    /**
     * Deletes a subject from the data source.
     *
     * @param subject object of the Subject object to be deleted.
     */
    void deleteSubject(Subject subject);

    /**
     * Retrieves a subject by its ID.
     *
     * @param id The unique identifier of the subject to be retrieved.
     * @return The Subject object if found, or null if not.
     */
    Subject getSubject(int id);

    /**
     * Retrieves all subjects in the data source.
     *
     * @return A list of all Subject objects.
     */
    List<Subject> getAllSubjects();

    List<Subject> searchSubjects(String query);
}