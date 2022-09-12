package com.marcelhomsak.timebetween;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FirstWindowController {

    /* ---------------------------------------- */
    @FXML
    private Label labelStartingTime;
    @FXML
    private TextField textFieldStartingTime;
    @FXML
    private Label labelEndingTime;
    @FXML
    private TextField textFieldEndingTime;
    @FXML
    private Button buttonCalculate;
    @FXML
    private Label labelOutputDifferenceTime;
    @FXML
    private ImageView imageViewAddTime;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem menuItemTimes;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private ImageView bgImage;

    /* ---------------------------------------- */

    public void calculate() {
        try {
            // first clear
            imageViewAddTime.setVisible(false);
            labelOutputDifferenceTime.setText("");

            Time time = new Time(textFieldStartingTime.getText(), textFieldEndingTime.getText());
            // get difference in xx:xx:xx format
            labelOutputDifferenceTime.setText(time.getDifference());

            // if time difference is valid make button for saving visible
            if (!labelOutputDifferenceTime.getText().equals("")) {
                imageViewAddTime.setVisible(true);
            }

        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }
    }

    public void saveTimeToFile() {
        try {
            // if the file does not exist - create one and append time
            // if the file exists append time in it
            String directoryName = System.getProperty("user.home") + File.separator + ".timebetween";
            File dir = new File(directoryName);
            File file = new File(directoryName + File.separator + "times.txt");
            if (dir.mkdir()) {  // will execute if directory ("user/home/.timebetween") does not exist yet but it will now
                // now we need to create times.txt in this folder
                file.createNewFile();
            } else {  // if this directory exists
                file.createNewFile();
            }
            // no matter what, file should be created now, we just append time to it
            FileWriter fw = new FileWriter(file, true);
            Time time = new Time(textFieldStartingTime.getText(), textFieldEndingTime.getText(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            fw.append(String.format("%s;%s;%s\n", time.getStartingTime(), time.getEndingTime(),
                    time.getDate()));  // HH:MM:SS;HH:MM:SS;YYYY-MM-DD
            fw.close();

            // in the end make TextFields "", outputLabel "" and imageView invisible
            textFieldEndingTime.setText("");
            textFieldStartingTime.setText("");
            labelOutputDifferenceTime.setText("");
            imageViewAddTime.setVisible(false);

            JFXSnackbar snackbar = new JFXSnackbar(anchorPane);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(new JFXSnackbarLayout("New time successfully saved!"), Duration.seconds(2), null));


        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }
    }

    public void openAbout() {
        Alerts.openAbout();
    }


    // switch scenes
    public void openListOfTimesWindow() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("listOfTimesWindow.fxml"));
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }

    }

    public void openStatisticsWindow() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("statisticsWindow.fxml"));
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }

    }


}