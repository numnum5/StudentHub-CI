package com.example.cab302project.controller;

import com.example.cab302project.model.MockFinanceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

// FinanceController class is class to manage all the controls from the finance fxml page
public class FinanceController {

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
    private Button resetButton;
    @FXML
    private GridPane calendar;
    @FXML
    private AnchorPane anchor;

    // Text fields for user to write their daily spending
    @FXML
    private TextField mondayText;
    @FXML
    private TextField tuesdayText;
    @FXML
    private TextField wednesdayText;
    @FXML
    private TextField thursdayText;
    @FXML
    private TextField fridayText;
    @FXML
    private TextField saturdayText;
    @FXML
    private TextField sundayText;

    MockFinanceDAO financeData;

    // calculate button controller and it calculates the budget of daily spending
    @FXML
    private void calculateButtonPressed()
    {
        HashMap <String, Float> textFieldMap = sendInfo();

        float budget = financeData.getBudget();
        float sum = 0;

        for (Map.Entry<String, Float> entry : textFieldMap.entrySet())
        {
           sum += entry.getValue();
        }

        float leftOver = budget - sum;
        budgetLeft.setText(String.valueOf(leftOver));

        if (leftOver > 0) {
            budgetLeft.setTextFill(Color.GREEN);
        } else if (leftOver == 0) {
            budgetLeft.setTextFill(Color.YELLOW);
        } else {
            budgetLeft.setTextFill(Color.RED);
        }

    }       


    private HashMap<String, Float> sendInfo()
    {
        HashMap<String, Float> textFieldMap = new HashMap<>();
        try
        {
            float budget = Float.parseFloat(budgetField.getText());
            if (budget <= 0)
            {
                errorMessage.setOpacity(1);
                return textFieldMap;
            }
            errorMessage.setOpacity(0);

            textFieldMap.put("amountSpentMon", floatHandler(mondayText.getText()));
            textFieldMap.put("amountSpentTue", floatHandler(tuesdayText.getText()));
            textFieldMap.put("amountSpentWed", floatHandler(wednesdayText.getText()));
            textFieldMap.put("amountSpentThu", floatHandler(thursdayText.getText()));
            textFieldMap.put("amountSpentFri", floatHandler(fridayText.getText()));
            textFieldMap.put("amountSpentSat", floatHandler(saturdayText.getText()));
            textFieldMap.put("amountSpentSun", floatHandler(sundayText.getText()));

            financeData = new MockFinanceDAO(budget, textFieldMap);
            return textFieldMap;
        }
        catch (NumberFormatException e)
        {
            errorMessage.setOpacity(1);
            return null;
        }
    }

    private float floatHandler(String textValue)
    {
        try
        {
            return Float.parseFloat(textValue);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    // controller for the reset button to reset the budget and daily spending
    @FXML
    private void resetButton()
    {

    }


}
