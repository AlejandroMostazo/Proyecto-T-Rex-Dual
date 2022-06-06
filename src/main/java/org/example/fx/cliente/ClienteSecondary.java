package org.example.fx.cliente;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import org.example.fx.modelBDD.dao.Join;

import java.util.ArrayList;
import java.util.List;

public class ClienteSecondary {

    private final WebTarget webTarget;

    public ClienteSecondary() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/webservice/api/");
    }

    public List<Join> ranking() {
        List<Join> machines = webTarget.path("table/ranking")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Join>>() {
        });
        List<Join> hostnames = new ArrayList<>(machines.size());
        for (Join machine : machines) {
            hostnames.add(machine);
        }
        return hostnames;
    }

    public List<Join> rankingId(int id) {
        return webTarget.path("table/rankingId/"+id)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Join>>(){});
    }

    public String ping() {

        return webTarget.path("notifications/ping")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}