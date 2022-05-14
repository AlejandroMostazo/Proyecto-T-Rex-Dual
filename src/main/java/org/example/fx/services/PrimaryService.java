package org.example.fx.services;

import javafx.util.converter.LocalDateTimeStringConverter;
import org.example.fx.modelBDD.Impl.ScoreManagerImpl;
import org.example.fx.modelBDD.main.MySQLConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class PrimaryService {

    public void isertarPuntuacion(int puntuacion, LocalDateTime fecha, int idplayer) {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            new ScoreManagerImpl().Insert(con, puntuacion, fecha, idplayer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
