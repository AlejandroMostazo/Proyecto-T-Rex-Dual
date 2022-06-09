package org.example.fx.cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.example.fx.cliente.dto.Player;

public class ClienteIncio {

    private final WebTarget webTarget;

    public ClienteIncio() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/webservice/api/");
    }

    public void insertarPlayer(String nombre, String contraseña, String email) {
        webTarget.path("inicio")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Player(nombre, contraseña, email),MediaType.APPLICATION_JSON));
    }

    public boolean checkPlayer(String nombre, String contraseña) {
        return webTarget.path("inicio/validar")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Player(nombre, contraseña), MediaType.APPLICATION_JSON), boolean.class);
    }

    public Player searchPlayerByName(String nombre) {

        return webTarget.path("inicio/search/"+nombre)
                .request(MediaType.APPLICATION_JSON)
                .get(Player.class);
    }

    public String ping() {

        return webTarget.path("notifications/ping")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}