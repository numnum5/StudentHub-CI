package com.example.cab302project.model;

public interface IGPADAO {
    void saveGPA(GPA gpa);

    double getGPA(String username);
}