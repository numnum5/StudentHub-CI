package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.MockSubjectDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectsController implements IController{

    @FXML
    private Button viewAssignments;

    private MockSubjectDAO connection;


    private MainController mainController;
    // Method to switching pages
    private void switchScene() throws IOException {
        Stage stage = (Stage) viewAssignments.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("project-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    public ProjectsController(){
        this.connection = new MockSubjectDAO();
    }


    @FXML
    private void onViewAssignmentsButtonClick() throws IOException {
        mainController.loadPage("project-list.fxml");
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }
}
