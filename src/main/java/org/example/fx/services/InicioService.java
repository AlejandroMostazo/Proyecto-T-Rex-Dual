package org.example.fx.services;

import org.example.fx.cliente.ClienteIncio;
import org.example.fx.modelBDD.manager.impl.PlayerManagerImpl;
import org.example.fx.cliente.dto.Player;
import org.example.fx.modelBDD.main.MySQLConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class InicioService {

    public void insertarJugador(String nombre, String contraseña) {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//            String encodedString = Base64.getEncoder().encodeToString(contraseña.getBytes());
//            new PlayerManagerImpl().insert(con, nombre, encodedString);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
        new ClienteIncio().insertarPlayer(nombre, contraseña);

    }

    public List<Player> buscarJugadores () throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {

           return new PlayerManagerImpl().findAll(con);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

    public boolean validarJugador(String nombre, String contraseña) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            String encodedString = Base64.getEncoder().encodeToString(contraseña.getBytes());
           return new PlayerManagerImpl().validatePlayer(con, nombre, encodedString) != null;
        }
    }

    public Player buscarJugadorByName (String nombre) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return new PlayerManagerImpl().findByName(con, nombre);
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

}


