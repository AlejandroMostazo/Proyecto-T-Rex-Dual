package edu.fpdual.jdbc.ejemplojdbc.main;

import edu.fpdual.jdbc.ejemplojdbc.connector.MySQLConnector;
import edu.fpdual.jdbc.ejemplojdbc.manager.impl.CityManagerImpl;
import edu.fpdual.jdbc.ejemplojdbc.manager.impl.CountryLamguageImpl;
import edu.fpdual.jdbc.ejemplojdbc.manager.impl.CountryManagerImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Connects to the DB

        try (Connection con = new MySQLConnector().getMySQLConnection()){
            // Looks for all the cities in the DB and prints them.
            System.out.println(new CityManagerImpl().findById(con, 2));
            new CityManagerImpl().Upade(con, "Marvella", 707);
            System.out.println(new CityManagerImpl().findByName(con, "Marvella"));
            System.out.println(new CityManagerImpl().frist(con, "X"));
            System.out.println(new CityManagerImpl().endLetter(con, "a"));
            new CityManagerImpl().Delete(con, 6666);
            new CityManagerImpl().Insert(con, 1234, "Nombre");
            System.out.println(new CityManagerImpl().findById(con, 1234));
           System.out.println("----------------Countrys-------------------------------------");
            System.out.println(new CountryManagerImpl().findByName(con, "Argentina"));
            System.out.println(new CountryManagerImpl().StartByName(con, "A"));
            System.out.println(new CountryManagerImpl().endByName(con, "n"));
            new CountryManagerImpl().Upade(con, "Argentina", "ARG");
            System.out.println(new CountryManagerImpl().findByName(con, "Argentina"));
            System.out.println("----------------CountryLanguages-------------------------------------");
            System.out.println(new CountryLamguageImpl().findAll(con));

//			List<Country> countries = new CountryManager().findBySurfaceAreaBetween(con, BigDecimal.valueOf(100),
//					BigDecimal.valueOf(1000));
//			System.out.println(countries.size());
//			countries.forEach(country -> System.out.println(country));
//			new GeneralManager().findLanguajeDataWithPercentageGreaterThan(con, 0)
//					.forEach(data -> System.out.printf(
//							"Datos de la ciudad %s: lenguaje -> %s - Porcentaje de habla: %f - Pais: (%s) %s ",
//							data.getCityName(), data.getCityLanguage(), data.getLanguagePercentage(),
//							data.getCountryCode(), data.getCountryName() + "\n"));
        }
    }

}
