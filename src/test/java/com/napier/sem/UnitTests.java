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


}