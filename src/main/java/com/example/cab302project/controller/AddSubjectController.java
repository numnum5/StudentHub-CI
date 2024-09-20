package com.example.cab302project.controller;

import com.example.cab302project.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

// Controller for handling adding a new subject to the database and UI for add subject fxml page
public class AddSubjectController {

    private SubjectManager manager;

    @FXML
    private TextField unitCode;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<String> semesterComboBox;

    @FXML
    private void initialize() {
        loadSemesters();
    }

    public AddSubjectController(){
        this.manager = new SubjectManager(new SqliteSubjectDAO());
    }

    private void loadSemesters() {

        List<String> subjectNames = new ArrayList<>();
        subjectNames.add("Semester 1");
        subjectNames.add("Semester 2");
        subjectNames.add("Summer Semester");
        semesterComboBox.setItems(FXCollections.observableArrayList(subjectNames));
    }


    @FXML
    private void onSubmit() {
        try {
            String subjectName = nameField.getText();
            String subjectDescription = descriptionArea.getText();
            String semester = semesterComboBox.getValue();
            String unitCodeString = unitCode.getText();
            if (subjectName.isEmpty() || subjectDescription.isEmpty() || semester == null) {
                throw new Exception("Please fill all the input fields");
            }
            int semesterNumber;
            if(unitCodeString.equals("Semester 1")){
                semesterNumber = 1;
            }else if(unitCodeString.equals("Semester 2")){
                semesterNumber = 2;
            }else {
                semesterNumber = 3;
            }
            Subject subject = new Subject(semesterNumber, unitCodeString, subjectName, subjectDescription);
            manager.addSubject(subject);
            // Close the current window
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
