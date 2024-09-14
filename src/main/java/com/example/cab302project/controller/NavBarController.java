package com.example.cab302project.controller;

import com.example.cab302project.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


// Controller for controlling navigation bar
public class NavBarController {

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
    private MenuButton userButton;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem profile;

    @FXML
    private MenuItem expertsContact;

    // Have to somehow communicate to the login page to identify if user is logged in
    private boolean isLoggedIn = false;


    // Initalisation method
    @FXML
    private void initialize() {
        // Select home page as default
        homeButton.setSelected(true);
    }


    // Handlers for different buttons
    @FXML
    protected void onHomeButtonClick() throws IOException {
        handleButtonClick(homeButton, "home.fxml");
    }

    @FXML
    protected void onJournalButtonClick() throws IOException {
        handleButtonClick(journalButton, "journal.fxml");
    }

    @FXML
    protected void onGpaButtonClick() throws IOException {
        handleButtonClick(gpaButton, "gpa.fxml");
    }

    @FXML
    protected void onGoalButtonClick() throws IOException {
        handleButtonClick(goalButton, "goal.fxml");
    }

    @FXML
    protected void onProjectsButtonClick() throws IOException {
        handleButtonClick(projectsButton, "projects.fxml");
    }

    @FXML
    protected void onFinanceButtonClick() throws IOException {
        handleButtonClick(financeButton, "finance.fxml");
    }


    // Handle logging out
    @FXML
    private void onLogoutButtonClick() throws IOException {
        // Redirect to home page
        isLoggedIn = false;
        switchScene(homeButton, "login-ui.fxml");
    }

    @FXML
    private void onProfileButtonClick() throws IOException {
        switchScene(homeButton, "profile.fxml");
    }

    @FXML
    private void onExpertsContactsButtonClick() throws IOException {
        switchScene(homeButton, "experts-contact.fxml");
    }


    // Method to handle switching scenes and updating button state
    private void handleButtonClick(ToggleButton activeButton, String fxmlFile) throws IOException {
        // Unselect all the buttons
        resetButtons();

        // Check currently selected button to avoid reloading the same scene if the button is already active
        if (activeButton.isSelected()) {
            return;
        }
        // Set the active button and switch the page
        activeButton.setSelected(true);
        // Switch page
        switchScene(activeButton, fxmlFile);
    }


    // Method to switching pages
    private void switchScene(ToggleButton activeButton, String fxmlFile) throws IOException {
        Stage stage = (Stage) activeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    // Method for unselecting all buttons
    private void resetButtons() {
        homeButton.setSelected(false);
        journalButton.setSelected(false);
        gpaButton.setSelected(false);
        goalButton.setSelected(false);
        projectsButton.setSelected(false);
        financeButton.setSelected(false);
    }

    public void handleUserButtonClick(ActionEvent actionEvent) {

    }
}