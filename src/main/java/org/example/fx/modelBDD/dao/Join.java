package org.example.fx.modelBDD.dao;

import lombok.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Join implements Comparable<Join>{

    String nombre;
    int puntuacion;
    Date fecha;



    public Join(ResultSet result) {
        try {
            this.nombre = result.getString("nombre");
            this.puntuacion = result.getInt("top");
            this.fecha = result.getDate("date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Join o) {
        return this.nombre.compareTo(o.getNombre());
    }
}
