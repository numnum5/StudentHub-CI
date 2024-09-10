package com.example.cab302project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static final int WIDTH = 770;
    public static final int HEIGHT = 560;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlRegistration = new FXMLLoader(Application.class.getResource("register-ui.fxml"));
        Scene scene = new Scene(fxmlRegistration.load(), WIDTH, HEIGHT);
        stage.setTitle("StudentHub");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}