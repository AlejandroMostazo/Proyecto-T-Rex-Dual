package edu.fpdual.jdbc.ejemplojdbc.manager.impl;

import edu.fpdual.jdbc.ejemplojdbc.dao.Country;
import edu.fpdual.jdbc.ejemplojdbc.manager.CountryManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Country DTO Manager.
 * <p>
 * Contains all the queries used to consult and manipulate Countries data.
 *
 * @author jose.m.prieto.villar
 */
public class CountryManagerImpl implements CountryManager {


    public List<Country> findByName(Connection con, String name) throws SQLException {

        try (PreparedStatement prepStmt = con.prepareStatement("SELECT * FROM Country c where c.name = ?")) {
            prepStmt.setString(1, name);

            return prepareReturn(prepStmt.executeQuery());
        }

    }

    public List<Country> StartByName(Connection con, String name) throws SQLException {

        try (PreparedStatement prepStmt = con.prepareStatement("SELECT * FROM Country c where c.name LIKE ?")) {
            prepStmt.setString(1, name+'%');

            return prepareReturn(prepStmt.executeQuery());
        }

    }

    public List<Country> endByName(Connection con, String name) throws SQLException {

        try (PreparedStatement prepStmt = con.prepareStatement("SELECT * FROM Country c where c.name LIKE ?")) {
            prepStmt.setString(1, '%'+name);

            return prepareReturn(prepStmt.executeQuery());
        }

    }

    public void Delete(Connection con, String code) {

        String sql = ("DELETE FROM country WHERE code = "+code);
        //prepare SQL statement
        try(PreparedStatement st = con.prepareStatement(sql)) {


            //Set before first registry before going through it.
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Insert(Connection con) {

        String sql = ("INSERT INTO country VALUE(6666, 'Louis', 'ESP','Cristina',20000)");
        //prepare SQL statement
        try(PreparedStatement st = con.prepareStatement(sql)) {


            //Set before first registry before going through it.
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Upade(Connection con, String newname, String id) {
        //prepare SQL statement

        String sql = "UPDATE country a SET a.name = ? where code = ?";
        try(PreparedStatement st = con.prepareStatement(sql)) {
            // Queries the DB
            st.setString(1, newname);
            st.setString(2, id);
            // Set before first registry before going through it.
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Country> findAllById(Connection con, Set<String> ids) {
        // Creates the SQL command
        String sql = String.format("SELECT * FROM Country WHERE Code IN (%s)",
                ids.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));
        // "ESP","FR","DEU","UK","PR"
        // SELECT * FROM Country WHERE Code in ("ESP","FR","DEU","UK","PR"

        // Create a prepared statement
        try (Statement stmt = con.createStatement()) {

            // Executes sql command
            ResultSet result = stmt.executeQuery(sql);
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<Country> countries = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a country per result
                countries.add(new Country(result));
            }

            return countries;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Country> findBySurfaceAreaBetween(Connection con, BigDecimal startSurfaceArea,
                                                  BigDecimal endSurfaceArea) throws SQLException {

        try (PreparedStatement prepStmt = con
                .prepareStatement("SELECT * FROM Country where SurfaceArea between ? and ?")) {
            prepStmt.setBigDecimal(1, startSurfaceArea);
            prepStmt.setBigDecimal(2, endSurfaceArea);

            return prepareReturn(prepStmt.executeQuery());
        }

    }

    /**
     * Prepare the return of a result set
     * @param result the result set to prepate
     * @return a {@link List} of {@link Country}
     * @throws {@link SQLException}
     */
    private List<Country> prepareReturn(ResultSet result) throws SQLException {

        List<Country> countries = new ArrayList<>();

        while (result.next()) {
            countries.add(new Country(result));
        }

        return countries;
    }

}
