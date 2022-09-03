package com.marcelhomsak.timebetween;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstWindow extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FirstWindow.class.getResource("firstWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Time Between");
        stage.getIcons().add(new Image(FirstWindow.class.getResourceAsStream("images/icon.png")));
        stage.setScene(scene);
        // window cannot be resizable
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}