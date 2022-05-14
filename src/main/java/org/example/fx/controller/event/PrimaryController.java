package org.example.fx.controller.event;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.example.fx.App;
import org.example.fx.services.PrimaryService;

public class PrimaryController implements Initializable, EventListener {

    @FXML
    private ImageView trex;

    @FXML
    private ImageView cactuc;

    @FXML
    private ImageView cactuc1;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text score;

    @FXML
    private Button restart;

    @FXML
    private Button ranking;

    @FXML
    private Button cerrarSesion;

    private TranslateTransition translaterex = new TranslateTransition();

    private TranslateTransition translatecactus = new TranslateTransition();

    @FXML
    public void switchToSecondary() throws IOException {
            App.setRoot("secondary");
   }

    @FXML
    public void switchToInicio() throws IOException {
        App.setRoot("inicio");
    }

    public void salto () {
        //if (translaterex.getStatus() == Animation.Status.STOPPED && !gameOver()) {
        if (trex.getTranslateY() == 256 && !gameOver()) {
            translaterex.setNode(trex);
            translaterex.setDuration(Duration.millis(300));
            translaterex.setCycleCount(2);
            translaterex.setAutoReverse(true);
            translaterex.setInterpolator(Interpolator.TANGENT(Duration.millis(100), 160, Duration.millis(100), 160));
            translaterex.setByY(-160);
            translaterex.play();
        }
    }

    public boolean gameOver () {
        if ( ((cactuc.getTranslateX() < trex.getTranslateX()+50 && cactuc.getTranslateX() > trex.getTranslateX()) || (cactuc1.getTranslateX() < trex.getTranslateX()+50 && cactuc1.getTranslateX() > trex.getTranslateX())) && trex.getTranslateY() >= 165 ) {
            animationTimer.stop();
            translaterex.stop();
            restart.setStyle("-fx-opacity: 1;");
            ranking.setStyle("-fx-opacity: 1;");
            cerrarSesion.setStyle("-fx-opacity: 1;");
            PrimaryService primaryService = new PrimaryService();
            System.out.println(LocalDateTime.now());
            primaryService.isertarPuntuacion(puntuacion, LocalDateTime.now(), new InicioController().getIdJugador());
            return true;
        }  return false;
    }

    public void restar() throws IOException {
        App.setRoot("primary");
    }

    int puntuacion = 0;

        final long startNanoTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                    puntuacion = (int)(t*t);
                    score.setText("Score: "+puntuacion);
                    cactuc.setTranslateX(cactuc.getTranslateX() - (t/5 + 7));
                    cactuc1.setTranslateX(cactuc1.getTranslateX() - (t/5 + 7));
                if (cactuc.getTranslateX() < -50) {
                    cactuc.setTranslateX(cactuc1.getTranslateX() + Math.random()*(900-854)+854);
                }
                if (cactuc1.getTranslateX() < -50) {
                    cactuc1.setTranslateX(cactuc.getTranslateX() + Math.random()*(850-270)+270);
                }
                gameOver();
            }
        };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        animationTimer.start();

        anchorPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getText().matches("w") || keyEvent.getText().matches("W"))
                    salto();
                    System.out.println(keyEvent.getCode());
            }
        });

    }
}
