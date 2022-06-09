package org.example.fx.cliente.dto;

public class Email {
    private String email;
    private String nombre;
    private int id;

    public Email(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public Email(String email, int id) {
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
