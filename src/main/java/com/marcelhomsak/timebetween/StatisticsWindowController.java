package com.marcelhomsak.timebetween;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StatisticsWindowController implements Initializable {

    /* ----------------------------- */
    @FXML
    private Label labelWorstTime;
    @FXML
    private Label labelBestTime;
    @FXML
    private Label labelAverageTime;
    @FXML
    private Label labelWorstTimeFTTT;
    @FXML
    private Label labelBestTimeFTTT;
    @FXML
    private Label labelWorstTimeDate;
    @FXML
    private Label labelBestTimeDate;
    @FXML
    private AnchorPane anchorPane;
    /* ----------------------------- */

    ArrayList<Time> times;

    // INITIALIZE
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // On window opening read file, make Time objects, load them into ArrayList<Time>,
        // sort ArrayList (implementation of comparable in Time class (compare difference seconds))
        // set labels

        times = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(System.getProperty("user.home") + File.separator + ".timebetween" + File.separator + "times.txt"))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                times.add(new Time(parts[0], parts[1], parts[2]));
            }

            Collections.sort(times);
            Time bestTime = times.get(0);
            Time worstTime = times.get(times.size() - 1);

            int sum = 0;
            for (Time t : times) {
                sum += t.getDifferenceInSeconds();
            }
            int avgTimeInSeconds = sum / times.size();

            labelWorstTimeFTTT.setText(worstTime.getRepresentation());
            labelWorstTime.setText(worstTime.getDifference());
            labelWorstTimeDate.setText(worstTime.getDate());
            labelBestTimeFTTT.setText(bestTime.getRepresentation());
            labelBestTime.setText(bestTime.getDifference());
            labelBestTimeDate.setText(bestTime.getDate());
            labelAverageTime.setText(Time.secondsToTime(avgTimeInSeconds));

        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }
    }


    /* ----------------------------- open new windows from current window ----------------------------- */
    public void openFirstWindow(Event event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("firstWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }
    }

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

    public void openAbout() {
        Alerts.openAbout();
    }
    /* --------------------------------------------------------------------------------------------- */


}
