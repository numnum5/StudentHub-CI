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

    private MainController mainController;

    // Method to switching pages
    private void switchScene() throws IOException {
        Stage stage = (Stage) viewGoals.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("goal-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    public GoalsController(){
        this.connection = new MockGoalDAO();
    }

    @FXML
    private void onViewGoalsButtonClick() throws IOException {
        mainController.loadPage("goal-list.fxml");
    }

    @FXML
    private void onSubmitButtonClick() throws IOException {
        // Get the values from the input fields
        String title = goalTitle.getText();
        String details = goalDetails.getText();

        // Validate the input (optional, you can add more checks)
        if (title.isEmpty() || details.isEmpty()) {
            System.out.println("Please fill in both fields.");
            return;
        }

        // Save the goal to the database
        connection.saveGoal(title, details);

        // Clear the fields after saving
        goalTitle.clear();
        goalDetails.clear();

        System.out.println("Goal saved successfully!");
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }
}
