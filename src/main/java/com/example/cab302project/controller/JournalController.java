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

public class JournalController implements IController
{
    @FXML
    private Button viewAssignments;

    private MockSubjectDAO connection;


    private MainController mainController;
    private void switchScene() throws IOException {
        Stage stage = (Stage) viewAssignments.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("project-list.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }

    @FXML
    public TextArea MoodText;

    @FXML
    public TextArea JournalText;

    public void Submit()
    {
        String MoodString = MoodText.getText();
        String JournalString = JournalText.getText();

    }
}
