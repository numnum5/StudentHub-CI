package com.example.cab302project.model;
/**
 * Class for managing GPA DAO functionalities
 */
public class GPAManager {
    private IGPADAO gpaDAO;

    public GPAManager(IGPADAO gpaDAO) {
        this.gpaDAO = gpaDAO;
    }

    public double getGPA(String username) {
        return gpaDAO.getGPA(username);
    }

    public void saveGPA(GPA gpa){
        gpaDAO.saveGPA(gpa);
    }
}
