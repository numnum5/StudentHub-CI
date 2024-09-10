package com.example.cab302project.controller;

import com.example.cab302project.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;

public class RegisterController {
    @FXML
    private Text registerTitle;

    @FXML
    private Button submitButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button loginButton;

    @FXML
    private void Submit()
    {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String userName = usernameField.getText();
        String password = passwordField.getText();
    }
    @FXML
    private void Login() {
        try {
            // Load the new FXML file (e.g., "login.fxml")
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-ui.fxml"));
            Scene newScene = new Scene(fxmlLoader.load());

            // Get the current stage from the loginButton
            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            // Set the new scene to the current stage
            currentStage.setScene(newScene);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}