package org.example.fx.services;

import org.example.fx.modelBDD.manager.impl.ScoreManagerImpl;
import org.example.fx.modelBDD.main.MySQLConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PrimaryService {

    public void insertarPuntuacion(int puntuacion, LocalDateTime fecha, int idplayer) {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            new ScoreManagerImpl().Insert(con, puntuacion, fecha, idplayer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
