package com.example.cab302project.controller;

import com.example.cab302project.model.GoalDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Controller responsible for handling user interactions on the goals page.
 */
public class GoalsController implements IController {

    @FXML
    private Button viewGoals;

    @FXML
    private TextField goalTitle;

    @FXML
    private TextArea goalDetails;

    private GoalDAO connection;

    private MainController mainController;

    /**
     * Constructor for the GoalsController.
     * Initializes the connection to the GoalDAO.
     */
    public GoalsController() {
        this.connection = new GoalDAO();
    }

    /**
     * Switches the scene to the goal-list.fxml page.
     *
     * @throws IOException If an error occurs while loading the goal-list.fxml.
     */
    @FXML
    private void onViewGoalsButtonClick() throws IOException {
        mainController.loadPage("goal-list.fxml");
    }

    /**
     * Handles the submission of a new goal.
     * Retrieves the goal title and details from the input fields, validates them,
     * and saves the new goal to the database.
     *
     * @throws IOException If an error occurs during the save operation.
     */
    @FXML
    private void onSubmitButtonClick() throws IOException {
        String title = goalTitle.getText();
        String details = goalDetails.getText();

        // Validate the input fields
        if (title.isEmpty() || details.isEmpty()) {
            System.out.println("Please fill in both fields.");
            return;
        }

        // Save the goal to the database
        connection.saveGoal(title, details);
        System.out.println("Goal saved successfully!");
    }

    /**
     * Sets the main controller for switching between scenes.
     *
     * @param mainController The main controller of the application.
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        System.out.println(this.mainController);
    }
}
