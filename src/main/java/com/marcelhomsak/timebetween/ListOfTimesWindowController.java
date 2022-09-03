package com.marcelhomsak.timebetween;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ListOfTimesWindowController implements Initializable {

    /* ---------------------------------------*/
    @FXML
    private TableView<Time> tableViewTimes;
    @FXML
    private ImageView imageViewHomeButton;
    @FXML
    private TableColumn<Time, String> tableColumnDifference;
    @FXML
    private TableColumn<Time, String> tableColumnDate;
    @FXML
    private TableColumn<Time, String> tableColumnRepresentation;
    @FXML
    private ImageView imageViewRefreshButton;
    @FXML
    private MenuItem menuItemAbout;
    @FXML
    private AnchorPane anchorPane;
    /* ---------------------------------------*/


    public ObservableList<Time> readFromFile() {
        // read line by line from times.txt create new objects of class Time and add them to ObservableList
        ObservableList<Time> times = FXCollections.observableArrayList();
        try (Scanner sc = new Scanner(new File(System.getProperty("user.home") + File.separator + ".timebetween" + File.separator + "times.txt"))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                times.add(new Time(parts[0], parts[1], parts[2]));
            }
        } catch (Exception e) {
            System.out.println("Napaka: " + e);
        }
        return times;
    }

    // refresh - on refresh button clicked, read from file and set table (same as on opening of window)
    public void refreshTableView() {
        ObservableList<Time> times = readFromFile();

        tableColumnRepresentation.setCellValueFactory(new PropertyValueFactory<>("representation"));
        tableColumnDifference.setCellValueFactory(new PropertyValueFactory<>("difference"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableViewTimes.setItems(times);
    }

    // INITIALIZE
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Time> times = readFromFile();

        tableColumnRepresentation.setCellValueFactory(new PropertyValueFactory<>("representation"));
        tableColumnDifference.setCellValueFactory(new PropertyValueFactory<>("difference"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableViewTimes.setItems(times);
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

    public void openAbout() {
        Alerts.openAbout();
    }
    /* --------------------------------------------------------------------------------------------- */

}
