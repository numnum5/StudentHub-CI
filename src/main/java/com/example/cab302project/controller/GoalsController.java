package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.MockGoalDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class GoalsController implements IController
{
    @FXML
    private Button viewGoals;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private TextField goalTitle;

    @FXML
    private TextArea goalDetails;

    private MockGoalDAO connection;

    private String originalTitle; // To store the original title during editing

    private MainController mainController;

    public GoalsController(){
        this.connection = new MockGoalDAO();
    }

//    // Method to switching pages
//    private void switchScene() throws IOException {
//        Stage stage = (Stage) viewGoals.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("goal-list.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
//        stage.setScene(scene);
//    }

    // Method to load the selected goal for editing
    public void loadGoalForEditing(String title, String details) {
        originalTitle = title; // Store the original title for updating
        goalTitle.setText(title);
        goalDetails.setText(details);
    }


    @FXML
    private void onViewGoalsButtonClick() throws IOException {
        // Directly switch back to the goal-list.fxml scene
        Stage stage = (Stage) viewGoals.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("goal-list.fxml"));
        Scene scene = new Scene(loader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void onSubmitButtonClick() throws IOException {
        String title = goalTitle.getText();
        String details = goalDetails.getText();

        if (title.isEmpty() || details.isEmpty()) {
            System.out.println("Please fill in both fields.");
            return;
        }

        // Check if it's an edit operation (when originalTitle is set)
        if (originalTitle != null) {
            connection.updateGoal(originalTitle, title, details); // Update the goal
            originalTitle = null; // Reset after update
        } else {
            connection.saveGoal(title, details); // Save new goal
        }

        // Clear the fields after saving
        goalTitle.clear();
        goalDetails.clear();

        System.out.println("Goal saved successfully!");

        // After saving, switch back to the goal-list.fxml
        switchScene(); // This will take you back to the goal list page
    }

    private void switchScene() throws IOException {
        Stage stage = (Stage) goalTitle.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("goal-list.fxml"));
        Scene scene = new Scene(loader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    public void resetForm() {
        // Clear the form fields and reset editing state
        goalTitle.clear();
        goalDetails.clear();
        originalTitle = null; // Ensure originalTitle is null, so it's not in edit mode
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }
}
