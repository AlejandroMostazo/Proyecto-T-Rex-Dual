package edu.fpdual.jdbc.ejemplojdbc.manager.impl;

import edu.fpdual.jdbc.ejemplojdbc.dao.City;
import edu.fpdual.jdbc.ejemplojdbc.dao.Country;
import edu.fpdual.jdbc.ejemplojdbc.dao.CountryLanguage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class CountryLamguageImpl {

    public List<CountryLanguage> findAll(Connection con) {
        // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("SELECT * FROM countrylanguage");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<CountryLanguage> CounLeng = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a city per result
                CounLeng.add(new CountryLanguage(result));
            }

            // Fills the country of each city

            return CounLeng;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
