package com.example.cab302project.controller;

import com.example.cab302project.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

/**
 * Main controller for the application, responsible for managing the main layout and navigation.
 */
public class MainController {

    @FXML
    private BorderPane mainPane; // The main layout of the application

    private String username; // Stores the current user's username

    /**
     * Sets the username for the current session.
     *
     * @param username the username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username of the current session.
     *
     * @return the current user's username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the navigation bar in the main layout.
     * This method loads the navigation bar FXML file and links it to the main controller,
     * allowing the navigation bar to have access to the current user's username.
     */
    public void setNavBar() {
        try {
            // Load the navigation bar FXML file
            FXMLLoader navBarLoader = new FXMLLoader(Application.class.getResource("nav-bar.fxml"));
            Parent navBar = navBarLoader.load();
            // Get the controller for the nav bar
            NavBarController controller = navBarLoader.getController();
            controller.setUsername(this.username); // Set the username in the nav bar controller
            controller.setMainController(this); // Link the nav bar controller to the main controller
            mainPane.setTop(navBar); // Set the loaded navigation bar at the top of the main pane
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a specified FXML page into the center of the main layout.
     *
     * @param fxmlFile the name of the FXML file to be loaded
     */
    public void loadPage(String fxmlFile) {
        try {
            // Load the specified FXML file
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFile));
            Parent page = loader.load(); // Load the page

            // Check if the controller implements IController
            if (loader.getController() instanceof IController) {
                IController controller = (IController) loader.getController();
                if (controller != null) {
                    controller.setMainController(this); // Set the main controller reference
                }
            }
            mainPane.setCenter(page); // Set the loaded page in the center of the main pane
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}