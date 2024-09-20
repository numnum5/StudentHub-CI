package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.MockGoalDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class GoalListController implements IController
{
    @FXML
    private Button addGoals;

    @FXML
    private VBox goalContainer; // Container to hold the goal

    @FXML
    private Button deleteGoals;

    @FXML
    private TextField searchGoal;

    @FXML
    private Button searchButton;

    private MockGoalDAO connection = new MockGoalDAO(); // DAO to interact with the database

    @FXML
    public void initialize() {
        loadGoals(); // Load goals when the view is initialized
    }

    private MainController mainController;

    private void switchScene() throws IOException {
        Stage stage = (Stage) addGoals.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("goals.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Application.WIDTH, Application.HEIGHT);
        stage.setScene(scene);
    }

    private void loadGoals() {
        List<String[]> goals = connection.getAllGoals(); // Fetch all goals

        // Clear any previous items in the goalContainer
        goalContainer.getChildren().clear();

        // Loop through the goals and add them to the VBox
        for (String[] goal : goals) {
            String title = goal[0];
            String details = goal[1];

            // Create an HBox for each goal item
            HBox goalBox = new HBox(10); // Spacing between elements
            goalBox.setStyle("-fx-background-color: #333; -fx-padding: 10; -fx-border-color: #4CAF50; -fx-border-radius: 5px; -fx-background-radius: 5px;");

            // Create a CheckBox for the goal
            CheckBox goalCheckBox = new CheckBox();
            goalCheckBox.setStyle("-fx-text-fill: white;");

            // Create a label for the goal title
            Label titleLabel = new Label(title);
            titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
            HBox.setHgrow(titleLabel, Priority.ALWAYS);

            // Create a label for the goal details
            Label detailsLabel = new Label(details);
            detailsLabel.setStyle("-fx-text-fill: lightgray;");

            // Add the checkbox, title, and details to the HBox
            goalBox.getChildren().addAll(goalCheckBox, titleLabel, detailsLabel);

            // Add the goalBox to the goalContainer (VBox)
            goalContainer.getChildren().add(goalBox);
        }
    }

    @FXML
    private void onAddGoalsButtonClick() throws IOException {
        mainController.loadPage("goals.fxml");
    }

    @FXML
    private void onDeleteGoalsButtonClick() {
        for (int i = 0; i < goalContainer.getChildren().size(); i++) {
            HBox goalBox = (HBox) goalContainer.getChildren().get(i);
            CheckBox checkBox = (CheckBox) goalBox.getChildren().get(0);

            if (checkBox.isSelected()) {
                Label titleLabel = (Label) goalBox.getChildren().get(1);
                String title = titleLabel.getText();

                // Use the new deleteGoal method in MockGoalDAO
                connection.deleteGoal(title);

                goalContainer.getChildren().remove(goalBox);
                i--; // Adjust index after removal
            }
        }
    }

    @FXML
    private void onSearchButtonClick() {
        String keyword = searchGoal.getText(); // Get the search input from the user

        // Clear the current list of goals
        goalContainer.getChildren().clear();

        // Search for goals matching the keyword using the connection object
        List<String[]> searchResults = connection.searchGoals(keyword);

        // Update the UI with the search results
        for (String[] goal : searchResults) {
            String title = goal[0];
            String details = goal[1];

            // Create an HBox for each goal (reuse existing goal display logic)
            HBox goalBox = new HBox(10); // Spacing between elements
            goalBox.setStyle("-fx-background-color: #333; -fx-padding: 10; -fx-border-color: #4CAF50; -fx-border-radius: 5px; -fx-background-radius: 5px;");

            // Create a CheckBox for the goal
            CheckBox goalCheckBox = new CheckBox();
            goalCheckBox.setStyle("-fx-text-fill: white;");

            // Create a label for the goal title
            Label titleLabel = new Label(title);
            titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
            HBox.setHgrow(titleLabel, Priority.ALWAYS);

            // Create a label for the goal details
            Label detailsLabel = new Label(details);
            detailsLabel.setStyle("-fx-text-fill: lightgray;");

            // Add the checkbox, title, and details to the HBox
            goalBox.getChildren().addAll(goalCheckBox, titleLabel, detailsLabel);

            // Add the goalBox to the goalContainer (VBox)
            goalContainer.getChildren().add(goalBox);
        }
    }



    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }
}
