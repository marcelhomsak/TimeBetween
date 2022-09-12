module com.marcelhomsak.timebetween {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.marcelhomsak.timebetween to javafx.fxml;
    exports com.marcelhomsak.timebetween;
}