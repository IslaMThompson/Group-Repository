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

    /**
     * Test output when getCity input is null
     */
    @Test
    void getCityTestNull()
    {
        app.getCity(null);
    }

    /**
     * Test output when getCountry input is null
     */
    @Test
    void getCountryTestNull()
    {
        app.getCountry(null);
    }

    /**
     * Test displayCountry when Country variable is null
     */
    @Test
    void displayCountryTestNull()
    {
        Country nullCountry = app.getCountry(null);
        app.displayCountry(nullCountry);
    }

    /**
     * Test getCountries methods when inputs are null
     */
    @Test
    void getCountriesByAreaTestNull()
    {
        app.getAllCountries();
        app.getCountriesByContinent(null);
        app.getCountriesByRegion(null);
    }

    /**
     * Test output when printCountries input is null
     */
    @Test
    void printCountriesTestNull()
    {
        app.printCountries(null);
    }

    /**
     * Test output when printCountries has an empty ArrayList
     */
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }

    /**
     * Test output when printLanguageReport has a null input
     */
    @Test
    void printLanguageReportTestNull()
    {

        app.printLanguageReport(null);
    }

    /**
     * Test output when printLanguageReport is given an empty ArrayList
     */
    @Test
    void printLanguageReportTestEmpty()
    {
        ArrayList<CountryLanguage> reports = new ArrayList<CountryLanguage>();
        app.printLanguageReport(reports);
    }

    /**
     * Test output when getCitiesByArea is given null parameters
     */
    @Test
    void getCitiesByAreaTestNullParams()
    {
        // null areaType
        app.getCitiesByArea(null, "Europe", 10);
        // zero / negative limit
        app.getCitiesByArea("world", "Europe", 0);
    }

    /**
     * Test output when printCities is given null
     */
    @Test
    void printCitiesTestNull()
    {

        app.printCities(null);
    }

    /**
     * Test output when printCities is given an empty ArrayList
     */
    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }

    /**
     * Test output when getPopulationSummary is given a null parameter
     */
    @Test
    void getPopulationSummaryTestNull()
    {

        app.getPopulationSummary(null, "Europe");
    }

    /**
     * Test output when printPopulationSummary has a null parameter
     */
    @Test
    void printPopulationSummaryTestNull()
    {

        app.printPopulationSummary(null);
    }

    /**
     * Test output when getCapitalCities is given null parameters
     */
    @Test
    void getCapitalCitiesTestNullParams()
    {
        app.getCapitalCities(null, "Europe", 10);
        app.getCapitalCities("world", "Europe", 0);
    }

    /**
     * Test output when printCapitalCities is given a null parameter
     */
    @Test
    void printCapitalCitiesTestNull()
    {

        app.printCapitalCities(null);
    }

    /**
     * Test output when printCapitalCities is given an empty ArrayList
     */
    @Test
    void printCapitalCitiesTestEmpty() {
        ArrayList<City> capitals = new ArrayList<City>();
        app.printCapitalCities(capitals);
    }


}