package com.example.cab302project.controller;

import com.example.cab302project.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * Controller for the navigation bar of the application.
 * Handles the navigation between different pages and manages user actions on the navigation buttons.
 */
public class NavBarController implements IController {

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

    /**
     * Sets the username for the current session.
     *
     * @param username the username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username of the current session.
     *
     * @return the current user's username
     */
    public String getUsername() {
        return this.username;
    }

    private MainController mainController; // Reference to the main controller

    /**
     * Sets the reference to the main controller.
     *
     * @param mainController the main controller to be set
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Initializes the navigation bar by setting the home button as selected.
     */
    @FXML
    private void initialize() {
        homeButton.setSelected(true);
    }

    // Handlers for button click events

    /**
     * Handles the event when the home button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    protected void onHomeButtonClick() throws IOException {
        handleButtonClick(homeButton, "home.fxml"); // Navigate to home
    }

    /**
     * Handles the event when the journal button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    protected void onJournalButtonClick() throws IOException {
        handleButtonClick(journalButton, "journal.fxml"); // Navigate to journal
    }

    /**
     * Handles the event when the GPA button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    protected void onGpaButtonClick() throws IOException {
        handleButtonClick(gpaButton, "GPA-Calculator.fxml"); // Navigate to GPA calculator
    }

    /**
     * Handles the event when the goal button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    protected void onGoalButtonClick() throws IOException {
        handleButtonClick(goalButton, "goal-list.fxml");
    }

    /**
     * Handles the event when the projects button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    protected void onProjectsButtonClick() throws IOException {
        handleButtonClick(projectsButton, "project-list.fxml"); // Navigate to project list
    }

    /**
     * Handles the event when the finance button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    protected void onFinanceButtonClick() throws IOException {
        handleButtonClick(financeButton, "finance.fxml"); // Navigate to finance page
    }

    /**
     * Handles user logout by clearing the username and switching to the login page.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onLogoutButtonClick() throws IOException {
        setUsername(null); // Clear the username
        switchScene(homeButton, "login-ui.fxml"); // Switch to login page
    }

    /**
     * Handles the event when the profile button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onProfileButtonClick() throws IOException {
        switchScene(homeButton, "profile.fxml"); // Navigate to profile page
    }

    /**
     * Handles the event when the experts contact button is clicked.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void onExpertsContactsButtonClick() throws IOException {
        switchScene(homeButton, "experts-contact.fxml"); // Navigate to experts contact page
    }

    /**
     * Handles button click events and switches scenes accordingly.
     *
     * @param activeButton the button that was clicked
     * @param fxmlFile the name of the FXML file to load
     * @throws IOException if the FXML file cannot be loaded
     */
    private void handleButtonClick(ToggleButton activeButton, String fxmlFile) throws IOException {
        resetButtons(); // Unselect all buttons
        activeButton.setSelected(true); // Select the active button
        mainController.loadPage(fxmlFile); // Load the specified page
    }

    /**
     * Switches the scene to the specified FXML file.
     *
     * @param activeButton the button that triggered the scene change
     * @param fxmlFile the name of the FXML file to load
     * @throws IOException if the FXML file cannot be loaded
     */
    private void switchScene(ToggleButton activeButton, String fxmlFile) throws IOException {
        Stage stage = (Stage) activeButton.getScene().getWindow(); // Get the current stage
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile)); // Load the new scene
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT); // Create a new scene
        stage.setScene(scene); // Set the new scene to the stage
    }

    /**
     * Resets the selection state of all navigation buttons.
     */
    private void resetButtons() {
        homeButton.setSelected(false);
        journalButton.setSelected(false);
        gpaButton.setSelected(false);
        goalButton.setSelected(false);
        projectsButton.setSelected(false);
        financeButton.setSelected(false);
    }

}
