package com.example.cab302project.controller;

import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.AssignmentStatus;
import com.example.cab302project.model.MockSubjectDAO;
import com.example.cab302project.model.Subject;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CreateAssignmentController {

    private MockSubjectDAO subjectDAO;

    private ProjectListController parentController;
    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private ComboBox<Subject> subjectComboBox;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private void initialize() {
        // Dynamically load subjects
        loadSubjects();
    }

    public CreateAssignmentController(){
        this.subjectDAO = new MockSubjectDAO();
    }
    private void loadSubjects() {
        List<Subject> subjects = subjectDAO.getAllUsers();
        subjectComboBox.setItems(FXCollections.observableArrayList(subjects));
    }

    // Method to set the parent controller
    public void setParentController(ProjectListController parentController) {
        this.parentController = parentController;
    }


    @FXML
    private void onSubmit() {

        try{
            // Collect all the input values
            String assignmentName = nameField.getText();
            String assignmentDescription = descriptionArea.getText();
//        Subject selectedSubject = subjectComboBox.getValue();
            Subject testSubject = new Subject(1, "CAB502", "Advanced Quantum Computing", "Idk");
            String dueDate = dueDatePicker.getValue().toString();

            if(assignmentName.isEmpty() || assignmentDescription.isEmpty() || dueDate.isEmpty()){
                throw new Exception("Please fill all the input fields");
            }

//        LocalDate a = dueDatePicker.getValue();

            Assignment newAssignment = new Assignment(assignmentName, assignmentDescription, LoginController.currentUsername,testSubject, dueDate);
            newAssignment.setStatus(AssignmentStatus.URGENT);
            parentController.addNewAssignment(newAssignment);
        }catch(Exception error){

        }

    }
}
