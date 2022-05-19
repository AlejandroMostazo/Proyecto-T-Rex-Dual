module org.example.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;

    opens org.example.fx to javafx.fxml;
    exports org.example.fx;
    exports org.example.fx.controller.event;
    opens org.example.fx.controller.event to javafx.fxml;

    exports org.example.fx.modelBDD.dao;
    opens org.example.fx.modelBDD.dao to javafx.fxml;

    exports org.example.fx.modelBDD.Impl;
}