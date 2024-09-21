package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for managing creating a new Assignment, handles CRUD actions for assignments table
 */
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

    // Constructor for initialising a SubjectManger
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

    // Loads assignment statues to the combobox
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

    // Method for handling information when user submits
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
            if (selectedSubject == null) {
                throw new Exception("Selected subject not found");
            }
            // Create a new assignment object based on the given information
            Assignment newAssignment = new Assignment(assignmentName, assignmentDescription, LoginController.username, selectedSubject, dueDate);
            // Set the status based on the selection
            newAssignment.setStatus(AssignmentStatus.valueOf(status.toUpperCase().replace(" ", "_")));

            parentController.addNewAssignment(newAssignment);

            // Close the current window
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } catch (Exception error) {
            error.printStackTrace();
            showErrorPopup(error.getMessage());
        }
    }

    // Method for displaying a pop up error message tab in case of any errors
    private void showErrorPopup(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("error.fxml"));
            Parent root = loader.load();
            ErrorController controller = loader.getController();
            controller.setErrorMessage(message);

            Stage stage = new Stage();
            // Set modality to application modal to block any inputs
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Error");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
