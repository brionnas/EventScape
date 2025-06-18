module com.event {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires junit;
    requires javafx.graphics; 

    opens com.event to javafx.fxml;
    exports com.event;
    exports com.model;

    opens com.model to javafx.fxml;

    opens com.controllers to javafx.fxml;
}
