package com.example.cab302project.model;
/**
 * Interface for GPA DAO, includes the methods that Mock and Sqlite DAO's are going to implement
 */
public interface IGPADAO {
    void saveGPA(GPA gpa);

    double getGPA(String username);
}