package org.example.fx.modelBDD.dao;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@EqualsAndHashCode
@Builder
public class Score implements Comparable<Score>{

    int id;
    int puntuacion;
    LocalDateTime fecha;
    int player;



    public Score(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.puntuacion = result.getInt("puntuacion");
            this.player = result.getInt("idplayer");
            this.fecha = result.getTimestamp("date").toLocalDateTime();
        } catch (SQLException e) {
            System.out.println("No se puede acceder a la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Score o) {
        return this.fecha.compareTo(o.getFecha());
    }
}
