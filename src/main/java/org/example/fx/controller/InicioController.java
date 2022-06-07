package org.example.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.SneakyThrows;
import org.example.fx.App;
import org.example.fx.controller.exeption.UserNotValidException;

import org.example.fx.email.Sender;
import org.example.fx.services.InicioService;


public class InicioController implements Initializable, EventListener {

    @FXML
    private ImageView imagen1;

    @FXML
    private ImageView imagen2;

    @FXML
    private Text singin;

    @FXML
    private TextField text = new TextField();

    @FXML
    private TextField email;

    @FXML
    private Button botonStart;

    @FXML
    private Button botonCrear;

    private InicioService service;


    @FXML
    private PasswordField password;

    @FXML
    private Text textemail;

    @FXML
    private Text error;

    @FXML
    private Text noExiste;

    @FXML
    private Text existe;

    private int conseguirID() {
        try {
            return service.buscarJugadorByName(text.getText()).getId();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("No se puede obtener el id del usuario");
            throw new RuntimeException(e);
        }
    }


    public void notificaciones () throws IOException {
        new Sender().send("alejandro.mostazo.fp@iescampanillas.com", email.getText(), "Tu cuenta ha sido creada con éxito",
                "<b>Ahora podrás saltar todos los cactus que quieras iniciando sesión con el nombre de "+text.getText()+" y la contraseña " +
                        "que hayas indicao anteroirmente. </br>" +
                        "¡Diviertete! :D <b>");
        App.setRoot("primary");
    }

    public void crearJugador () {
        try {
            UserNotValidException.printMenssage(text.getText(), password.getText());
            if (!(text.getText().equals("")) && !(password.getText().equals("")) && service.buscarJugadorByName(text.getText()) == null) {
                service.insertarJugador(text.getText(), password.getText());
               App.setIdJugador(conseguirID());
                email.setVisible(true);
                textemail.setVisible(true);
                text.setVisible(false);
                password.setVisible(false);
                botonCrear.setVisible(false);
                botonStart.setVisible(false);
                singin.setVisible(false);
                imagen1.setVisible(false);
                imagen2.setVisible(false);
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
        } catch (SQLException | ClassNotFoundException e) {
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

    }
}
