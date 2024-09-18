package com.example.cab302project.controller;

import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.AssignmentStatus;
import com.example.cab302project.model.MockAssignmentDAO;
import com.example.cab302project.model.Subject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ProjectListController{

    public ProjectListController(){
        this.assignmentDAO = new MockAssignmentDAO();
    }

    @FXML
    private TextField searchField;

    @FXML
    private Button newAssignment, newSubject;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox cardContainer;
    @FXML
    private ListView<Assignment> assignmentListView;
    @FXML
    private VBox taskListContainer;

    private MockAssignmentDAO assignmentDAO;

    private void renderCards(List<Assignment> projects) {
        if(assignmentListView != null) {
            assignmentListView.getItems().clear();
        }
        cardContainer.getChildren().clear();
        for (Assignment project : projects) {
            addNewAssignment(project);
        }
    }

    // Initalisation method
    @FXML
    private void initialize() {
        assignmentDAO.populateData();
        renderCards(assignmentDAO.getAllAssignments());
    }


    @FXML
    private void onSearch() throws IOException {
        List<Assignment> result = assignmentDAO.searchAssignments(searchField.getText());

        System.out.println(result.size());
        for(Assignment assignment : result) {
            System.out.println(assignment.toString());
        }
        renderCards(result);
    }

    public void addNewAssignment(Assignment assignment) {
        VBox newCard = new VBox();
        newCard.getStyleClass().add("card");
        Label titleLabel = new Label(assignment.getSubject().getUnitCode() + assignment.getSubject().getName());
        titleLabel.getStyleClass().add("card-title");
        Label semesterLabel = new Label("Semester " + assignment.getSubject().getSemester());
        semesterLabel.getStyleClass().add("card-subtitle");
        Label assignmentLabel = new Label(assignment.getName());
        assignmentLabel.getStyleClass().add("card-assignment");
        Label descriptionLabel = new Label(assignment.getDescription());
        descriptionLabel.getStyleClass().add("card-content");
        Label dueDateLabel = new Label("Due: " + assignment.getDueDate());
        dueDateLabel.getStyleClass().add("card-due-date");

//        // Dynamically change the style based on the assignment status
//        switch (assignment.getStatus()) {
//            case URGENT:
//                statusLabel.setStyle("-fx-background-color: #ff5252; -fx-text-fill: white; " +
//                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
//                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
//                break;
//            case PENDING:
//                statusLabel.setStyle("-fx-background-color: #ffa500; -fx-text-fill: white; " +
//                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
//                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
//                break;
//            case COMPLETED:
//                statusLabel.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; " +
//                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
//                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
//                break;
//            default:
//                statusLabel.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black; " +
//                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
//                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
//                break;
//        }
        newCard.getChildren().addAll(
                titleLabel,
                semesterLabel,
                assignmentLabel,
                descriptionLabel,
                dueDateLabel
                );
        // Finally, add the new card to the cardContainer
        cardContainer.getChildren().add(newCard);
        assignmentDAO.addAssignment(assignment);
    }

    @FXML
    private void onNewAssignmentClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/" + "create-new-assignment.fxml"));
        Parent root = loader.load();

        CreateAssignmentController controller = loader.getController();
        // Create a new Stage
        controller.setParentController(this);
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



}
