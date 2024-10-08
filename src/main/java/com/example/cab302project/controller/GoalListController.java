//package com.example.cab302project.controller;
//
//import com.example.cab302project.model.GoalDAO;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//import java.io.IOException;
//import java.util.List;
//
//public class GoalListController implements IController {
//
//    @FXML
//    private Button addGoals, deleteGoals, searchButton;
//
//    @FXML
//    private VBox goalContainer;
//
//    @FXML
//    private TextField searchGoal;
//
//    private GoalDAO connection = new GoalDAO(); // Data access object for goals
//    private MainController mainController;
//
//    // Initializes the view by loading all goals when the scene is displayed
//    @FXML
//    public void initialize() {
//        loadGoals();
//    }
//
//    // Loads all goals from the database and displays them in the VBox
//    private void loadGoals() {
//        List<String[]> goals = connection.getAllGoals();
//        goalContainer.getChildren().clear();
//
//        for (String[] goal : goals) {
//            HBox goalBox = createGoalBox(goal[0], goal[1]);
//            goalContainer.getChildren().add(goalBox);
//        }
//    }
//
//    // Create an HBox layout for each goal (title, details, checkbox)
//    private HBox createGoalBox(String title, String details) {
//        HBox goalBox = new HBox(10); // HBox with spacing between elements
//        goalBox.setStyle("-fx-background-color: #333; -fx-padding: 10; -fx-border-color: #4CAF50; -fx-border-radius: 5px; -fx-background-radius: 5px;");
//
//        CheckBox goalCheckBox = new CheckBox();
//        goalCheckBox.setStyle("-fx-text-fill: white;");
//
//        Label titleLabel = new Label(title);
//        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
//        HBox.setHgrow(titleLabel, Priority.ALWAYS);
//
//        Label detailsLabel = new Label(details);
//        detailsLabel.setStyle("-fx-text-fill: lightgray;");
//
//        goalBox.getChildren().addAll(goalCheckBox, titleLabel, detailsLabel);
//        return goalBox;
//    }
//
//    // Switch scene to goals.fxml
//    @FXML
//    private void onAddGoalsButtonClick() throws IOException {
//        mainController.loadPage("goals.fxml");
//    }
//
//    // Deletes selected goals from both the UI and the database
//    @FXML
//    private void onDeleteGoalsButtonClick() {
//        goalContainer.getChildren().removeIf(node -> {
//            HBox goalBox = (HBox) node;
//            CheckBox checkBox = (CheckBox) goalBox.getChildren().get(0);
//
//            if (checkBox.isSelected()) {
//                Label titleLabel = (Label) goalBox.getChildren().get(1);
//                connection.deleteGoal(titleLabel.getText());
//                return true; // Remove the selected goal from the container
//            }
//            return false;
//        });
//    }
//
//    // Searches for goals by a keyword entered by the user and updates the goal list
//    @FXML
//    private void onSearchButtonClick() {
//        String keyword = searchGoal.getText();
//        goalContainer.getChildren().clear();
//        List<String[]> searchResults = connection.searchGoals(keyword);
//
//        for (String[] goal : searchResults) {
//            HBox goalBox = createGoalBox(goal[0], goal[1]);
//            goalContainer.getChildren().add(goalBox);
//        }
//    }
//
//    @Override
//    public void setMainController(MainController mainController) {
//        this.mainController = mainController;
//    }
//}

package com.example.cab302project.controller;

import com.example.cab302project.model.GoalDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

/**
 * Controller for handling the display and management of goals in the application.
 */
public class GoalListController implements IController {

    @FXML
    private Button addGoals, deleteGoals, searchButton;

    @FXML
    private VBox goalContainer;

    @FXML
    private TextField searchGoal;

    private GoalDAO connection = new GoalDAO(); // Data access object for goals
    private MainController mainController;

    /**
     * Initializes the view by loading all goals when the scene is displayed.
     */
    @FXML
    public void initialize() {
        loadGoals();
    }

    /**
     * Loads all goals from the database and displays them in the VBox.
     */
    private void loadGoals() {
        List<String[]> goals = connection.getAllGoals();
        goalContainer.getChildren().clear();

        for (String[] goal : goals) {
            HBox goalBox = createGoalBox(goal[0], goal[1]);
            goalContainer.getChildren().add(goalBox);
        }
    }

    /**
     * Creates an HBox layout for each goal, including the title, details, and checkbox.
     *
     * @param title   The title of the goal.
     * @param details The details of the goal.
     * @return A formatted HBox containing the goal data.
     */
    private HBox createGoalBox(String title, String details) {
        HBox goalBox = new HBox(10); // HBox with spacing between elements
        goalBox.setStyle("-fx-background-color: #333; -fx-padding: 10; -fx-border-color: #4CAF50; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        CheckBox goalCheckBox = new CheckBox();
        goalCheckBox.setStyle("-fx-text-fill: white;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        HBox.setHgrow(titleLabel, Priority.ALWAYS);

        Label detailsLabel = new Label(details);
        detailsLabel.setStyle("-fx-text-fill: lightgray;");

        goalBox.getChildren().addAll(goalCheckBox, titleLabel, detailsLabel);
        return goalBox;
    }

    /**
     * Switches the scene to goals.fxml when the "Add Goals" button is clicked.
     *
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void onAddGoalsButtonClick() throws IOException {
        mainController.loadPage("goals.fxml");
    }

    /**
     * Deletes the selected goals from both the UI and the database.
     * This method iterates through the goal list and removes those with selected checkboxes.
     */
    @FXML
    private void onDeleteGoalsButtonClick() {
        goalContainer.getChildren().removeIf(node -> {
            HBox goalBox = (HBox) node;
            CheckBox checkBox = (CheckBox) goalBox.getChildren().get(0);

            if (checkBox.isSelected()) {
                Label titleLabel = (Label) goalBox.getChildren().get(1);
                connection.deleteGoal(titleLabel.getText());
                return true; // Remove the selected goal from the container
            }
            return false;
        });
    }

    /**
     * Searches for goals by a keyword entered by the user and updates the goal list.
     */
    @FXML
    private void onSearchButtonClick() {
        String keyword = searchGoal.getText();
        goalContainer.getChildren().clear();
        List<String[]> searchResults = connection.searchGoals(keyword);

        for (String[] goal : searchResults) {
            HBox goalBox = createGoalBox(goal[0], goal[1]);
            goalContainer.getChildren().add(goalBox);
        }
    }

    /**
     * Sets the MainController reference to allow switching between views.
     *
     * @param mainController The main controller of the application.
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
