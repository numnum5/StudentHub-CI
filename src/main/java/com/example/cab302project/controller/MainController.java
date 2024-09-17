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

    private String username;

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setNavBar(){
        try {
            FXMLLoader navBarLoader = new FXMLLoader(getClass().getResource("/com/example/cab302project/nav-bar.fxml"));
            Parent navBar = navBarLoader.load();
            NavBarController controller = navBarLoader.getController();
            controller.setUsername(this.username);
            controller.setMainController(this);
            mainPane.setTop(navBar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/" + fxmlFile));
            Parent page = loader.load();
            if(loader.getController() instanceof IController){
                IController controller = loader.getController();
                if(controller != null){
                    controller.setMainController(this);
                }
            }
            mainPane.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
