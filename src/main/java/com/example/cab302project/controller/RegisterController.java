package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.SqliteUserDAO;
import com.example.cab302project.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * The RegisterController class handles user registration in the application.
 * It validates user inputs, and registers the user in the database, 
 * and allows navigation between the register and login pages.
 */
public class RegisterController {

    /**
     * The SqliteUserDAO instance used for interacting with the user database.
     */
    private SqliteUserDAO Connection = new SqliteUserDAO();

    /**
     * The FXML object for the username input field.
     */
    @FXML
    private TextField usernameField;

    /**
     * The FXML object for the password input field.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * The FXML object for the first name input field.
     */
    @FXML
    private TextField firstNameField;

    /**
     * The FXML object for the last name input field.
     */
    @FXML
    private TextField lastNameField;

    /**
     * The FXML object for the login button.
     */
    @FXML
    private Button loginButton;

    /**
     * The FXML object for the warning label that displays registration status messages.
     */
    @FXML
    private Text warningLabel;

    /**
     * Handles  button click by extracting input values from all fields,
     * validating them, and registers the user in the database.
     * Displays appropriate feedback messages on success or failure.
     */
    @FXML
    private void Submit() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = usernameField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            warningLabel.setText("All fields must be filled.");
        } else {
            User user = new User(firstName, lastName, userName, password);
            Connection.addUser(user);

            if (Connection.userUnique) {
                warningLabel.setText("Successfully Registered.");
            } else {
                warningLabel.setText("User already exists.");
            }
        }
    }

    /**
     * Handles the action of the login button click by navigating to the login page.
     * Changes the scene to the login UI.
     */
    @FXML
    private void Login() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-ui.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
