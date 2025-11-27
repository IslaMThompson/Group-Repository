package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UnitTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void getCityTestNull()
    {
        app.getCity(null);
    }

    @Test
    void getCountryTestNull()
    {
        app.getCountry(null);
    }

    @Test
    void displayCountryTestNull()
    {
        Country nullCountry = app.getCountry(null);
        app.displayCountry(nullCountry);
    }

    @Test
    void getCountriesByAreaTestNull()
    {
        app.getAllCountries();
        app.getCountriesByContinent(null);
        app.getCountriesByRegion(null);
    }

    @Test
    void printCountriesTestNull()
    {
        app.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }

    @Test
    void printLanguageReportTestNull()
    {
        app.printLanguageReport(null);
    }

    @Test
    void printLanguageReportTestEmpty()
    {
        ArrayList<CountryLanguage> reports = new ArrayList<CountryLanguage>();
        app.printLanguageReport(reports);
    }

    @Test
    void getCitiesByAreaTestNullParams()
    {
        // null areaType
        app.getCitiesByArea(null, "Europe", 10);
        // zero / negative limit
        app.getCitiesByArea("world", "Europe", 0);
    }

    @Test
    void printCitiesTestNull()
    {
        app.printCities(null);
    }

    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }

    @Test
    void getPopulationSummaryTestNull()
    {
        app.getPopulationSummary(null, "Europe");
    }

    @Test
    void printPopulationSummaryTestNull()
    {
        app.printPopulationSummary(null);
    }

    @Test
    void getCapitalCitiesTestNullParams()
    {
        app.getCapitalCities(null, "Europe", 10);
        app.getCapitalCities("world", "Europe", 0);
    }

    @Test
    void printCapitalCitiesTestNull()
    {
        app.printCapitalCities(null);
    }

    @Test
    void printCapitalCitiesTestEmpty() {
        ArrayList<City> capitals = new ArrayList<City>();
        app.printCapitalCities(capitals);
    }


}