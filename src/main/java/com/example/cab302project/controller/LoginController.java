package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.example.cab302project.model.User;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class that manages the logging user for the application.
 * Handles login submissions and navigates to the registration and home page after sucessful login.
 */
public class LoginController {

    /**
     * Database connection for querying user data.
     */
    private SqliteUserDAO Connection = new SqliteUserDAO();

    /**
     * Stores the username of the user who successfully logs in.
     */
    public static String username;

    /**
     * FXML elements used in the login view.
     */
    @FXML
    private TextField usernameField; // Text field to input the username

    @FXML
    private PasswordField passwordField; // Password field to input the password

    @FXML
    private Button submitButton; // Button to submit the login form

    @FXML
    private Button registerButton; // Button to navigate to the registration page

    @FXML
    private Text warningLabel; // Label to display warnings or error messages

    /**
     * Method for when the "Register" button is clicked.
     * Loads the registration page by setting the scene to "register-ui.fxml".
     */
    @FXML
    private void Register() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("register-ui.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) registerButton.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for loggin in user after pressing submit
     * Validates the username and password, checks against the database, and navigates to the homepage if successful.
     * Displays appropriate error messages if login fails.
     */
    @FXML
    private void Submit() {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        // Validate that both fields are filled
        if (userName.isEmpty() || password.isEmpty()) {
            warningLabel.setText("All fields must be filled.");
        } else {
            // Check if the user exists in the database
            boolean userExists = Connection.userExists(userName.toLowerCase());
            if (userExists) {
                // Check if the password is correct
                boolean passwordCorrect = Connection.passwordCorrect(userName, password);
                if (passwordCorrect) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
                        Scene newScene = new Scene(fxmlLoader.load(), 800, 600);
                        Stage currentStage = (Stage) submitButton.getScene().getWindow();
                        MainController controller = fxmlLoader.getController();

                        // Set username and update the view for the logged-in user
                        controller.setUsername(userName);
                        controller.setNavBar();
                        controller.loadPage("home.fxml");

                        LoginController.username = userName; // Store logged-in username
                        currentStage.setScene(newScene);
                        currentStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    warningLabel.setText("The password you have entered is wrong.");
                }
            } else {
                warningLabel.setText("User " + userName + " does not exist.");
            }
        }
    }
}
