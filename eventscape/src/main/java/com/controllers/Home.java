package com.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.event.App;

public class Home {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}