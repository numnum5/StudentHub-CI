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
    private TextField GPAField;

    @FXML
    private Button calculateButton;

    @FXML
    private Button resetButton;

    private SqliteGPADAO gpaDao;

    private Double calculatedGPA;

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
            GPAField.setText(String.format("%.2f", gpa));
            // Store the calculated GPA in a class variable for later use
            this.calculatedGPA = gpa;


        } catch (NumberFormatException e) {
            // Handle invalid input, e.g. show an error or reset fields
            GPAField.setText("Invalid input. Please enter numeric values.");
        }
    }

    @FXML
    private void saveGPAToDatabase(){
        if (calculatedGPA != null){
            GPA newGPA = new GPA(LoginController.username, calculatedGPA);
            gpaDao.saveGPA(newGPA);
            GPAField.setText("GPA saved to database.");
        }
        else {
            GPAField.setText("Calculate GPA first.");
        }
    }

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