package com.example.cab302project.model;

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

    public List<Subject> searchSubjects(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllSubjects();
        }
        return subjectDAO.searchSubjects(query);
    }
}
