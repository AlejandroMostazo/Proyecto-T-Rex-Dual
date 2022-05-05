module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx;
    exports org.example.fx.event;
    opens org.example.fx.event to javafx.fxml;
}