package com.example.cab302project.model;

import com.example.cab302project.controller.LoginController;

import java.util.ArrayList;
import java.util.List;

public class MockAssignmentDAO {

    public static final ArrayList<Assignment> subjects = new ArrayList<>();
    private static int autoIncrementedId = 0;


    public void populateData(){
        Subject testSubject = new Subject(1, "CAB502", "Advanced Quantum Computing", "Idk");
        Subject testSubject2 = new Subject(1, "CAB503", "Advanced Operating Systems", "Idk");
        Assignment assg1 = new Assignment("Assignment 1", "Develop AGI and solve Pnp problem", LoginController.currentUsername, testSubject, "24/11/2026");
        Assignment assg2 = new Assignment("Assignment 2", "Create next generation super computer", LoginController.currentUsername, testSubject, "25/11/2026");
        Assignment assg3 = new Assignment("Assignment 3", "Create next Windows 12", LoginController.currentUsername, testSubject2, "25/11/2026");
        addAssignment(assg1);
        addAssignment(assg2);
        addAssignment(assg3);
        for(Assignment assignment : subjects){
            System.out.println("R");
            System.out.println(assignment.toString());
        }
    }
    public List<Assignment> searchAssignments(String keyword) {
        List<Assignment> matchingAssignments = new ArrayList<>();  // Create a list to store matching assignments

        for (Assignment assignment : getAllAssignments()) {
            System.out.println(assignment.toString());
            if (assignment.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingAssignments.add(assignment);  // Add matching assignment to the list
            }
        }
        return matchingAssignments;  // Return the list of matching assignments
    }

    public void addAssignment(Assignment subject) {
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


    public List<Assignment> getAllAssignments() {
        return new ArrayList<>(subjects);
    }
}
