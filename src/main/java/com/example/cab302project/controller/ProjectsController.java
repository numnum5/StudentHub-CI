package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.MockSubjectDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for managing the project view and navigation to the assignment list.
 * This class implements the IController interface and facilitates page transitions.
 */
public class ProjectsController implements IController {

    @FXML
    private Button viewAssignments; // Button to view assignments

    private MockSubjectDAO connection; // DAO for subject data management

    private MainController mainController; // Reference to the main controller for page management

    /**
     * Constructor initializes the DAO instance for managing subjects.
     */
    public ProjectsController() {
        this.connection = new MockSubjectDAO();
    }

    /**
     * Switches the current scene to the project list page.
     *
     * @throws IOException If an error occurs while loading the project list FXML.
     */
    private void switchScene() throws IOException {
        Stage stage = (Stage) viewAssignments.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("project-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the button click event to view assignments by loading the project list page.
     *
     * @throws IOException If an error occurs while loading the project list page.
     */
    @FXML
    private void onViewAssignmentsButtonClick() throws IOException {
        mainController.loadPage("project-list.fxml");
    }

    /**
     * Sets the main controller for this controller, allowing access to shared functionality.
     *
     * @param mainController The main controller to be set.
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        System.out.println(this.mainController);
    }

    /**
     * Controller for the journal entry functionality.
     * This inner class manages user mood submissions.
     */
    public static class JournalController {

        @FXML
        public TextArea MoodText; // Text area for user to input their mood

        /**
         * Submits the mood text input by the user.
         * Outputs the mood text to the console.
         */
        public void Submit() {
            System.out.println(MoodText.getText());
        }
    }
}
