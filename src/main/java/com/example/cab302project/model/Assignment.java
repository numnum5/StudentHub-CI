package com.example.cab302project.model;

import java.time.LocalDate;

// Class for modeling an assignment item with necessary fields.
public class Assignment {
    private int id;
    private String name;
    private String description;
    private float grade = 0;
    private String username;
    private Subject subject;
    private String dueDate;
    private AssignmentStatus status;

    // Constructor
    public Assignment(String name, String description, String username, Subject subject, String dueDate) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.subject = subject;
        this.dueDate = dueDate;
    }


    // Setters and getters for different fields
    // Getter for status
    public AssignmentStatus getStatus() {
        return this.status;
    }

    // Setter for status (optional)
    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

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
                ", status=" +
                '}';
    }
}