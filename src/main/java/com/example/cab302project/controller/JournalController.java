package com.example.cab302project.controller;


import com.example.cab302project.model.Journal;
import com.example.cab302project.model.SqliteJournalDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class JournalController implements IController {
    private SqliteJournalDAO connection = new SqliteJournalDAO();

    @FXML
    private Button viewEntry;

    private MainController mainController;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController =  mainController;
        System.out.println(this.mainController);
    }

    @FXML
    public TextArea MoodText;

    @FXML
    public TextArea JournalText;

    @FXML
    private ListView<String> entriesListView;


    public void Submit() {
        String moodString = MoodText.getText();
        String entryString = JournalText.getText();
        String userName = mainController.getUsername();
        Journal newJournal = new Journal(entryString, moodString, userName);
        connection.addEntry(newJournal);
    }

    @FXML
    public void viewEntry() {
        String userName = mainController.getUsername();

        ObservableList<String> entries = FXCollections.observableArrayList(connection.getAllEntries(userName));
        entriesListView.setItems(entries);
    }
}
