package edu.fpdual.jdbc.ejemplojdbc.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class CountryLanguage {

    String country;
    String language;
    String isOficial;
    Double percentage;

    public CountryLanguage(ResultSet result) {
        try {
            this.country = result.getString("CountryCode");
            this.language = result.getString("Language");
            this.isOficial = result.getString("IsOfficial");
            this.percentage = result.getDouble("Percentage");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
