package com.marcelhomsak.timebetween;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Alerts {

    public static void openAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About TimeBetween");
        alert.setHeaderText(About.NAME_AND_VERSION);
        alert.setResizable(false);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(FirstWindow.class.getResourceAsStream("images/icon_small.png")));
        alert.setGraphic(new ImageView(new Image(FirstWindow.class.getResourceAsStream("images/icon_small.png"))));
        alert.setContentText(String.format("Build Name: %s\nVersion: %s\nJava Runtime Version: %s\nCopyright: ©%s %s", About.BUILD_NAME, About.VERSION, About.JAVA, About.COPYRIGHT, About.YEAR));
        alert.show();
    }

}
