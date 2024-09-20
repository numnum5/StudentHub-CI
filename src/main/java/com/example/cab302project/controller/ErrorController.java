package com.example.cab302project.controller;

import com.example.cab302project.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


// Controller for handling displaying of error messages
public class ErrorController {
    @FXML
    private Label errorMessage;

    // Public method used by other controllers to set the error message
    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    // Closes the window when user presses the close button
    @FXML
    private void handleClose() {
        Stage stage = (Stage) errorMessage.getScene().getWindow();
        stage.close();
    }
}
