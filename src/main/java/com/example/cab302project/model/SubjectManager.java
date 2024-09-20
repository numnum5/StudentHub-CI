package com.example.cab302project.model;

import com.example.cab302project.controller.LoginController;

import java.util.ArrayList;
import java.util.List;

// Class for managing subject data access object.
public class SubjectManager {
    private ISubjectDAO subjectDAO;

    public SubjectManager(ISubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public void addSubject(Subject subject) {
        subjectDAO.addSubject(subject);
    }

    public void updateSubject(Subject subject) {
        subjectDAO.updateSubject(subject);
    }

    public void deleteSubject(Subject subject) {
        subjectDAO.deleteSubject(subject);
    }

    public Subject getSubject(int id) {
        return subjectDAO.getSubject(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    // Search for subjects with given keyword
    public List<Subject> searchSubjects(String keyword) {
        List<Subject> matchingSubjects = new ArrayList<>();
        // Return an empty list if the keyword is null or empty
        if (keyword == null || keyword.trim().isEmpty()) {
            return subjectDAO.getAllSubjects();
        }
        // Iterate through all assignments and check for the keyword
        for (Subject subject : subjectDAO.getAllSubjects()) {
            if (subject.getName().toLowerCase().contains(keyword.toLowerCase())
                    || subject.getUnitCode().toLowerCase().contains(keyword.toLowerCase())) {
                matchingSubjects.add(subject);
            }
        }
        return matchingSubjects;
    }
}
