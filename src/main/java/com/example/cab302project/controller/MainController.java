package com.example.cab302project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    public void initialize() {
        try {
            FXMLLoader navBarLoader = new FXMLLoader(getClass().getResource("/com/example/cab302project/nav-bar.fxml"));
            Parent navBar = navBarLoader.load();
            NavBarController navBarController = navBarLoader.getController();
            navBarController.setMainController(this);
            mainPane.setTop(navBar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadPage(String fxmlFile) {
        try {

            System.out.println(fxmlFile);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/" + fxmlFile));
            Parent page = loader.load();
            IController controller = loader.getController();
            if(controller != null){
                controller.setMainController(this);
            }
            mainPane.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
