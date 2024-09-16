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

public class FinanceController implements IController{

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

    @Override
    public void setMainController(MainController mainController)
    {

    }

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
//        int numWeeks = 5;
//        int daysPerWeek = 7;
//        //GridPane gridPane = new GridPane();
//        VBox vBox = new VBox();
//        HBox hBox = new HBox();
//        hBox.getChildren().add(new Label("Monday"));
//        hBox.getChildren().add(new Label("Tuesday"));
//        hBox.getChildren().add(new Label("Wednesday"));
//        hBox.getChildren().add(new Label("Thursday"));
//        hBox.getChildren().add(new Label("Friday"));
//        hBox.getChildren().add(new Label("Saturday"));
//        hBox.getChildren().add(new Label("Sunday"));
//        vBox.getChildren().add(hBox);
//        /*for (int week = 0; week < numWeeks; week++) {
//            HBox weekBox = new HBox();
//            for (int day = 0; day < daysPerWeek; day++) {
//                TextField dayField = new TextField();
//                dayField.setPromptText("Day " + (day + 1));
//                weekBox.getChildren().add(dayField);
//            }
//            gridPane.getChildren().add(weekBox);
//        }*/
//        anchor.getChildren().add(vBox);
    }


}
