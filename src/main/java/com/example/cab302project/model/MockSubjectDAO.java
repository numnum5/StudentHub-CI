package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The MockSubjectDAO class simulates a Data Access Object (DAO) for managing subjects.
 */
public class MockSubjectDAO implements ISubjectDAO {
    public final ArrayList<Subject> subjects = new ArrayList<>(); // List to store subjects
    private int autoIncrementedId = 0; // Auto-incremented ID for new subjects

    /**
     * Adds a new subject to the list and assigns it a unique ID.
     *
     * @param subject The Subject object to be added.
     */
    @Override
    public void addSubject(Subject subject) {
        subject.setId(autoIncrementedId);
        autoIncrementedId++;
        subjects.add(subject);
    }

    /**
     * Updates an existing subject in the list.
     *
     * @param subject The Subject object containing updated information.
     */
    @Override
    public void updateSubject(Subject subject) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == subject.getId()) {
                subjects.set(i, subject);
                break;
            }
        }
    }

    /**
     * Deletes a subject from the list.
     *
     * @param subject The Subject object to be deleted.
     */
    @Override
    public void deleteSubject(Subject subject) {
        subjects.remove(subject);
    }

    /**
     * Retrieves a subject by its ID.
     *
     * @param id The unique identifier of the subject to be retrieved.
     * @return The Subject object if found, or null if not.
     */
    @Override
    public Subject getSubject(int id) {
        for (Subject subject : subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }

    /**
     * Retrieves all subjects in the list.
     *
     * @return A list of all Subject objects.
     */
    @Override
    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects);
    }

    /**
     * Searches for subjects that match the given keyword in their name or unit code.
     *
     * @param keyword The keyword to search for in subject names or unit codes.
     * @return A list of matching Subject objects.
     */
    @Override
    public List<Subject> searchSubjects(String keyword) {
        List<Subject> matchingSubjects = new ArrayList<>();
        // Return an empty list if the keyword is null or empty
        if (keyword == null || keyword.trim().isEmpty()) {
            return matchingSubjects;
        }
        for (Subject subject : subjects) {
            if (subject.getName().toLowerCase().contains(keyword.toLowerCase()) || subject.getUnitCode().toLowerCase().contains(keyword.toLowerCase())) {
                matchingSubjects.add(subject);
            }
        }
        return matchingSubjects;
    }
}
