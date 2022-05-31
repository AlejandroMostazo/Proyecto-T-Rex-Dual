package org.example.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.SneakyThrows;
import org.example.fx.App;
import org.example.fx.controller.exeption.ConnectionException;
import org.example.fx.controller.exeption.UserNotValidException;

import org.example.fx.modelBDD.dao.Player;
import org.example.fx.modelBDD.main.MySQLConnector;
import org.example.fx.services.InicioService;


public class InicioController implements Initializable, EventListener {

    @FXML
    private TextField text;

    private InicioService service;


    @FXML
    private PasswordField password;

    @FXML
    private Text error;

    @FXML
    private Text noExiste;

    @FXML
    private Text existe;

    private int conseguirID() {
        try {
            return Stream.of(service.buscarJugadorByName(text.getText())).mapToInt(Player::getId).findFirst().getAsInt();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearJugador () {
        try {
            if (!(text.getText().equals("")) && !(password.getText().equals("")) && service.buscarJugadorByName(text.getText()) == null) {
                service.insertarJugador(text.getText(), password.getText());
                App.setIdJugador(conseguirID());
                App.setRoot("primary");
            } else {
                throw new UserNotValidException("Ususario ya existente");
            }
        }catch (UserNotValidException e) {
            existe.setVisible(true);
            noExiste.setVisible(false);
            error.setVisible(true);
            text.clear();
            password.clear();
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void iniciarSesion () {
        try {
            if (service.validarJugador(text.getText(), password.getText())) {
                App.setIdJugador(conseguirID());
                App.setRoot("primary");
            } else {
                throw new UserNotValidException("Usuario o contraseña invalidos");
            }
        } catch (UserNotValidException e) {
            error.setVisible(true);
            noExiste.setVisible(true);
            existe.setVisible(false);
            text.clear();
            password.clear();
            e.printStackTrace();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        service = new InicioService();

        try {
            new MySQLConnector().getMySQLConnection();
        } catch (ClassNotFoundException | SQLException e) {
            error.setVisible(true);
            throw new ConnectionException("No se puede conectar a la base de datos");
        }
    }
}
