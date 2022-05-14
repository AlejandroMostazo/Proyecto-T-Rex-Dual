package org.example.fx.controller.event;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import org.example.fx.modelBDD.dao.Join;
import org.example.fx.services.SecondaryService;

public class SecondaryController implements Initializable {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void switchToInicio() throws IOException {
        App.setRoot("inicio");
    }

    @FXML
    private TableView tabla;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<Join, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(100);
        TableColumn<Join, Integer> Score = new TableColumn<>("Score");
        Score.setMinWidth(100);
        TableColumn<Join, Date> fecha = new TableColumn<>("Fecha");
        fecha.setMinWidth(100);


        nombre.setCellValueFactory(
                new PropertyValueFactory<Join, String>("nombre"));

        Score.setCellValueFactory(
                new PropertyValueFactory<Join, Integer>("puntuacion"));

        fecha.setCellValueFactory(
                new PropertyValueFactory<>("fecha"));


        SecondaryService service = new SecondaryService();

        final ObservableList<Join> data;
        try {
            data = FXCollections.observableArrayList(
                    service.ranking()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        tabla.getItems().addAll(data);
        tabla.getColumns().addAll(nombre, Score, fecha);


    }
}