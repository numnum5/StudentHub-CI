package com.example.cab302project.controller;

import com.example.cab302project.model.GPA;
import com.example.cab302project.model.SqliteGPADAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


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

    public GPACalcController() {
        gpaDao = new SqliteGPADAO();  // Initialize GPADao
    }

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

    // Add calculation error checking

    // Method to calculate GPA (example formula)
    private double calculateGPA(int totalUnits, int hdUnits, int distUnits, int creditUnits, int passUnits, int failUnits) {
        // Implement your specific GPA calculation formula here
        double gpa = (hdUnits * 7 + distUnits * 6 + creditUnits * 5 + passUnits * 4) / (double) totalUnits;
        return gpa;
    }

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
