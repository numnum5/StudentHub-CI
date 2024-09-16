package com.example.cab302project.model;

import java.time.LocalDate;

public class Assignment {
    private int id;
    private String name;
    private String description;
    private float grade;
    private String username;
    private Subject subject;
    private LocalDate dueDate;

    // Constructor
    public Assignment(int id, String name, String description, float grade, String username, Subject subject, LocalDate dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.grade = grade;
        this.username = username;
        this.subject = subject;
        this.dueDate = dueDate;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
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
                '}';
    }
}