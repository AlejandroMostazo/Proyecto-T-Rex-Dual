package org.example.fx.services;

import org.example.fx.cliente.ClientePrimary;


public class PrimaryService {

    public void insertarPuntuacion(int puntuacion, int idplayer) {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//            new ScoreManagerImpl().Insert(con, puntuacion, fecha, idplayer);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        new ClientePrimary().insertarPuntos(puntuacion, idplayer);
    }
}
