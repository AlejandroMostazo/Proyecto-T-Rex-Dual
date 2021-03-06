package org.example.fx.cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.example.fx.cliente.dto.Score;


public class ClientePrimary {

    private final WebTarget webTarget;

    public ClientePrimary() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/webservice/api/");
    }

    public void insertarPuntos(int puntuacion, int idplayer) {
        webTarget.path("game/score")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Score(puntuacion, idplayer),MediaType.APPLICATION_JSON));
    }

    public String ping() {

        return webTarget.path("notifications/ping")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}