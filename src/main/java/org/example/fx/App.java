package org.example.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static int idJugador = 0;

    public static String emailJugador = "";

    private static Scene scene;

    public static int getIdJugador() {
        return idJugador;
    }


    public static void setIdJugador(int idJugador) {
        App.idJugador = idJugador;
    }

    public static void setemailJugador(String emailJugador) {
        App.emailJugador = emailJugador;
    }

    public static String getemailJugador() {
        return emailJugador;
    }


    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("inicio"));
        stage.setTitle("Juego T-rex");
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/static/dino.png")));
        stage.setScene(scene);
        stage.show();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}