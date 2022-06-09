package org.example.fx.cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.example.fx.cliente.dto.Email;

public class ClienteEmail {

    private final WebTarget webTarget;

    public ClienteEmail() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/webservice/api/");
    }

    public void enviarEmail(String email, String nombre) {
        webTarget.path("email")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Email(email, nombre),MediaType.APPLICATION_JSON));
    }

    public void enviarRanking(String email, int id) {
        webTarget.path("email/sendRanking")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Email(email, id),MediaType.APPLICATION_JSON));
    }
}