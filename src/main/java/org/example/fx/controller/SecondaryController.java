package org.example.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.example.fx.App;
import org.example.fx.cliente.dto.Join;
import org.example.fx.services.EmailService;
import org.example.fx.services.SecondaryService;

public class SecondaryController implements Initializable {

    private SecondaryService secondaryService;

    @FXML
    private Label textmundial;

    @FXML
    private Button enviar;

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

    public void rankingMundial() {

        textmundial.setVisible(true);
        enviar.setVisible(false);

        tabla.getItems().clear();


        final ObservableList<Join> data;

        try {
            data = FXCollections.observableArrayList(
                    secondaryService.ranking()
            );
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("no se puede mostrar el ranking");
            throw new RuntimeException(e);
        }
        tabla.getItems().addAll(data);
    }


    public void rankingIndividual() {

        textmundial.setVisible(false);
        enviar.setVisible(true);

        tabla.getItems().clear();

        final ObservableList<Join> data;
        try {
            data = FXCollections.observableArrayList(
                    secondaryService.rankingById(App.getIdJugador())
            );
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("No se puede mostrar el ranking individual");
            throw new RuntimeException(e);
        }
        tabla.getItems().addAll(data);
    }

    public void enviarEmail() {
        new EmailService().sendRanking(App.getemailJugador(), App.getIdJugador());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        secondaryService = new SecondaryService();

        TableColumn<Join, Integer> puesto = new TableColumn<>("TOP");
        puesto.setMinWidth(100);
        TableColumn<Join, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(100);
        TableColumn<Join, Integer> Score = new TableColumn<>("Score");
        Score.setMinWidth(100);
        TableColumn<Join, String> fecha = new TableColumn<>("Fecha");
        fecha.setMinWidth(100);

        puesto.setStyle( "-fx-alignment: CENTER;");
        nombre.setStyle( "-fx-alignment: CENTER;");
        Score.setStyle( "-fx-alignment: CENTER;");
        fecha.setStyle( "-fx-alignment: CENTER;");


        puesto.setCellValueFactory(
                new PropertyValueFactory<Join, Integer>("puesto"));
        nombre.setCellValueFactory(
                new PropertyValueFactory<Join, String>("nombre"));

        Score.setCellValueFactory(
                new PropertyValueFactory<Join, Integer>("puntuacion"));

//        fecha.setCellValueFactory(
//                new PropertyValueFactory<Join, LocalDateTime>("fecha"));

        fecha.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

        rankingMundial();

        tabla.getColumns().addAll(puesto, nombre, Score, fecha);



    }
}