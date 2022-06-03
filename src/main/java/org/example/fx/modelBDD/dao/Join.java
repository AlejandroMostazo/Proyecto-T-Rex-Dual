package org.example.fx.modelBDD.dao;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@EqualsAndHashCode
@Builder
public class Join implements Comparable<Join>{

    int puesto;
    String nombre;
    int puntuacion;
    LocalDateTime fecha;



    public Join(ResultSet result) {
        try {
            this.puesto = result.getInt("puesto");
            this.nombre = result.getString("nombre");
            this.puntuacion = result.getInt("top");
            this.fecha = result.getTimestamp("date").toLocalDateTime();
        } catch (SQLException e) {
            System.out.println("No se puede acceder a la tabla Join de la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Join o) {
        return this.nombre.compareTo(o.getNombre());
    }
}
