package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetCountry()
    {
        Country country = app.getCountry("Armenia");

        assertEquals(country.code, "ARM");
        assertEquals(country.name, "Armenia");
        assertEquals(country.continent, "Asia");
        assertEquals(country.region, "Middle East");
        assertEquals(country.population, 3520000);
        assertEquals(country.capital, 126);

    }

    @Test
    void testGetCity()
    {
        City city = app.getCity("Edinburgh");

        assertEquals(city.id, 460);
        assertEquals(city.name, "Edinburgh");
        assertEquals(city.country_code, "GBR");
        assertEquals(city.district, "Scotland");
        assertEquals(city.population, 450180);
    }

    @Test
    void testGetLanguageReport()
    {
        ArrayList<CountryLanguage> reports = app.getLanguageReport();

        assertNotNull(reports);
        // chinese, english, hindi, spanish, arabic = 5 rows
        assertEquals(reports.size(), 5);
        // in db, chinese is the largest
        assertEquals(reports.get(0).language, "Chinese");
    }

    @Test
    void testGetCitiesByAreaWorld()
    {
        ArrayList<City> cities = app.getCitiesByArea("world", "", 5);

        assertNotNull(cities);
        assertEquals(cities.size(), 5);
        // in the db, top city is "Mumbai (Bombay)"
        assertEquals(cities.get(0).name, "Mumbai (Bombay)");
    }

    @Test
    void testGetPopulationSummaryWorld()
    {
        PopulationSummary ps = app.getPopulationSummary("world", "");

        assertNotNull(ps);
        // total population in db
        assertEquals(ps.totalPop, 6078749450L);
        assertTrue(ps.cityPop > 0);
        assertTrue(ps.nonCityPop >= 0);
    }

    @Test
    void testGetCapitalCitiesWorld()
    {
        ArrayList<City> capitals = app.getCapitalCities("world", "", 5);

        assertNotNull(capitals);
        assertEquals(capitals.size(), 5);
        // in the standard world db, Seoul is the largest capital
        assertEquals(capitals.get(0).name, "Seoul");
    }
}