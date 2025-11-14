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
}