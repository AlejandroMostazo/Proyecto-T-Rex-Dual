package org.example.fx.services;

import org.example.fx.cliente.ClienteIncio;
import org.example.fx.cliente.dto.Player;

import java.sql.SQLException;
import java.util.Base64;

public class InicioService {

    public void insertarJugador(String nombre, String contraseña, String email) {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//
//            new PlayerManagerImpl().insert(con, nombre, encodedString);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
        String encodedString = Base64.getEncoder().encodeToString(contraseña.getBytes());
        new ClienteIncio().insertarPlayer(nombre, encodedString, email);

    }

//    public List<Player> buscarJugadores () throws SQLException, ClassNotFoundException {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//
//           return new PlayerManagerImpl().findAll(con);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw e;
//        }
//    }

    public boolean validarJugador(String nombre, String contraseña) throws SQLException, ClassNotFoundException {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//            String encodedString = Base64.getEncoder().encodeToString(contraseña.getBytes());
//           return new PlayerManagerImpl().validatePlayer(con, nombre, encodedString) != null;
//        }
        String encodedString = Base64.getEncoder().encodeToString(contraseña.getBytes());
        return new ClienteIncio().checkPlayer(nombre, encodedString);
    }

    public Player buscarJugadorByName (String nombre) throws SQLException, ClassNotFoundException {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//            return new PlayerManagerImpl().findByName(con, nombre);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw e;
//        }

        return new ClienteIncio().searchPlayerByName(nombre);
    }

}


