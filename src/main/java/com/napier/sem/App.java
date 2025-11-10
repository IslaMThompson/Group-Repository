package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if(args.length < 1){
            a.connect("localhost:33060", 30000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        ArrayList<City> allCities = a.getAllCities();
        a.printCities(allCities);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Returns the details of a specified country.
     * @param name - Country name
     * @return country
     */
    public Country getCountry(String name)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Name = '" + name + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                return country;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Returns the details of a specified city.
     * @param name - City name
     * @return city
     */
    public City getCity(String name)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.Name = '" + name + "' " +
                            "AND city.CountryCode = country.Code;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                return city;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Returns the details of the capital city for a specified country.
     * @param country - Country name
     * @return capital
     */
    public City getCapital(String country)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name AS CountryName, city.Name AS CityName, city.Population "
                            + "FROM city, country "
                            + "WHERE country.Name = '" + country + "' " +
                            "AND city.ID = country.Capital;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City city = new City();
                city.name = rset.getString("CityName");
                city.country = rset.getString("CountryName");
                city.population = rset.getInt("city.Population");
                return city;
            }
            else
                return null;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital details");
            return null;
        }
    }

    /**
     * Displays the details for a Country class variable.
     * @param country - Country details
     */
    public static void displayCountry(Country country)
    {
        if (country != null)
        {
            System.out.println(
                    country.code + "\n"
                            + country.name + "\n"
                            + country.region + "\n"
                            + country.continent + "\n"
                            + "Population: " + country.population + "\n"
                            + "Capital: " + country.capital + "\n");
        }
    }

    /**
     * Displays the details for a City class variable.
     * @param city - City details
     */
    public static void displayCity(City city)
    {
        if (city != null)
        {
            System.out.println(
                    city.name + "\n"
                            + city.country + "\n"
                            + city.district + "\n"
                            + "Population: " + city.population + "\n");
        }
    }

    /**
     * Displays the details for a City class variable where the city is the capital of the country.
     * @param city - Capital city details
     */
    public static void displayCapital(City city)
    {
        if (city != null)
        {
            System.out.println(
                    city.name + "\n"
                            + city.country + "\n"
                            + "Population: " + city.population + "\n");
        }
    }

    /**
     * Returns list of all countries details in the globe.
     * @return ArrayList<Country> countries
     */
    public ArrayList<Country> getAllCountries()
    {
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                                "FROM country " +
                                "ORDER BY Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    /**
     * Returns list of all countries details in a specified continent.
     * @param continent
     * @return ArrayList<Country> countries
     */
    public ArrayList<Country> getCountriesByContinent(String continent)
    {
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Continent = '" + continent + "' " +
                    "ORDER BY Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    /**
     * Returns list of all countries details in a specified region.
     * @param region
     * @return ArrayList<Country> countries
     */
    public ArrayList<Country> getCountriesByRegion(String region)
    {
        try{
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Region = '" + region + "' " +
                    "ORDER BY Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    /**
     * Returns the top 'n' (user-input) of countries from the globe.
     * @return ArrayList<Country> countries
     */
    public ArrayList<Country> getTopCountries()
    {
        try{
            Statement stmt = con.createStatement();

            Scanner lineRead = new Scanner(System.in);
            System.out.print("Enter Number Of Lines To Output: ");
            int n = lineRead.nextInt();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    /**
     * Returns the top 'n' (user-input) of countries from a continent.
     * @param continent
     * @return ArrayList<Country> countries
     */
    public ArrayList<Country> getTopCountriesByContinent(String continent)
    {
        try{
            Statement stmt = con.createStatement();

            Scanner lineRead = new Scanner(System.in);
            System.out.print("Enter Number Of Lines To Output: ");
            int n = lineRead.nextInt();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Continent = '" + continent + "' " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    /**
     * Returns the top 'n' (user-input) of countries from a region.
     * @param region
     * @return ArrayList<Country> countries
     */
    public ArrayList<Country> getTopCountriesByRegion(String region)
    {
        try{
            Statement stmt = con.createStatement();

            Scanner lineRead = new Scanner(System.in);
            System.out.print("Enter Number Of Lines To Output: ");
            int n = lineRead.nextInt();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Region = '" + region + "' " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next()){
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                countries.add(country);
            }
            return countries;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    public ArrayList<City> getAllCities()
    {
        try{
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population "
                            + "FROM city, country "
                            + "WHERE city.CountryCode = country.Code;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cities = new ArrayList<>();
            while(rset.next()){
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details.");
            return null;
        }
    }

    /**
     * Prints a list of countries.
     *
     * @param countries The list of countries to print.
     */
    public void printCountries(ArrayList<Country> countries)
    {
        // Check employees is not null.
        if(countries == null)
        {
            System.out.println("No countries in list.");
            return;
        }

        // Print header.
        System.out.println(String.format("%-8s %-45s %-15s %-30s %-15s %-10s", "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Loop over all countries in the list.
        for(Country country : countries)
        {
            if(country == null)
                continue;

            String country_string = String.format("%-8s %-45s %-15s %-30s %-15s %-10s",
                    country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(country_string);
        }
    }

    public void printCities(ArrayList<City> cities)
    {
        // Check employees is not null.
        if(cities == null)
        {
            System.out.println("No countries in list.");
            return;
        }

        // Print header.
        System.out.println(String.format("%-40s %-45s %-20s %-10s", "City", "Country", "District", "Population"));

        // Loop over all countries in the list.
        for(City city : cities)
        {
            if(city == null)
                continue;

            String country_string = String.format("%-40s %-45s %-20s %-10s",
                    city.name, city.country, city.district, city.population);
            System.out.println(country_string);
        }
    }

    public void printCapitals(ArrayList<City> capitals)
    {
        // Check employees is not null.
        if(capitals == null)
        {
            System.out.println("No countries in list.");
            return;
        }

        // Print header.
        System.out.println(String.format("%-40s %-45s %-10s", "City", "Country", "Population"));

        // Loop over all countries in the list.
        for(City capital : capitals)
        {
            if(capital == null)
                continue;

            String country_string = String.format("%-40s %-45s %-10s",
                    capital.name, capital.country, capital.population);
            System.out.println(country_string);
        }
    }

}