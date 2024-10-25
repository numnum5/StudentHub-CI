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


public class LoginController {
    /**
     * Establish a connection to the database.
     */
    private SqliteUserDAO Connection = new SqliteUserDAO();

    public static String username;

    /**
     * Handles FXML objects.
     */
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text warningLabel;

    /**
     * Handles register button click to go to the register page by setting the scene.
     */
    @FXML
    private void Register()
    {
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
     * Handles submission button click by querying the database, and going to the homepage
     * if username and password combination is correct.
     */
    @FXML
    private void Submit()
    {
        String userName = usernameField.getText();
        String password = passwordField.getText();


        if(userName.isEmpty() || password.isEmpty())
        {
            warningLabel.setText("All fields must be filled.");
        }
        else
        {
            boolean userExists = Connection.userExists(userName.toLowerCase());
            if(userExists)
            {
                boolean passwordCorrect = Connection.passwordCorrect(userName, password);
                if(passwordCorrect)
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
                        Scene newScene = new Scene(fxmlLoader.load(), 800, 600);
                        Stage currentStage = (Stage) submitButton.getScene().getWindow();
                        MainController controller = fxmlLoader.getController();

                        controller.setUsername(userName);
                        controller.setNavBar();
                        controller.loadPage("home.fxml");
                        LoginController.username = userName;
                        currentStage.setScene(newScene);
                        currentStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    warningLabel.setText("The password you have entered is wrong.");
                }
            }
            else
            {
                warningLabel.setText("user " + userName + " does not exist.");
            }
        }
    }

}