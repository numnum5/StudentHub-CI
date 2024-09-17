package com.example.cab302project.controller;

import com.example.cab302project.model.FinanceFeatures;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.w3c.dom.Text;

public class FinanceController {

    @FXML
    private Label label;
    @FXML
    private TextField budgetField;
    @FXML
    private SplitMenuButton splitMenu;
    @FXML
    private Label amountLeft;
    @FXML
    private Label budgetLeft;
    @FXML
    private Label errorMessage;
    @FXML
    private Button calculateButton;
    @FXML
    private Button addMoreButton;
    @FXML
    private GridPane calendar;
    @FXML
    private AnchorPane anchor;



    private void calculateBudget()
    {

    }

    @FXML
    private void calculateButtonPressed()
    {
        getBudgetInfo();
    }


    private void getBudgetInfo()
    {
        try
        {
            int getBudget = Integer.parseInt(budgetField.getText());
            if (getBudget <= 0)
            {
                errorMessage.setOpacity(1);
            }
            else
            {
                errorMessage.setOpacity(0);
                FinanceFeatures financeFeatures = new FinanceFeatures(getBudget);
            }
        }
        catch (NumberFormatException e)
        {
            errorMessage.setOpacity(1);
        }
    }

    @FXML
    private void resetButton()
    {

    }


}
