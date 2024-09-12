package com.example.cab302project.controller;

import com.example.cab302project.model.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import com.example.cab302project.model.User;


public class LoginController {
    private SqliteUserDAO Connection = new SqliteUserDAO();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void Submit()
    {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        boolean userExists = Connection.userExists(userName.toLowerCase());
        boolean passwordCorrect = Connection.passwordCorrect(userName, password);
        if(userExists)
        {
            if(passwordCorrect)
            {

            }
            else
            {

            }
        }
        else
        {

        }
    }

}