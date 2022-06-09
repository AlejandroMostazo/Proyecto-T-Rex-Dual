package org.example.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.stream.Stream;

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

import org.example.fx.services.EmailService;
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

    public String conseguirEmail() {
        try {
            if (service.buscarJugadorByName(text.getText()).getEmail() != null) {
                return service.buscarJugadorByName(text.getText()).getEmail();
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("No se puede obtener el id del usuario");
            throw new RuntimeException(e);
        }
    }


    public void notificaciones () throws IOException {
        if (email.getText() != null) {
            new EmailService().sendEmail(email.getText(), text.getText());
        }
        App.setemailJugador(email.getText());
        service.insertarJugador(text.getText(), password.getText(), email.getText());
        App.setIdJugador(conseguirID());
        App.setRoot("primary");

    }

    public void crearJugador () {
        try {
            UserNotValidException.printMenssage(text.getText(), password.getText());
            if (!(text.getText().equals("")) && !(password.getText().equals("")) && service.buscarJugadorByName(text.getText()) == null) {
                email.setVisible(true);
                textemail.setVisible(true);
                text.setVisible(false);
                password.setVisible(false);
                botonCrear.setVisible(false);
                botonStart.setVisible(false);
                singin.setVisible(false);
                imagen1.setVisible(false);
                imagen2.setVisible(false);
                existe.setVisible(false);
                noExiste.setVisible(false);
                error.setVisible(false);
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
                App.setemailJugador(conseguirEmail());
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
