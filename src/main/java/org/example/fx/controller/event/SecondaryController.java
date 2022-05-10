package org.example.fx.controller.event;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.App;
import org.example.fx.modelBDD.Impl.PlayerManagerImpl;
import org.example.fx.modelBDD.dao.Player;
import org.example.fx.modelBDD.main.MySQLConnector;

public class SecondaryController implements Initializable {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private TableView tabla;

    @FXML
    private TableColumn column;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        column.setCellValueFactory(
                new PropertyValueFactory<Player, String>("nombre"));

         final ObservableList<Player> data =
                FXCollections.observableArrayList(
                        new Player(2, "Smith", 12),
                        new Player(3, "Johnson", 0)
                );
        tabla.setItems(data);
        tabla.getColumns().addAll(column);

        try (Connection con = new MySQLConnector().getMySQLConnection()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}