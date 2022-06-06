package org.example.fx.cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;


public class ClientePrimary {

    private final WebTarget webTarget;

    public ClientePrimary() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/webservice/api/");
    }

    public void insertarPuntos(int puntuacion, int idplayer) {
        webTarget.path("game/score/"+puntuacion+"/"+idplayer)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity("",MediaType.APPLICATION_JSON));
    }

    public String ping() {

        return webTarget.path("notifications/ping")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}