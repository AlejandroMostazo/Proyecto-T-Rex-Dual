package org.example.fx.modelBDD.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Comparable<Player>{

    int id;
    String name;
    int puntuacion;

    public Player(ResultSet result) {
        try {
            this.id = result.getInt("puesto");
            this.name = result.getString("nombre");
            this.puntuacion = result.getInt("puntuacion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Player o) {
        return this.name.compareTo(o.getName());
    }
}
