module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires jakarta.ws.rs;
    requires jakarta.xml.bind;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx;
    exports org.example.fx.controller;


    opens org.example.fx.controller to javafx.fxml;


    exports org.example.fx.cliente.dto;

}