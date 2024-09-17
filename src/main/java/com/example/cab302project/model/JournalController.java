package com.example.cab302project.model;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;

public class JournalController
{
    @FXML
    public TextArea MoodText;

    public void Submit()
    {
        System.out.println(MoodText.getText());
    }
}
