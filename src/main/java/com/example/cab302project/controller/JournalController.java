package com.example.cab302project.controller;

import com.example.cab302project.Application;
import com.example.cab302project.model.JournalDAO;
import com.example.cab302project.model.MockSubjectDAO;
import com.example.cab302project.model.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class JournalController implements IController
{
    private JournalDAO Connection = new JournalDAO();

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

    public void Submit()
    {
        String MoodString = MoodText.getText();
        String EntryString = JournalText.getText();

        Connection.addEntry(EntryString, MoodString);
    }

    public void viewEntry()
    {
        System.out.println("xte");
    }
}
