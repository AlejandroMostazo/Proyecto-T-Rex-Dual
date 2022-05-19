package org.example.fx.modelBDD.dao;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player implements Comparable<Player>{

    int id;
    String name;
    String contraseña;



    public Player(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.name = result.getString("nombre");
            this.contraseña = result.getString("contraseña");
        } catch (SQLException e) {
            System.out.println("No se puede acceder a la tabla Player de la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Player o) {
        return this.name.compareTo(o.getName());
    }
}
