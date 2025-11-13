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

        // Gets ArrayList of all countries in db.
        ArrayList<Country> allCountries = a.getTopCountries();
        // Displays provided ArrayList of countries.
        a.printCountries(allCountries);

        a.getPopulationReport();

        ArrayList<Country> countriesContinent = a.getTopCountriesByContinent("Africa");
        a.printCountries(countriesContinent);

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

    public City getCity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.country_code = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
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

    public Country getCountry(String code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Code = '" + code + "'";
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

    public void getPopulationReport()
    {
        try {
            Statement stmt = con.createStatement();

            System.out.println("\n=== Total Population Report ===");

            String worldQuery = "SELECT SUM(Population) AS TotalPop FROM country;;";
            ResultSet rsWorld = stmt.executeQuery(worldQuery);
            if (rsWorld.next())
            {
                long worldPop = rsWorld.getLong("TotalPop");
                System.out.println(String.format("%-25s %-15d", "World Population", worldPop));
            }

            String continentQuery = "SELECT Continent, SUM(Population) AS TotalPop FROM country GROUP BY Continent ORDER BY TotalPop DESC;";
            ResultSet rsCont = stmt.executeQuery(continentQuery);
            System.out.println("\nPopulation by Continent");
            System.out.println(String.format("%-20s %-15s", "Continent", "Population"));
            while (rsCont.next())
            {
                String continent = rsCont.getString("Continent");
                long pop = rsCont.getLong("TotalPop");
                System.out.println(String.format("%-20s %-15d", continent, pop));
            }

            // Population by region
            String regionQuery = "SELECT Region, SUM(Population) AS TotalPop FROM country GROUP BY Region ORDER BY TotalPop DESC;";
            ResultSet rsReg = stmt.executeQuery(regionQuery);
            System.out.println("\nPopulation by Region:");
            System.out.println(String.format("%-30s %-15s", "Region", "Population"));
            while (rsReg.next())
            {
                String region = rsReg.getString("Region");
                long pop = rsReg.getLong("TotalPop");
                System.out.println(String.format("%-30s %-15d", region, pop));
            }

            // Population by country
            String countryQuery = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT 10;";
            ResultSet rsCountry = stmt.executeQuery(countryQuery);
            System.out.println("\nTop 10 Populous Countries:");
            System.out.println(String.format("%-45s %-15s", "Country", "Population"));
            while (rsCountry.next())
            {
                String name = rsCountry.getString("Name");
                long pop = rsCountry.getLong("Population");
                System.out.println(String.format("%-45s %-15d", name, pop));
            }

            // Population by district
            String districtQuery = "SELECT District, SUM(Population) AS TotalPop FROM city GROUP BY District ORDER BY TotalPop DESC LIMIT 10;";
            ResultSet rsDist = stmt.executeQuery(districtQuery);
            System.out.println("\nTop 10 Districts by Population:");
            System.out.println(String.format("%-30s %-15s", "District", "Population"));
            while (rsDist.next())
            {
                String district = rsDist.getString("District");
                long pop = rsDist.getLong("TotalPop");
                System.out.println(String.format("%-30s %-15d", district, pop));
            }

            // Population by city
            String cityQuery = "SELECT Name, Population FROM city ORDER BY Population DESC LIMIT 10;";
            ResultSet rsCity = stmt.executeQuery(cityQuery);
            System.out.println("\nTop 10 Cities by Population:");
            System.out.println(String.format("%-45s %-15s", "City", "Population"));
            while (rsCity.next())
            {
                String city = rsCity.getString("Name");
                long pop = rsCity.getLong("Population");
                System.out.println(String.format("%-45s %-15d", city, pop));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate population report");
        }
    }
}

