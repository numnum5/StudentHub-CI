package com.example.cab302project.controller;

import com.example.cab302project.model.MockFinanceDAO;
import com.example.cab302project.model.SqliteFinanceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;
/**
    * A class to manage all the controls from the finance fxml page such as the text fields and labels.
 */
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

    /**
        * A function for the Calculate button controller and it calculates the budget of daily spending.
     */
    @FXML
    private void calculateButtonPressed() {
        HashMap <String, Float> textFieldMap = sendInfo();

        float budget = mockFinanceDAO.getBudget();
        float sum = 0;
        // Gets elements that are stored in the hashmap and adds them together to give a total sum.
        for (Map.Entry<String, Float> entry : textFieldMap.entrySet()) {
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

    /**
        * A function to send and return data
     */
    private HashMap<String, Float> sendInfo() {
        HashMap<String, Float> textFieldMap = new HashMap<>();
        // Puts all the data into a hashmap where the data will stored and reused
        try {
            float budget = floatHandler(budgetField.getText(), budgetField);
            if (budget < 0) {
                return textFieldMap;
            }

            textFieldMap.put("amountSpentMon", floatHandler(mondayText.getText(), mondayText));
            textFieldMap.put("amountSpentTue", floatHandler(tuesdayText.getText(), tuesdayText));
            textFieldMap.put("amountSpentWed", floatHandler(wednesdayText.getText(), wednesdayText));
            textFieldMap.put("amountSpentThu", floatHandler(thursdayText.getText(), thursdayText));
            textFieldMap.put("amountSpentFri", floatHandler(fridayText.getText(), fridayText));
            textFieldMap.put("amountSpentSat", floatHandler(saturdayText.getText(), saturdayText));
            textFieldMap.put("amountSpentSun", floatHandler(sundayText.getText(), sundayText));

            mockFinanceDAO = new MockFinanceDAO(budget, textFieldMap);
            return textFieldMap;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    /**
        * A function that acts as exception handling to manage non float texts.
        * @param textValue The string value that will be turned into a float.
        * @param c The Textfield that will be altered if there is a catch
     */
    private float floatHandler(String textValue, TextField c) {
        try {
            return Float.parseFloat(textValue);
        }
        catch (NumberFormatException e) {
            c.setText("0");
            return 0;
        }
    }

    /**
        * A function that acts as a controller for the reset button to reset the budget,
        daily spending, and amount left.
     */
    @FXML
    private void resetButton() {
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
