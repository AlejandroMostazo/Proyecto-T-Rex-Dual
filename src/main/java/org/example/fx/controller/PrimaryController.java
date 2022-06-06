package org.example.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.EventListener;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.fx.App;
import org.example.fx.controller.exeption.KeyNotValidException;
import org.example.fx.services.InicioService;
import org.example.fx.services.PrimaryService;
import org.example.fx.services.SecondaryService;

public class PrimaryController implements Initializable, EventListener {


    private PrimaryService primaryService;

    @FXML
    private ImageView trex;

    @FXML
    private ImageView cactuc;

    @FXML
    private ImageView cactuc1;

    @FXML
    private ImageView nube;

    @FXML
    private ImageView suelo;
    @FXML
    private ImageView suelo2;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text score;

    @FXML
    private Text uvedoble;

    @FXML
    private Button restart;

    @FXML
    private Button ranking;

    @FXML
    private Button cerrarSesion;

    private TranslateTransition translaterex;


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
            translaterex.setDuration(Duration.millis(320));
            translaterex.setCycleCount(2);
            translaterex.setAutoReverse(true);
            translaterex.setInterpolator(Interpolator.TANGENT(Duration.millis(100), 160, Duration.millis(100), 160));
            translaterex.setByY(-200);
            translaterex.play();
        }
    }


    public boolean gameOver () {
        if ((isCactusInRange() || isCactus2InRange()) && trex.getTranslateY() >= 165) {
            primaryService.insertarPuntuacion(puntuacion, App.getIdJugador());
            animationTimer.stop();
            translaterex.stop();
            restart.setStyle("-fx-opacity: 1;");
            ranking.setStyle("-fx-opacity: 1;");
            cerrarSesion.setStyle("-fx-opacity: 1;");
            System.out.println(App.getIdJugador()+" "+puntuacion);
            return true;
        }  return false;
    }

    private boolean isCactus2InRange() {
        return cactuc1.getTranslateX() < trex.getTranslateX() + 50 && cactuc1.getTranslateX() > trex.getTranslateX();
    }

    private boolean isCactusInRange() {
        return cactuc.getTranslateX() < trex.getTranslateX() + 50 && cactuc.getTranslateX() > trex.getTranslateX();
    }

    public void restart() throws IOException {
        App.setRoot("primary");
    }

    int puntuacion = 0;

        final long startNanoTime = System.nanoTime();
        AnimationTimer animationTimer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                    puntuacion = (int)(t*t);
                    score.setText("Score: "+puntuacion);
                    nube.setTranslateX(nube.getTranslateX() - 4);
                    cactuc.setTranslateX(cactuc.getTranslateX() - (t/5 + 7));
                    cactuc1.setTranslateX(cactuc1.getTranslateX() - (t/5 + 7));
                    suelo.setTranslateX(suelo.getTranslateX() - (t/5 + 7));
                    suelo2.setTranslateX(suelo2.getTranslateX() - (t/5 + 7));
                if (cactuc.getTranslateX() < -50) {
                    cactuc.setTranslateX(cactuc1.getTranslateX() + Math.random()*(900-854)+854);
                }
                if (cactuc1.getTranslateX() < -50) {
                    cactuc1.setTranslateX(cactuc.getTranslateX() + Math.random()*(850-270)+270);
                }
                if (nube.getTranslateX() < -250) {
                    nube.setTranslateX(850);
                    nube.setTranslateY(Math.random()*125);
                }
                if (suelo.getTranslateX() < -suelo.getFitWidth()) {
                    suelo.setTranslateX(suelo2.getTranslateX()+suelo2.getFitWidth());
                }
                if (suelo2.getTranslateX() < -suelo2.getFitWidth()) {
                    suelo2.setTranslateX(suelo.getTranslateX()+suelo.getFitWidth()-60);
                }
                gameOver();
            }
        };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        primaryService = new PrimaryService();

        translaterex = new TranslateTransition();

        animationTimer.start();

        anchorPane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getText().matches("w|W")) {
                salto();
                System.out.println(keyEvent.getCode());
                uvedoble.setVisible(false);
            } else {
                try {
                    throw new KeyNotValidException("Tecla equivocada");
                } catch (KeyNotValidException e) {
                    uvedoble.setVisible(true);
                    e.printStackTrace();
                }
            }
            });
        }
}
