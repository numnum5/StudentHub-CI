package com.example.cab302project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Text registerTitle;

    @FXML
    private Button submitButton;

    @FXML
    private void Submit()
    {
        System.out.println("test");
    }

}