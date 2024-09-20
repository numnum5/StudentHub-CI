package com.example.cab302project.model;

/**
 * The GPA class represents a student's GPA record.
 */
public class GPA {
    private String username; // The username of the student
    private double gpa;      // The GPA value

    /**
     * Constructor to create a GPA object.
     *
     * @param username The username of the student.
     * @param gpa The GPA value.
     */
    public GPA(String username, double gpa) {
        this.username = username;
        this.gpa = gpa;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Override toString for easy representation
    @Override
    public String toString() {
        return "GPA [Username=" + username + ", GPA=" + gpa + "]";
    }
}
