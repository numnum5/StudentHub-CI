package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.MockGoalDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class GoalListController implements IController
{
    @FXML
    private Button addGoals;

    private MainController mainController;

    private void switchScene() throws IOException {
        Stage stage = (Stage) addGoals.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("goals.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void onAddGoalsButtonClick() throws IOException {
        mainController.loadPage("goals.fxml");
    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }
}
