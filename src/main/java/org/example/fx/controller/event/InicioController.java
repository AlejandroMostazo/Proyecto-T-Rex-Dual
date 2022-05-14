package org.example.fx.controller.event;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import org.example.fx.App;
import org.example.fx.modelBDD.dao.Player;
import org.example.fx.modelBDD.main.MySQLConnector;
import org.example.fx.services.InicioService;

public class InicioController implements Initializable, EventListener {


    @FXML
    private TextField text;

    InicioService service = new InicioService();
    @Getter
    @Setter
    static int idJugador;


    public int conseguirID() {
            try {
                int[] i = Stream.of(service.buscarJugadorByName(text.getText())).mapToInt(Player::getId).toArray();
                return i[0];
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }




    @FXML
    private PasswordField password;

    @FXML
    private Text error;

    @FXML
    private Text noExiste;

    @FXML
    private Text existe;

    public void crearJugador () {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            if (service.buscarJugadorByName(text.getText()) == null && !(text.getText().equals("")) && !(password.getText().equals(""))) {
                service.insertarJugador(text.getText(), password.getText());
                System.out.println(service.buscarJugadores());
                setIdJugador(conseguirID());
                System.out.println(getIdJugador());
                App.setRoot("primary");
            } else {
                existe.setVisible(true);
                noExiste.setVisible(false);
                error.setVisible(true);
                text.clear();
                password.clear();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void iniciarSesion () {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            if (service.buscarJugadorByName(text.getText()) != null) {
                String nombreJugador = Stream.of(service.buscarJugadorByName(text.getText())).map(Player::getName).collect(Collectors.joining());
                String contraseñaJugador = Stream.of(service.buscarJugadorByName(text.getText())).map(Player::getContraseña).collect(Collectors.joining());

                if (text.getText().toLowerCase(Locale.ROOT).equals(nombreJugador.toLowerCase(Locale.ROOT)) && password.getText().equals(contraseñaJugador)) {
                    System.out.println(service.buscarJugadores());
                    setIdJugador(conseguirID());
                    System.out.println(getIdJugador());
                    App.setRoot("primary");
                }
            }

            error.setVisible(true);
            noExiste.setVisible(true);
            existe.setVisible(false);
            text.clear();
            password.clear();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
