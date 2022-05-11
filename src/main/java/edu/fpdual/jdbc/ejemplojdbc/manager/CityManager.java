package edu.fpdual.jdbc.ejemplojdbc.manager;

import edu.fpdual.jdbc.ejemplojdbc.dao.City;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * City DTO Manager.
 *
 * Contains all the queries used to consult and manipulate Cities data.
 *
 * @author jose.m.prieto.villar
 *
 */
public interface CityManager {

    /**
     * Finds all the cities in the DB
     *
     * @param con DB connection
     * @return a {@link List} of {@link City}
     */
    public List<City> findAll(Connection con);

    /**
     * Find an specific cities from the DB
     *
     * @param con DB connection
     * @param id the city id
     * @return a {@link List} of {@link City}
     */
    public List<City> findById(Connection con, int id);

    public void Upade(Connection con, String newname, int id) throws SQLException;

    public List<City> frist(Connection con, String fristLetter);

    public City endLetter(Connection con, String end);

    public List<City> findByName(Connection con, String name);

}