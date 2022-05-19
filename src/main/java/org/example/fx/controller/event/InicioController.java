package org.example.fx.controller.event;

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
import lombok.Getter;
import lombok.Setter;
import org.example.fx.App;
import org.example.fx.controller.event.exeption.UserNotValidExeption;
import org.example.fx.modelBDD.dao.Player;
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
        try {
            if (!(text.getText().equals("")) && !(password.getText().equals("")) && service.buscarJugadorByName(text.getText()) == null) {
                service.insertarJugador(text.getText(), password.getText());
                System.out.println(service.buscarJugadores());
                setIdJugador(conseguirID());
                System.out.println(getIdJugador());
                App.setRoot("primary");
            } else {
                throw new UserNotValidExeption("Ususario ya existente");
            }
        }catch (UserNotValidExeption e) {
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
                System.out.println(service.buscarJugadores());
                setIdJugador(conseguirID());
                System.out.println(getIdJugador());
                App.setRoot("primary");
            } else {
                throw new UserNotValidExeption("Usuario o contraseña invalidos");
            }
        } catch (UserNotValidExeption e) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
