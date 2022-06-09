package org.example.fx.services;

import org.example.fx.cliente.ClienteEmail;
import org.example.fx.controller.InicioController;

public class EmailService {
    public void sendEmail(String email, String nombre) {
        new ClienteEmail().enviarEmail(email, nombre);
    }

    public void sendRanking(String email, int id) {
        new ClienteEmail().enviarRanking(email, id);
    }
}
