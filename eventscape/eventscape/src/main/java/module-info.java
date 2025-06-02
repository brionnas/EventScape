module com.event {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.event to javafx.fxml;
    exports com.event;

    opens com.controllers to javafx.fxml;
}
