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
@Builder
public class Join implements Comparable<Join>{

    int puesto;
    String nombre;
    int puntuacion;
    Date fecha;



    public Join(ResultSet result) {
        try {
            this.puesto = result.getInt("puesto");
            this.nombre = result.getString("nombre");
            this.puntuacion = result.getInt("top");
            this.fecha = result.getDate("date");
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
