package org.example.fx.event;

import java.io.IOException;
import javafx.fxml.FXML;
import org.example.fx.App;

public class SecondaryController {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}