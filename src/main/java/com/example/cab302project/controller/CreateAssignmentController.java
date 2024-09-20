package com.example.cab302project.controller;

import com.example.cab302project.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class CreateAssignmentController {

    private SubjectManager subjectDAO;

    private ProjectListController parentController;
    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private void initialize() {
        // Dynamically load subjects
        loadSubjects();
        loadAssignmentStatuses();
    }


    public CreateAssignmentController(){
        this.subjectDAO = new SubjectManager(new SqliteSubjectDAO());
    }


    // Method for loading a list of subjects
    private void loadSubjects() {
        List<Subject> subjects = subjectDAO.getAllSubjects();
        List<String> subjectNames = subjects.stream()
                .map(Subject::getName)
                .toList();
        subjectComboBox.setItems(FXCollections.observableArrayList(subjectNames));
    }

    private void loadAssignmentStatuses() {

        List<String> subjectNames = new ArrayList<>();
        subjectNames.add("Urgent");
        subjectNames.add("In progress");
        subjectNames.add("Completed");
        subjectNames.add("Over due");
        statusComboBox.setItems(FXCollections.observableArrayList(subjectNames));
    }

    // Method to set the parent controller
    public void setParentController(ProjectListController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void onSubmit() {
        try {
            String assignmentName = nameField.getText();
            String assignmentDescription = descriptionArea.getText();
            String dueDate = dueDatePicker.getValue().toString();
            String subjectName = subjectComboBox.getValue();
            String status = statusComboBox.getValue();
            if (assignmentName.isEmpty() || assignmentDescription.isEmpty() || dueDate.isEmpty() || subjectName == null || status == null) {
                throw new Exception("Please fill all the input fields");
            }
            Subject selectedSubject = subjectDAO.getAllSubjects().stream()
                    .filter(subject -> subject.getName().equals(subjectName))
                    .findFirst()
                    .orElse(null);

            System.out.println(selectedSubject.toString());
            if (selectedSubject == null) {
                throw new Exception("Selected subject not found");
            }

            Assignment newAssignment = new Assignment(assignmentName, assignmentDescription, LoginController.username, selectedSubject, dueDate);

            // Set the status based on the selection
            newAssignment.setStatus(AssignmentStatus.valueOf(status.toUpperCase().replace(" ", "_")));

            parentController.addNewAssignment(newAssignment);

            // Close the current window
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } catch (Exception error) {
            error.printStackTrace(); // For debugging
        }
    }
}
