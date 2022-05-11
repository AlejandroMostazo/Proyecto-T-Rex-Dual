package edu.fpdual.jdbc.ejemplojdbc.manager.impl;

import edu.fpdual.jdbc.ejemplojdbc.dao.City;
import edu.fpdual.jdbc.ejemplojdbc.dao.Country;
import edu.fpdual.jdbc.ejemplojdbc.manager.CityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * City DTO Manager.
 *
 * Contains all the queries used to consult and manipulate Cities data.
 *
 * @author jose.m.prieto.villar
 *
 */

public class CityManagerImpl implements CityManager {


    public List<City> findAll(Connection con) {
        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("SELECT * FROM City");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<City> cities = new ArrayList<>();
            Map<Integer, String> countries = new HashMap();

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                cities.add(new City(result));
                // Groups the countried by city
                countries.put(result.getInt("ID"), result.getString("CountryCode"));
            }

            // Fills the country of each city
            fillCountries(con, countries, cities);

            return cities;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<City> findById(Connection con, int id) {
        //prepare SQL statement
        String sql = "select * "
                + "from city a, Country b "
                + "where a.id = ? "
                + "and a.CountryCode = b.Code";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, id);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable

            List<City> cities = new ArrayList<>();
            // Run through each result
            while (result.next()) {
                // Initializes a city per result
               City city = new City(result);
               cities.add(city);
            }

            return cities;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<City> findByName(Connection con, String name) {
        //prepare SQL statement
        String sql = "select * "
                + "from city a, Country b "
                + "where a.Name = ? "
                + "and a.CountryCode = b.Code";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, name);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            City city = null;
            List<City> cities = new ArrayList<>();
            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                city = new City(result);
                cities.add(city);
                Country country = new Country(result);
                city.setCountry(country);
            }

            return cities;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public City endLetter(Connection con, String end) {
        //prepare SQL statement
        String sql = "select * "
                + "from city a, Country b "
                + "where a.Name LIKE ? "
                + "and a.CountryCode = b.Code";

        // Create general statement

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, '%'+end);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            City city = null;

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                city = new City(result);
                Country country = new Country(result);
                city.setCountry(country);
            }

            return city;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<City> frist(Connection con, String fristLetter) {
        //prepare SQL statement
        String sql = "select * "
                + "from city a, Country b "
                + "where a.Name LIKE ? "
                + "and a.CountryCode = b.Code";

        // Create general statement

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, fristLetter+'%');
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            City city = null;
            List<City> ciudades = new ArrayList<>();
            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                city = new City(result);
                Country country = new Country(result);
                city.setCountry(country);
                ciudades.add(city);
            }

            return ciudades;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void Delete(Connection con, int id) {

        String sql = ("DELETE FROM city WHERE id = ?");
        //prepare SQL statement
        try(PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            //Set before first registry before going through it.
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Insert(Connection con, int id, String nombre) {

        String sql = ("INSERT INTO city (ID, Name, CountryCode, District, Population) VALUES (?, ?, ?, ?, ?);");
        //prepare SQL statement
        try(PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
           st.setString(2, nombre);
            st.setString(3, "ESP");
            st.setString(4, "nombre2");
            st.setInt(5, 20000);

            //Set before first registry before going through it.
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Upade(Connection con, String newname, int id) {
        //prepare SQL statement

        String sql = "UPDATE city a SET name = ? where id = ?";
        try(PreparedStatement st = con.prepareStatement(sql)) {
            // Queries the DB
            st.setString(1, newname);
            st.setInt(2, id);
            // Set before first registry before going through it.
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills all the countries for each city.
     *
     * @param con       the Db connection
     * @param countries the map of cities and countries.
     * @param cities    the list of cities to update.
     */
    private void fillCountries(Connection con, Map<Integer, String> countries, List<City> cities) {
        // Obtains all the country codes to search
        Set<String> countryCodes = new HashSet<>(countries.values());

        // Looks for all countries and groups them by id.
        Map<String, Country> countriesMap = new CountryManagerImpl().findAllById(con, countryCodes).stream()
                .collect(Collectors.toMap(Country::getId, data -> data));

        // Associates the corresponding Country to each City
        cities.forEach(city -> {
            String countryCode = countries.get(city.getId());
            Country foundCountry = countriesMap.get(countryCode);
            city.setCountry(foundCountry);
        });

    }

}
