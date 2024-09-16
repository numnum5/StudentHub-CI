package com.example.cab302project.controller;

public class ProjectListController implements IController{

    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
