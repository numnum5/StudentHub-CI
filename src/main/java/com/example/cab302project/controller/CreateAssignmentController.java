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
import java.util.ArrayList;
import java.util.List;

public class CreateAssignmentController {

    private MockSubjectDAO subjectDAO;

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
        this.subjectDAO = new MockSubjectDAO();
    }
    private void loadSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject mock1 = new Subject(1, "CAB502", "Advanced Quantum Computing", "Idk");
        Subject mock2 = new Subject(1, "CAB503", "Advanced Discrete Mathematics", "Idk");
        subjects.add(mock1);
        subjects.add(mock2);
        List<String> subjectNames = new ArrayList<>();
        subjectNames.add(mock1.getName());
        subjectNames.add(mock2.getName());
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

            Assignment newAssignment = new Assignment(assignmentName, assignmentDescription, LoginController.username,testSubject, dueDate);
            newAssignment.setStatus(AssignmentStatus.URGENT);
            parentController.addNewAssignment(newAssignment);
        }catch(Exception error){

        }

    }
}
