package com.example.cab302project.controller;

import com.example.cab302project.model.MockFinanceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

// FinanceController is a class to manage all the controls from the finance fxml page
public class FinanceController {

    @FXML
    private TextField budgetField;
    @FXML
    private Label budgetLeft;
    @FXML
    public Label overBudgetText;

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


    MockFinanceDAO mockFinanceDAO;

    // Calculate button controller and it calculates the budget of daily spending
    @FXML
    private void calculateButtonPressed()
    {
        HashMap <String, Float> textFieldMap = sendInfo();

        float budget = mockFinanceDAO.getBudget();
        float sum = 0;

        for (Map.Entry<String, Float> entry : textFieldMap.entrySet())
        {
            if (!entry.getKey().equals("budget"))
                sum += entry.getValue();
        }

        float leftOver = budget - sum;
        budgetLeft.setText(Float.toString(leftOver));

        if (leftOver > 0) {
            overBudgetText.setOpacity(0);
            budgetLeft.setTextFill(Color.GREEN);
        } else if (leftOver == 0) {
            overBudgetText.setOpacity(0);
            budgetLeft.setTextFill(Color.YELLOW);
        } else {
            overBudgetText.setOpacity(1);
            budgetLeft.setTextFill(Color.RED);
        }

    }       

    // Function to send and return data
    private HashMap<String, Float> sendInfo()
    {
        HashMap<String, Float> textFieldMap = new HashMap<>();
        try
        {
            float budget = floatHandler(budgetField.getText());
            if (budget < 0)
            {
                return textFieldMap;
            }

            textFieldMap.put("amountSpentMon", floatHandler(mondayText.getText()));
            textFieldMap.put("amountSpentTue", floatHandler(tuesdayText.getText()));
            textFieldMap.put("amountSpentWed", floatHandler(wednesdayText.getText()));
            textFieldMap.put("amountSpentThu", floatHandler(thursdayText.getText()));
            textFieldMap.put("amountSpentFri", floatHandler(fridayText.getText()));
            textFieldMap.put("amountSpentSat", floatHandler(saturdayText.getText()));
            textFieldMap.put("amountSpentSun", floatHandler(sundayText.getText()));

            mockFinanceDAO = new MockFinanceDAO(budget, textFieldMap);
            return textFieldMap;
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    // Acts as exception handling to manage non float texts
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

    // Controller for the reset button to reset the budget, daily spending, and amount left
    @FXML
    private void resetButton()
    {
        budgetField.clear();
        mondayText.clear();
        tuesdayText.clear();
        wednesdayText.clear();
        thursdayText.clear();
        fridayText.clear();
        saturdayText.clear();
        sundayText.clear();
        overBudgetText.setOpacity(0);
        budgetLeft.setText("0");
        budgetLeft.setTextFill(Color.YELLOW);
    }


}
