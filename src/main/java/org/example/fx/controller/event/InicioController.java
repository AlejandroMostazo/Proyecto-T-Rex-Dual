package org.example.fx.controller.event;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.example.fx.App;
import org.example.fx.modelBDD.Impl.PlayerManagerImpl;
import org.example.fx.modelBDD.main.MySQLConnector;

public class InicioController implements Initializable, EventListener {

    @FXML
    private TextField text;

    @FXML
    public void iniciarJuego() throws IOException {
        App.setRoot("primary");

        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            new PlayerManagerImpl().Insert(con, text.getText());
            System.out.println(new PlayerManagerImpl().findAll(con));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
