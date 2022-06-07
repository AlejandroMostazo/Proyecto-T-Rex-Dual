package org.example.fx.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id;
    private String name;
    private String contraseña;

    public Player(String name, String contraseña) {
        this.name = name;
        this.contraseña = contraseña;
    }
}
