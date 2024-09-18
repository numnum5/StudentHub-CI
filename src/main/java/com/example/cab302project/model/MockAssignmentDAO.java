package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

public class MockAssignmentDAO {

    public static final ArrayList<Assignment> subjects = new ArrayList<>();
    private static int autoIncrementedId = 0;

    // Method for adding a user

    public Assignment searchAssignment(String name){
        for(Assignment assignment : subjects){
            if(assignment.getName().contains(name)){
                return assignment;
            }
        }
        return null;
    }

    public void addUser(Assignment subject) {
        subject.setId(autoIncrementedId);
        autoIncrementedId++;
        subjects.add(subject);
    }

    // Method for updating user
    public void updateUser(Assignment subject) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == subject.getId()) {
                subjects.set(i, subject);
                break;
            }
        }
    }

    // Method for deleting a user
    public void deleteUser(Assignment subject) {
        subjects.remove(subject);
    }

    // A method for checking current user's password with the given password
    public Assignment getSubject(int id) {
        for (Assignment subject : subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null;
    }


    public List<Assignment> getAllUsers() {
        return new ArrayList<>(subjects);
    }
}
