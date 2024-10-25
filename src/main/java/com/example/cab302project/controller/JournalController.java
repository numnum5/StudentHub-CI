package com.example.cab302project.controller;


import com.example.cab302project.model.Journal;
import com.example.cab302project.model.SqliteJournalDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/**
 * Handles connection to the database and sets the main controller.
 */
public class JournalController implements IController {
    private SqliteJournalDAO connection = new SqliteJournalDAO();


    private MainController mainController;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }

    /**
     * Handles FXML objects.
     */

    @FXML
    public TextArea MoodText;

    @FXML
    public TextArea JournalText;

    @FXML
    private ListView<String> entriesListView;

    /**
     * Handles journal entry submission by adding it into the database.
     */
    public void Submit() {
        String moodString = MoodText.getText();
        String entryString = JournalText.getText();
        String userName = mainController.getUsername();
        Journal newJournal = new Journal(entryString, moodString, userName);
        connection.addEntry(newJournal);
    }
    /**
     * Handles viewentry click by returning entries from the database to the entry list
     */

    @FXML
    public void viewEntry() {
        String userName = mainController.getUsername();

        ObservableList<String> entries = FXCollections.observableArrayList(connection.getAllEntries(userName));
        entriesListView.setItems(entries);
    }
}
