package com.example.cab302project.controller;
import com.example.cab302project.model.GoalDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

public class GoalsController implements IController
{
    @FXML
    private Button viewGoals;

    @FXML
    private TextField goalTitle;

    @FXML
    private TextArea goalDetails;

    private GoalDAO connection;

    private MainController mainController;

    public GoalsController(){
        this.connection = new GoalDAO();
    }

    // Switch scene to goal-list.fxml
    @FXML
    private void onViewGoalsButtonClick() throws IOException {
        mainController.loadPage("goal-list.fxml");
    }

    // Add new goal to database
    @FXML
    private void onSubmitButtonClick() throws IOException {
        String title = goalTitle.getText();
        String details = goalDetails.getText();

        if (title.isEmpty() || details.isEmpty()) {
            System.out.println("Please fill in both fields.");
            return;
        }

        connection.saveGoal(title, details);
        System.out.println("Goal saved successfully!");

    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
    }
}
