package com.example.cab302project.controller;

import com.example.cab302project.model.GPA;
import com.example.cab302project.model.SqliteGPADAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * The GPACalcController class handles the interactions between the GPA Calculator view and the
 * logic for calculating and managing the GPA. It also interacts with a database through the
 * SqliteGPADAO class to save and load GPA data.
 */
public class GPACalcController {
    @FXML
    private TextField totalUnitsField;

    @FXML
    private TextField highDistinctionUnitsField;

    @FXML
    private TextField distinctionUnitsField;

    @FXML
    private TextField creditUnitsField;

    @FXML
    private TextField passUnitsField;

    @FXML
    private TextField failUnitsField;

    @FXML
    private Label GPAField;

    @FXML
    private Label currentGPA;

    @FXML
    private Button calculateButton;

    @FXML
    private Button resetButton;

    private double currentGPAValue;

    private SqliteGPADAO gpaDao;

    /**
     * Constructor for the GPACalcController class.
     * Initializes the GPADao to manage GPA data storage and retrieval from the database.
     */

    public GPACalcController() {
        gpaDao = new SqliteGPADAO();  // Initialize GPADao
    }

    /**
     * Handles the calculation of GPA based on user input.
     * Validates the input fields and performs the GPA calculation.
     * Displays the calculated GPA or error message on the interface.
     */

    @FXML
    private void handleCalculateGPA() {
        try {
            int totalUnits = Integer.parseInt(totalUnitsField.getText());
            int hdUnits = Integer.parseInt(highDistinctionUnitsField.getText());
            int distUnits = Integer.parseInt(distinctionUnitsField.getText());
            int creditUnits = Integer.parseInt(creditUnitsField.getText());
            int passUnits = Integer.parseInt(passUnitsField.getText());
            int failUnits = Integer.parseInt(failUnitsField.getText());

            // Check if the total units match the sum of the individual units
            int sumOfUnits = hdUnits + distUnits + creditUnits + passUnits + failUnits;
            if (sumOfUnits != totalUnits) {
                GPAField.setText("Error");
                return; // Exit the method early if there's an error
            }

            // Calculate GPA (implement your GPA formula here)
            double gpa = calculateGPA(totalUnits, hdUnits, distUnits, creditUnits, passUnits, failUnits);
            this.currentGPAValue = gpa;
            // Display the calculated GPA
            GPAField.setText(String.format("%.2f", gpa));

        } catch (NumberFormatException e) {
            // Handle invalid input, e.g. show an error or reset fields
            GPAField.setText("Invalid input. Please enter numeric values.");
        }
    }

    /**
     * Handles the event to view the currently saved GPA from the database.
     * Displays the GPA if it exists or shows an error if not.
     */

    @FXML
    private void handleViewGPA() {
        try {
            double value = gpaDao.getGPA(LoginController.username);
            if(value == -1.00){
                throw new Exception("Please save your GPA first to view.");
            }
            String currentGpa = String.format("%.2f", value);
            currentGPA.setText("GPA: " + currentGpa);
        } catch (Exception e) {
            // Handle invalid input, e.g. show an error or reset fields
            currentGPA.setText("Please save your GPA first to view.");
        }
    }

    /**
     * Handles the event to save the currently calculated GPA to the database.
     * Associates the GPA with the logged-in user's username.
     */

    @FXML
    private void handleSaveGPA(){
        try {
            GPA newGpa = new GPA(LoginController.username, currentGPAValue);
            gpaDao.saveGPA(newGpa);
        } catch (Exception e) {
            // Handle invalid input, e.g. show an error or reset fields
            currentGPA.setText("Please save your GPA first to view.");
        }
    }

    /**
     * Calculates the GPA based on the provided units and weighting.
     * This examples formula gives weight to high distinction (7), distinction (6),
     * credit (5), and pass (4) units. Fail units are not considered.
     *
     * @param totalUnits Total units the student has completed
     * @param hdUnits High Distinction units
     * @param distUnits Distinction units
     * @param creditUnits Credit units
     * @param passUnits Pass units
     * @param failUnits Fail units
     * @return Calculated GPA
     */

    // Add calculation error checking

    // Method to calculate GPA (example formula)
    private double calculateGPA(int totalUnits, int hdUnits, int distUnits, int creditUnits, int passUnits, int failUnits) {
        // Implement your specific GPA calculation formula here
        double gpa = (hdUnits * 7 + distUnits * 6 + creditUnits * 5 + passUnits * 4) / (double) totalUnits;
        return gpa;
    }

    /**
     * Resets all the input fields and GPA label to their default state.
     */

    @FXML
    private void handleReset() {
        totalUnitsField.setText("0");
        highDistinctionUnitsField.setText("0");
        distinctionUnitsField.setText("0");
        creditUnitsField.setText("0");
        passUnitsField.setText("0");
        failUnitsField.setText("0");
        GPAField.setText("");
    }

    /**
     * Loads the GPA from the database and displays it on the screen.
     * If no GPA is saved, a message will be shown instead.
     */

    @FXML
    private void loadGPA() {
        double gpa = gpaDao.getGPA(LoginController.username);
        if (gpa != -1) {
            GPAField.setText(String.format("%.2f", gpa));
        } else {
            GPAField.setText("No GPA saved.");
        }
    }

}
