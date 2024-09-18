package com.example.cab302project.controller;

import com.example.cab302project.model.Assignment;
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
    /**
     * Synchronizes the contacts list view with the contacts in the database.
     */
//    @FXML
//    private void onAdd() {
//        // Default values for a new contact
//        final String DEFAULT_FIRST_NAME = "New";
//        final String DEFAULT_LAST_NAME = "Contact";
//        final String DEFAULT_EMAIL = "";
//        final String DEFAULT_PHONE = "";
////        Assignment newAssignment = new Assignment();
//        // Add the new contact to the database
//        assignmentDAO.addUser(newAssignment);
//        syncContacts();
//        // Select the new contact in the list view
//        // and focus the first name text field
//        selectContact(newContact);
////        firstNameTextField.requestFocus();
//    }

    private void syncProjects() {
        List<Assignment> projects = assignmentDAO.getAllUsers();
        renderCards(projects); // Render the projects into the UI
    }
    private void renderCards(List<Assignment> projects) {
        cardContainer.getChildren().clear(); // Clear the existing cards

        for (Assignment project : projects) {
            VBox card = new VBox();
            Label title = new Label(project.getName());
            Label dueDate = new Label("Due: " + project.getDueDate());
            Label description = new Label(project.getDescription());
            card.getChildren().addAll(title, dueDate, description);

//            card.setOnMouseClicked(event -> selectProject(project));
            cardContainer.getChildren().add(card);
        }
    }

    public void addNewAssignment(Assignment assignment) {

        System.out.println(assignment.toString());
        // Create a new VBox for the card and add a style class
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

        Label statusLabel = new Label(assignment.getStatus().toString());
// Dynamically change the style based on the assignment status
        switch (assignment.getStatus()) {
            case URGENT:
                statusLabel.setStyle("-fx-background-color: #ff5252; -fx-text-fill: white; " +
                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
                break;
            case PENDING:
                statusLabel.setStyle("-fx-background-color: #ffa500; -fx-text-fill: white; " +
                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
                break;
            case COMPLETED:
                statusLabel.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; " +
                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
                break;
            default:
                statusLabel.setStyle("-fx-background-color: #cccccc; -fx-text-fill: black; " +
                        "-fx-font-size: 12px; -fx-font-weight: bold; " +
                        "-fx-padding: 5px 10px; -fx-background-radius: 10px;");
                break;
        }
        newCard.getChildren().addAll(titleLabel, semesterLabel, assignmentLabel, descriptionLabel, dueDateLabel, statusLabel);

        // Finally, add the new card to the cardContainer
        cardContainer.getChildren().add(newCard);
        assignmentDAO.addUser(assignment);
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
