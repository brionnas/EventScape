module com.event {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.event to javafx.fxml;
    exports com.event;

    opens com.controllers to javafx.fxml;
}
