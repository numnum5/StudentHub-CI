package com.example.cab302project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.example.cab302project.model.User;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void Submit()
    {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        User currentUser = new User(null, null, userName, password);
    }

}