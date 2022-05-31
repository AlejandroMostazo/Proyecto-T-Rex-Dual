package org.example.fx.modelBDD.manager.impl;


import org.example.fx.modelBDD.dao.Join;
import org.example.fx.modelBDD.manager.JoinMaganager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * City DTO Manager.
 *
 * Contains all the queries used to consult and manipulate Cities data.
 *
 * @author jose.m.prieto.villar
 *
 */

public class JoinManagerImpl implements JoinMaganager {

    public List<Join> findAll(Connection con) {
        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("select row_number() over (order by max(puntuacion) desc) as 'puesto', juego.player.nombre, MAX(puntuacion) as 'top', juego.score.date from juego.player join juego.score on (player.id=score.idplayer) group by juego.player.id");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<Join> players = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                players.add(new Join(result));
                // Groups the countried by city
            }
            return players;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Join> findById(Connection con, int id) {
        //prepare SQL statement
        String sql = "select row_number() over (order by puntuacion desc) as 'puesto', juego.player.nombre, juego.score.puntuacion as 'top', juego.score.date from juego.player join juego.score on (player.id=score.idplayer) where juego.score.idplayer=? order by 2 desc;";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            List<Join> j = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                j.add(new Join(result));
            }

            return j;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fills all the countries for each city.
     *
     * @param con       the Db connection
     * @param countries the map of cities and countries.
     * @param cities    the list of cities to update.
     */

}
