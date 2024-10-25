package com.example.cab302project.model;

import com.example.cab302project.controller.LoginController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for managing subject data access operations.
 * This class provides methods to add, update, delete, and retrieve subjects,
 * as well as to search for subjects based on a keyword.
 */
public class SubjectManager {
    private ISubjectDAO subjectDAO; // Data access object for subjects

    /**
     * Constructor that initializes the SubjectManager with the given subject DAO.
     *
     * @param subjectDAO An implementation of ISubjectDAO for data access operations.
     */
    public SubjectManager(ISubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO; // Set the subject DAO
    }

    /**
     * Adds a new subject to the data source.
     *
     * @param subject The Subject object to be added.
     */
    public void addSubject(Subject subject) {
        subjectDAO.addSubject(subject); // Delegate to the DAO
    }

    /**
     * Updates an existing subject in the data source.
     *
     * @param subject The Subject object containing updated information.
     */
    public void updateSubject(Subject subject) {
        subjectDAO.updateSubject(subject); // Delegate to the DAO
    }

    /**
     * Deletes a subject from the data source.
     *
     * @param subject The Subject object to be deleted.
     */
    public void deleteSubject(Subject subject) {
        subjectDAO.deleteSubject(subject); // Delegate to the DAO
    }

    /**
     * Retrieves a subject by its unique identifier.
     *
     * @param id The unique identifier of the subject to be retrieved.
     * @return The Subject object with the specified id, or null if not found.
     */
    public Subject getSubject(int id) {
        return subjectDAO.getSubject(id); // Delegate to the DAO
    }

    /**
     * Retrieves all subjects from the data source.
     *
     * @return A list of all Subject objects.
     */
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects(); // Delegate to the DAO
    }

    /**
     * Searches for subjects that match the given keyword.
     *
     * @param keyword The keyword to search for in subject names and unit codes.
     * @return A list of Subject objects that contain the keyword in their name or unit code.
     */
    public List<Subject> searchSubjects(String keyword) {
        List<Subject> matchingSubjects = new ArrayList<>(); // List to hold matching subjects

        // Return an empty list if the keyword is null or empty
        if (keyword == null || keyword.trim().isEmpty()) {
            return subjectDAO.getAllSubjects(); // Return all subjects if keyword is empty
        }

        // Iterate through all subjects and check for the keyword
        for (Subject subject : subjectDAO.getAllSubjects()) {
            if (subject.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    subject.getUnitCode().toLowerCase().contains(keyword.toLowerCase())) {
                matchingSubjects.add(subject); // Add matching subject to the list
            }
        }
        return matchingSubjects; // Return the list of matching subjects
    }
}
