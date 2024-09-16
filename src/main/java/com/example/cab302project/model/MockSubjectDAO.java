package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

public class MockSubjectDAO {
    public static final ArrayList<Subject> subjects = new ArrayList<>();
    private static int autoIncrementedId = 0;

    // Method for adding a user

    public void addUser(Subject subject) {
        subject.setId(autoIncrementedId);
        autoIncrementedId++;
        subjects.add(subject);
    }

    // Method for updating user
    public void updateUser(Subject subject) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == subject.getId()) {
                subjects.set(i, subject);
                break;
            }
        }
    }

    // Method for deleting a user
    public void deleteUser(Subject subject) {
        subjects.remove(subject);
    }

    // A method for checking current user's password with the given password
    public Subject getSubject(int id) {
        for (Subject subject : subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }


    public List<Subject> getAllUsers() {
        return new ArrayList<>(subjects);
    }
}

