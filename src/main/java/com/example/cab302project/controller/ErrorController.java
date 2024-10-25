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


/**
 * Controller for handling the display of error messages in the application.
 * This class is responsible for setting the error message and closing the error dialog.
 */
public class ErrorController {

    /** Label to display the error message. */
    @FXML
    private Label errorMessage;

    /**
     * Public method used by other controllers to set the error message.
     *
     * @param message the error message to be displayed in the label
     */
    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    /**
     * Closes the error message window when the user presses the close button.
     */
    @FXML
    private void handleClose() {
        Stage stage = (Stage) errorMessage.getScene().getWindow();
        stage.close();
    }
}
