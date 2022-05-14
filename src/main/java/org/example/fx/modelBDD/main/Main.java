package org.example.fx.modelBDD.main;

import org.example.fx.modelBDD.Impl.PlayerManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Connects to the DB
        try (Connection con = new MySQLConnector().getMySQLConnection()){
            // Looks for all the cities in the DB and prints them.
//            System.out.println(new CityManagerImpl().findById(con, 2));
//
//            new CityManagerImpl().Upade(con, "Marvella", 707);
//            System.out.println(new CityManagerImpl().findByName(con, "Marvella"));
//            System.out.println(new CityManagerImpl().frist(con, "X"));
//            System.out.println(new CityManagerImpl().endLetter(con, "a"));
//            new CityManagerImpl().Delete(con, 6666);
//            new CityManagerImpl().Insert(con);
//            System.out.println(new CityManagerImpl().findById(con, 6666));
//            System.out.println("----------------Countrys-------------------------------------");
//            System.out.println(new PlayerManagerImpl().findAll(con));

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
