package com.example.cab302project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Main entry point of the application
 */
public class Application extends javafx.application.Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlRegistration = new FXMLLoader(Application.class.getResource("login-ui.fxml"));
        Scene scene = new Scene(fxmlRegistration.load(), WIDTH, HEIGHT);
        stage.setTitle("StudentHub");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}