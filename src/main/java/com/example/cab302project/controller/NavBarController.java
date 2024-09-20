package com.example.cab302project.controller;

import com.example.cab302project.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

// Controller for the navigation bar of the application.
// handles the navigation between different pages and manages user actions on the navigation buttons.
public class NavBarController implements IController{

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton journalButton;

    @FXML
    private ToggleButton gpaButton;

    @FXML
    private ToggleButton goalButton;

    @FXML
    private ToggleButton projectsButton;

    @FXML
    private ToggleButton financeButton;

    @FXML
    private MenuButton userButton; // Menu for user options

    @FXML
    private MenuItem logout; // Logout menu item

    @FXML
    private MenuItem profile; // Profile menu item

    @FXML
    private MenuItem expertsContact; // Experts contact menu item

    private String username; // Stores the current user's username

    // Setter for the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for the username
    public String getUsername() {
        return this.username;
    }

    private MainController mainController; // Reference to the main controller

    // Setter for the main controller
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // Initialization method to set the default selected button
    @FXML
    private void initialize() {
        homeButton.setSelected(true); // Set home page as default
    }

    // Handlers for button click events
    @FXML
    protected void onHomeButtonClick() throws IOException {
        System.out.println(this.username); // Print username for debugging
        handleButtonClick(homeButton, "home.fxml"); // Navigate to home
    }

    @FXML
    protected void onJournalButtonClick() throws IOException {
        handleButtonClick(journalButton, "journal.fxml"); // Navigate to journal
    }

    @FXML
    protected void onGpaButtonClick() throws IOException {
        handleButtonClick(gpaButton, "GPA-Calculator.fxml"); // Navigate to GPA calculator
    }

    @FXML
    protected void onGoalButtonClick() throws IOException {
        handleButtonClick(goalButton, "goal.fxml"); // Navigate to goals page
    }

    @FXML
    protected void onProjectsButtonClick() throws IOException {
        handleButtonClick(projectsButton, "project-list.fxml"); // Navigate to project list
    }

    @FXML
    protected void onFinanceButtonClick() throws IOException {
        handleButtonClick(financeButton, "finance.fxml"); // Navigate to finance page
    }

    // Handles user logout
    @FXML
    private void onLogoutButtonClick() throws IOException {
        setUsername(null); // Clear the username
        switchScene(homeButton, "login-ui.fxml"); // Switch to login page
    }

    @FXML
    private void onProfileButtonClick() throws IOException {
        switchScene(homeButton, "profile.fxml"); // Navigate to profile page
    }

    @FXML
    private void onExpertsContactsButtonClick() throws IOException {
        switchScene(homeButton, "experts-contact.fxml"); // Navigate to experts contact page
    }

    // Handles button click events and switches scenes accordingly
    private void handleButtonClick(ToggleButton activeButton, String fxmlFile) throws IOException {
        // Unselect all buttons
        resetButtons();
        activeButton.setSelected(true); // Select the active button
        mainController.loadPage(fxmlFile); // Load the corresponding page
    }

    // Switches the scene to the specified FXML file
    private void switchScene(ToggleButton activeButton, String fxmlFile) throws IOException {
        Stage stage = (Stage) activeButton.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile)); // Load the new scene
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT); // Create a new scene
        stage.setScene(scene); // Set the new scene to the stage
    }

    // Resets the selection state of all navigation buttons
    private void resetButtons() {
        homeButton.setSelected(false);
        journalButton.setSelected(false);
        gpaButton.setSelected(false);
        goalButton.setSelected(false);
        projectsButton.setSelected(false);
        financeButton.setSelected(false);
    }

}
