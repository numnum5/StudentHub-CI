package com.example.cab302project.controller;

/**
 * Interface for controllers that need to inherit a parent controller
 * Used for navigation to different pages in the application
 */
public interface IController {
    void setMainController(MainController mainController);
}
