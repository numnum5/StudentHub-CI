package com.example.cab302project.model;

import java.time.LocalDate;

/**
 * Class for modeling an assignment item with necessary fields.
 */
public class Assignment {
    private int id; // Unique identifier for the assignment
    private String name; // Name of the assignment
    private String description; // Description of the assignment
    private float grade = 0; // Grade for the assignment, default is 0
    private String username; // Username of the student associated with the assignment
    private Subject subject; // Subject associated with the assignment
    private String dueDate; // Due date for the assignment
    private AssignmentStatus status; // Status of the assignment

    /**
     * Constructor to create an Assignment instance with specified details.
     *
     * @param name        The name of the assignment.
     * @param description A brief description of the assignment.
     * @param username    The username of the student who owns the assignment.
     * @param subject     The subject associated with the assignment.
     * @param dueDate     The due date for the assignment.
     */
    public Assignment(String name, String description, String username, Subject subject, String dueDate) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.subject = subject;
        this.dueDate = dueDate;
    }

    // Setters and getters for different fields

    /**
     * Gets the status of the assignment.
     *
     * @return The status of the assignment.
     */
    public AssignmentStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the assignment.
     *
     * @param status The status to set for the assignment.
     */
    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    /**
     * Gets the unique identifier of the assignment.
     *
     * @return The ID of the assignment.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the assignment.
     *
     * @param id The ID to set for the assignment.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the assignment.
     *
     * @return The name of the assignment.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the assignment.
     *
     * @param name The name to set for the assignment.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the assignment.
     *
     * @return The description of the assignment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the assignment.
     *
     * @param description The description to set for the assignment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the grade of the assignment.
     *
     * @return The grade of the assignment.
     */
    public float getGrade() {
        return grade;
    }

    /**
     * Sets the grade of the assignment.
     *
     * @param grade The grade to set for the assignment.
     */
    public void setGrade(float grade) {
        this.grade = grade;
    }

    /**
     * Gets the username of the student associated with the assignment.
     *
     * @return The username of the student.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the student associated with the assignment.
     *
     * @param username The username to set for the assignment.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the subject associated with the assignment.
     *
     * @return The subject of the assignment.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the subject associated with the assignment.
     *
     * @param subject The subject to set for the assignment.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Gets the due date of the assignment.
     *
     * @return The due date of the assignment.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the assignment.
     *
     * @param dueDate The due date to set for the assignment.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Assignment object.
     *
     * @return A string representing the Assignment details.
     */
    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", grade=" + grade +
                ", username='" + username + '\'' +
                ", subject=" + subject +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
