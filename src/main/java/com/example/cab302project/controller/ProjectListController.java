package com.example.cab302project.controller;
import com.example.cab302project.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
/**
 * Controller for handling the project list page.
 * This class manages the display of assignment items, creation of new assignments, and addition of new subjects.
 */
public class ProjectListController {

    private SubjectManager subjectDAO;
    private SqliteAssignmentDAO assignmentDAO;

    /**
     * Constructor initializes the DAO instances for managing subjects and assignments.
     */
    public ProjectListController() {
        subjectDAO = new SubjectManager(new SqliteSubjectDAO());
        assignmentDAO = new SqliteAssignmentDAO();
    }

    // FXML UI components

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

    /**
     * Renders assignment cards in the project list.
     *
     * @param assignments List of assignments to display as cards.
     */
    private void renderCards(List<Assignment> assignments) {
        cardContainer.getChildren().clear();
        for (Assignment assignment : assignments) {
            VBox card = createCard(assignment);
            cardContainer.getChildren().add(card);
        }
    }

    /**
     * Initialization method to load assignments for the current user.
     */
    @FXML
    private void initialize() {
        List<Assignment> assignments = assignmentDAO.getAllAssignments(LoginController.username);
        renderCards(assignments);
    }

    /**
     * Handles the search functionality for searching assignments based on user input.
     *
     * @throws IOException If an error occurs while searching for assignments.
     */
    @FXML
    private void onSearch() throws IOException {
        List<Assignment> result = assignmentDAO.searchAssignments(searchField.getText());
        renderCards(result);
    }

    /**
     * Creates a card representation for an assignment to be appended to the FXML page.
     *
     * @param assignment The assignment to be represented in a card.
     * @return A VBox representing the assignment card.
     */
    private VBox createCard(Assignment assignment) {
        VBox newCard = new VBox();
        newCard.getStyleClass().add("card");

        Label titleLabel = new Label(assignment.getSubject().getUnitCode() + " " + assignment.getSubject().getName());
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
        setStatusLabelStyle(statusLabel, assignment);

        newCard.getChildren().addAll(titleLabel, semesterLabel, assignmentLabel, descriptionLabel, dueDateLabel, statusLabel);
        return newCard;
    }

    /**
     * Sets the style of the status label based on the assignment's status.
     *
     * @param statusLabel The label to be styled based on the assignment status.
     * @param assignment The assignment whose status is used for styling.
     */
    private void setStatusLabelStyle(Label statusLabel, Assignment assignment) {
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
    }

    /**
     * Adds a new assignment and renders it in the FXML page.
     *
     * @param assignment The assignment to be added and displayed.
     */
    public void addNewAssignment(Assignment assignment) {
        VBox newCard = createCard(assignment);
        cardContainer.getChildren().add(newCard);
        assignmentDAO.addAssignment(assignment);
    }

    /**
     * Opens a new window for creating a new assignment.
     *
     * @throws IOException If an error occurs while loading the new assignment view.
     */
    @FXML
    private void onNewAssignmentClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/create-new-assignment.fxml"));
        Parent root = loader.load();
        CreateAssignmentController controller = loader.getController();
        controller.setParentController(this);
        Stage newStage = new Stage();
        newStage.setTitle("New Assignment");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    /**
     * Opens a new window for creating a new subject.
     *
     * @throws IOException If an error occurs while loading the new subject view.
     */
    @FXML
    private void onAddNewSubjectClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302project/add-new-subject.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle("New Subject");
        newStage.setScene(new Scene(root));
        newStage.show();
    }
}

