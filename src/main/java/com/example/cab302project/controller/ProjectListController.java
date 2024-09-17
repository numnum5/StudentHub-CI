package com.example.cab302project.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectListController implements IController{


    @FXML
    private VBox taskListContainer;

    @FXML
    private void initialize() {
        // Example tasks
//        String[] tasks = {
//                "Task 1: Read lecture slides",
//                "Task 2: Complete coding exercise",
//                "Task 3: Write report"
//        };
//
//        // Add tasks dynamically
//        addTasks(tasks);
    }

    private void addTasks(String[] tasks) {
        for (String task : tasks) {
            Label taskLabel = new Label("• " + task); // Add bullet point
            taskLabel.getStyleClass().add("card-task-item"); // Add CSS class for styling
            taskListContainer.getChildren().add(taskLabel);
        }
    }


    @FXML
    private void onNewAssignmentClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/" + "create-new-assignment.fxml"));
        Parent root = loader.load();

        // Create a new Stage
        Stage newStage = new Stage();
        newStage.setTitle("New Assignment");
        newStage.setScene(new Scene(root));

        // Show the new Stage
        newStage.show();
    }
    @FXML
    private void onAddNewSubjectClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/" + "create-new-subject.fxml"));
        Parent root = loader.load();

        // Create a new Stage
        Stage newStage = new Stage();
        newStage.setTitle("New Subject");
        newStage.setScene(new Scene(root));

        // Show the new Stage
        newStage.show();
    }
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
