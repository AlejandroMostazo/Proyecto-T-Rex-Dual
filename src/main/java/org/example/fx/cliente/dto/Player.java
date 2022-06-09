package org.example.fx.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id;
    private String name;
    private String contraseña;

    private String email;

    public Player(String nombre, String contraseña) {
        this.name = nombre;
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Player(String name, String contraseña, String email) {
        this.name = name;
        this.contraseña = contraseña;
        this.email = email;
    }
}
