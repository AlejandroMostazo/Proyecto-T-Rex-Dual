package org.example.fx.services;

import org.example.fx.cliente.ClientePrimary;
import org.example.fx.cliente.ClienteSecondary;
import org.example.fx.cliente.dto.Join;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SecondaryService {

//    public void insertarJugador(String nombre, String contraseña) {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//            new PlayerManagerImpl().Insert(con, nombre, contraseña);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public List<Join> ranking () throws SQLException, ClassNotFoundException {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//           return new JoinManagerImpl().findAll(con);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw e;
//        }

       return new ClienteSecondary().ranking();
    }


    public List<Join> rankingById (int id) throws SQLException, ClassNotFoundException {
//        try (Connection con = new MySQLConnector().getMySQLConnection()) {
//            return new JoinManagerImpl().findById(con, id);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw e;
//        }
        return new ClienteSecondary().rankingId(id);
    }

}


