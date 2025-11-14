package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 30000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Gets ArrayList of all countries in db.
        a.getPopulationReport();

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
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public City getCity(Integer id) {
        if(id == null)
        {
            System.out.println("ID is null.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE ID = " + id;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.country_code = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                return city;
            } else
                return null;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public Country getCountry(String code) {
        if(code == null)
        {
            System.out.println("Code is null.");
            return null;
        }

        try {
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
            if (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getInt("Capital");
                return country;
            } else
                return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public static void displayCountry(Country country) {
        if (country != null) {
            System.out.println(
                    country.code + "\n"
                            + country.name + "\n"
                            + country.region + "\n"
                            + country.continent + "\n"
                            + "Population: " + country.population + "\n"
                            + "Capital: " + country.capital + "\n");
        }
        else{
            System.out.println("Country is null");
        }
    }

    public ArrayList<Country> getAllCountries() {
        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "ORDER BY Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    public ArrayList<Country> getCountriesByContinent(String continent) {
        if(continent == null)
        {
            System.out.println("Continent is null");
            return null;
        }

        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Continent = '" + continent + "' " +
                    "ORDER BY Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    public ArrayList<Country> getCountriesByRegion(String region) {
        if(region == null)
        {
            System.out.println("Region is null");
            return null;
        }
        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE Region = '" + region + "' " +
                    "ORDER BY Population DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    public ArrayList<Country> getTopCountries() {
        try {
            Statement stmt = con.createStatement();

            Scanner lineRead = new Scanner(System.in);
            System.out.print("Enter Number Of Lines To Output: ");
            Integer n = lineRead.nextInt();

            if(n == null)
            {
                System.out.println("Line input is null");
                return null;
            }

            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + n;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    public ArrayList<Country> getTopCountriesByContinent(String continent) {
        if(continent == null)
        {
            System.out.println("Continent is null");
            return null;
        }

        try {
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
            while (rset.next()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details.");
            return null;
        }
    }

    public ArrayList<Country> getTopCountriesByRegion(String region) {
        if(region == null)
        {
            System.out.println("Region is null");
            return null;
        }

        try {
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
            while (rset.next()) {
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
        } catch (Exception e) {
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
    public void printCountries(ArrayList<Country> countries) {
        // Check employees is not null.
        if (countries == null) {
            System.out.println("No countries in list.");
            return;
        }
        else if(countries.isEmpty())
        {
            System.out.println("No countries in list.");
            return;
        }

        // Print header.
        System.out.println(String.format("%-8s %-45s %-15s %-30s %-15s %-10s", "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Loop over all countries in the list.
        for (Country country : countries) {
            if (country == null)
                continue;

            String country_string = String.format("%-8s %-45s %-15s %-30s %-15s %-10s",
                    country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(country_string);
        }
    }

    /**
     * Prints a report of total speakers of specific languages
     * (Chinese, English, Hindi, Spanish), ordered from highest to lowest
     */
    public void getLanguageReport() {
        try {
            // new sql statement obj
            Statement stmt = con.createStatement();

            // joining countryLanguage and country to combine the language and population data
            // filters through only the specified languages
            String strSelect =
                    "SELECT cl.Language, " +
                            "       SUM((c.Population * cl.Percentage) / 100) AS TotalSpeakers, " +
                            "       (SUM((c.Population * cl.Percentage) / 100) / (SELECT SUM(Population) FROM country)) * 100 AS WorldPercentage " +
                            "FROM countrylanguage cl " +
                            "JOIN country c ON c.Code = cl.CountryCode " +
                            "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                            "GROUP BY cl.Language " +
                            "ORDER BY TotalSpeakers DESC;";

            // execute the query and store the results
            ResultSet rset = stmt.executeQuery(strSelect);

            // formatting the table header
            System.out.println(String.format("%-15s %-20s %-15s", "Language", "Total Speakers", "World %"));

            // iterating through each record in the result set
            while (rset.next()) {
                // extracting data from each column
                String language = rset.getString("Language");
                long totalSpeakers = rset.getLong("TotalSpeakers");
                double worldPercentage = rset.getDouble("WorldPercentage");

                // printing using the format
                System.out.println(String.format("%-15s %-20d %-15.2f", language, totalSpeakers, worldPercentage));
            }

            // closing the result set and statement
            rset.close();
            stmt.close();
        } catch (Exception e) {
            // print the specific sql error message
            System.out.println(e.getMessage());
            // print user friendly error message
            System.out.println("Failed to get language report");
        }
    }


    /**
     * Print a report on the total population of people in
     * an area (world, continent, region, country, district, or city)
     */
    public void getPopulationReport() {
        try {
            // new statement object
            Statement stmt = con.createStatement();

            // format report heading
            System.out.println("\n=== Total Population Report ===");

            // sum of population from countries
            String worldQuery = "SELECT SUM(Population) AS TotalPop FROM country;";
            ResultSet rsWorld = stmt.executeQuery(worldQuery);
            if (rsWorld.next()) {
                // using the result
                long worldPop = rsWorld.getLong("TotalPop");
                System.out.println(String.format("%-25s %-15d", "World Population", worldPop));
            }

            // grouping population by continent and sorting by largest to lowest
            String continentQuery = "SELECT Continent, SUM(Population) AS TotalPop FROM country GROUP BY Continent ORDER BY TotalPop DESC;";
            ResultSet rsCont = stmt.executeQuery(continentQuery);
            System.out.println("\nPopulation by Continent");
            System.out.println(String.format("%-20s %-15s", "Continent", "Population"));
            while (rsCont.next()) {
                String continent = rsCont.getString("Continent");
                long pop = rsCont.getLong("TotalPop");
                System.out.println(String.format("%-20s %-15d", continent, pop));
            }

            // Population by region
            String regionQuery = "SELECT Region, SUM(Population) AS TotalPop FROM country GROUP BY Region ORDER BY TotalPop DESC;";
            ResultSet rsReg = stmt.executeQuery(regionQuery);
            System.out.println("\nPopulation by Region:");
            System.out.println(String.format("%-30s %-15s", "Region", "Population"));
            while (rsReg.next()) {
                String region = rsReg.getString("Region");
                long pop = rsReg.getLong("TotalPop");
                System.out.println(String.format("%-30s %-15d", region, pop));
            }

            // Population by country
            String countryQuery = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT 10;";
            ResultSet rsCountry = stmt.executeQuery(countryQuery);
            System.out.println("\nTop 10 Populous Countries:");
            System.out.println(String.format("%-45s %-15s", "Country", "Population"));
            while (rsCountry.next()) {
                String name = rsCountry.getString("Name");
                long pop = rsCountry.getLong("Population");
                System.out.println(String.format("%-45s %-15d", name, pop));
            }

            // population by district
            String districtQuery = "SELECT District, SUM(Population) AS TotalPop FROM city GROUP BY District ORDER BY TotalPop DESC LIMIT 10;";
            ResultSet rsDist = stmt.executeQuery(districtQuery);
            System.out.println("\nTop 10 Districts by Population:");
            System.out.println(String.format("%-30s %-15s", "District", "Population"));
            while (rsDist.next()) {
                String district = rsDist.getString("District");
                long pop = rsDist.getLong("TotalPop");
                System.out.println(String.format("%-30s %-15d", district, pop));
            }

            // population by city
            String cityQuery = "SELECT Name, Population FROM city ORDER BY Population DESC LIMIT 10;";
            ResultSet rsCity = stmt.executeQuery(cityQuery);
            System.out.println("\nTop 10 Cities by Population:");
            System.out.println(String.format("%-45s %-15s", "City", "Population"));
            while (rsCity.next()) {
                String city = rsCity.getString("Name");
                long pop = rsCity.getLong("Population");
                System.out.println(String.format("%-45s %-15d", city, pop));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate population report");
        }
    }

    public void getCityVsNonCityReport() {
        try {
            // new sql statement obj
            Statement stmt = con.createStatement();

            // formatting report header
            System.out.println("\n=== Population in Cities vs Outside Cities ===\n");

            // calculates the total population by continent and hte populiaton living in cities
            String continentQuery =
                    "SELECT c.Continent, " +
                            "       SUM(c.Population) AS TotalPop, " +
                            "       (SELECT SUM(ci.Population) " +
                            "        FROM city ci " +
                            "        JOIN country c2 ON ci.CountryCode = c2.Code " +
                            "        WHERE c2.Continent = c.Continent) AS CityPop " +
                            "FROM country c " +
                            "GROUP BY c.Continent " +
                            "ORDER BY TotalPop DESC;";

            ResultSet rsCont = stmt.executeQuery(continentQuery);

            // continent header
            System.out.println("By Continent:");
            System.out.println(String.format("%-15s %-15s %-15s %-15s",
                    "Continent", "Total", "Cities", "Non-Cities"));

            while (rsCont.next()) {
                // extracting values
                String cont = rsCont.getString("Continent");
                long total = rsCont.getLong("TotalPop");
                long city = rsCont.getLong("CityPop");
                long nonCity = total - city; // getting the outside city population

                System.out.println(String.format("%-15s %-15d %-15d %-15d",
                        cont, total, city, nonCity));
            }

            // region report,
            String regionQuery =
                    "SELECT c.Region, " +
                            "       SUM(c.Population) AS TotalPop, " +
                            "       (SELECT SUM(ci.Population) " +
                            "        FROM city ci " +
                            "        JOIN country c2 ON ci.CountryCode = c2.Code " +
                            "        WHERE c2.Region = c.Region) AS CityPop " +
                            "FROM country c " +
                            "GROUP BY c.Region " +
                            "ORDER BY TotalPop DESC;";

            ResultSet rsReg = stmt.executeQuery(regionQuery);

            System.out.println("\nBy Region:");
            System.out.println(String.format("%-30s %-15s %-15s %-15s",
                    "Region", "Total", "Cities", "Non-Cities"));

            while (rsReg.next()) {
                String reg = rsReg.getString("Region");
                long total = rsReg.getLong("TotalPop");
                long city = rsReg.getLong("CityPop");
                long nonCity = total - city;

                System.out.println(String.format("%-30s %-15d %-15d %-15d",
                        reg, total, city, nonCity));
            }

            // country report
            String countryQuery =
                    "SELECT c.Name AS Country, " +
                            "       c.Population AS TotalPop, " +
                            "       (SELECT SUM(ci.Population) " +
                            "        FROM city ci " +
                            "        WHERE ci.CountryCode = c.Code) AS CityPop " +
                            "FROM country c " +
                            "ORDER BY c.Population DESC " +
                            "LIMIT 20;";

            ResultSet rsCountry = stmt.executeQuery(countryQuery);

            System.out.println("\nTop 20 Countries:");
            System.out.println(String.format("%-45s %-15s %-15s %-15s",
                    "Country", "Total", "Cities", "Non-Cities"));

            while (rsCountry.next()) {
                String country = rsCountry.getString("Country");
                long total = rsCountry.getLong("TotalPop");
                long city = rsCountry.getLong("CityPop");
                long nonCity = total - city;

                System.out.println(String.format("%-45s %-15d %-15d %-15d",
                        country, total, city, nonCity));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate report");
        }
    }

    /**
     * Asks the user what area they want to produce a city report for
     * (world, continent, region, country, district) and prints the top cities.
     */
    public void getCitiesByArea()
    {
        try
        {
            Scanner in = new Scanner(System.in);

            // ask area type
            System.out.print("Enter area type (world, continent, region, country, district): ");
            String areaType = in.nextLine();

            String areaName = "";

            // ask for area name only if needed
            if (!areaType.equalsIgnoreCase("world"))
            {
                System.out.print("Enter the name of the " + areaType + ": ");
                areaName = in.nextLine();
            }

            // ask how many cities to output
            System.out.print("Enter number of cities to display: ");
            int n = in.nextInt();

            // create SQL statement
            Statement stmt = con.createStatement();
            String sql;

            // build SQL based on area type
            if (areaType.equalsIgnoreCase("world"))
            {
                sql = "SELECT ci.Name, ci.District, ci.Population, c.Name AS Country "
                        + "FROM city ci "
                        + "JOIN country c ON ci.CountryCode = c.Code "
                        + "ORDER BY ci.Population DESC "
                        + "LIMIT " + n;
            }
            else if (areaType.equalsIgnoreCase("continent"))
            {
                sql = "SELECT ci.Name, ci.District, ci.Population, c.Name AS Country "
                        + "FROM city ci "
                        + "JOIN country c ON ci.CountryCode = c.Code "
                        + "WHERE c.Continent = '" + areaName + "' "
                        + "ORDER BY ci.Population DESC "
                        + "LIMIT " + n;
            }
            else if (areaType.equalsIgnoreCase("region"))
            {
                sql = "SELECT ci.Name, ci.District, ci.Population, c.Name AS Country "
                        + "FROM city ci "
                        + "JOIN country c ON ci.CountryCode = c.Code "
                        + "WHERE c.Region = '" + areaName + "' "
                        + "ORDER BY ci.Population DESC "
                        + "LIMIT " + n;
            }
            else if (areaType.equalsIgnoreCase("country"))
            {
                sql = "SELECT ci.Name, ci.District, ci.Population, c.Name AS Country "
                        + "FROM city ci "
                        + "JOIN country c ON ci.CountryCode = c.Code "
                        + "WHERE c.Name = '" + areaName + "' "
                        + "ORDER BY ci.Population DESC "
                        + "LIMIT " + n;
            }
            else if (areaType.equalsIgnoreCase("district"))
            {
                sql = "SELECT Name, District, Population, CountryCode "
                        + "FROM city "
                        + "WHERE District = '" + areaName + "' "
                        + "ORDER BY Population DESC "
                        + "LIMIT " + n;
            }
            else
            {
                System.out.println("Invalid area type.");
                return;
            }

            // run query
            ResultSet rset = stmt.executeQuery(sql);

            // print header
            System.out.println(String.format("%-40s %-25s %-15s %-20s",
                    "City", "District", "Population", "Country"));

            // print cities
            while (rset.next())
            {
                String city = rset.getString("Name");
                String district = rset.getString("District");
                int pop = rset.getInt("Population");

                String country;
                try {
                    country = rset.getString("Country");
                } catch (Exception e) {
                    country = rset.getString("CountryCode");
                }

                System.out.println(String.format("%-40s %-25s %-15d %-20s",
                        city, district, pop, country));
            }

            rset.close();
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report");
        }
    }

    /**
     * Produces a report containing the population of an area
     * (area name, total population, population in cities, % in cities,
     *  population outside cities, % outside cities)
     */
    public void getPopulationSummaryReport()
    {
        try
        {
            Scanner in = new Scanner(System.in);

            // ask the user what area type they want
            System.out.print("Enter area type (world, continent, region, country, district): ");
            String areaType = in.nextLine();

            String areaName = "";

            // only ask for name if needed
            if (!areaType.equalsIgnoreCase("world"))
            {
                System.out.print("Enter the name of the " + areaType + ": ");
                areaName = in.nextLine();
            }

            // new SQL statement
            Statement stmt = con.createStatement();

            String sqlTotal = "";
            String sqlCity = "";

            // build SQL for total population
            if (areaType.equalsIgnoreCase("world"))
            {
                sqlTotal = "SELECT SUM(Population) AS TotalPop FROM country";
                sqlCity = "SELECT SUM(ci.Population) AS CityPop "
                        + "FROM city ci";
            }
            else if (areaType.equalsIgnoreCase("continent"))
            {
                sqlTotal = "SELECT SUM(Population) AS TotalPop "
                        + "FROM country WHERE Continent = '" + areaName + "'";

                sqlCity = "SELECT SUM(ci.Population) AS CityPop "
                        + "FROM city ci "
                        + "JOIN country c ON ci.CountryCode = c.Code "
                        + "WHERE c.Continent = '" + areaName + "'";
            }
            else if (areaType.equalsIgnoreCase("region"))
            {
                sqlTotal = "SELECT SUM(Population) AS TotalPop "
                        + "FROM country WHERE Region = '" + areaName + "'";

                sqlCity = "SELECT SUM(ci.Population) AS CityPop "
                        + "FROM city ci "
                        + "JOIN country c ON ci.CountryCode = c.Code "
                        + "WHERE c.Region = '" + areaName + "'";
            }
            else if (areaType.equalsIgnoreCase("country"))
            {
                sqlTotal = "SELECT Population AS TotalPop "
                        + "FROM country WHERE Name = '" + areaName + "'";

                sqlCity = "SELECT SUM(Population) AS CityPop "
                        + "FROM city WHERE CountryCode = "
                        + "(SELECT Code FROM country WHERE Name = '" + areaName + "')";
            }
            else if (areaType.equalsIgnoreCase("district"))
            {
                sqlTotal = "SELECT SUM(Population) AS TotalPop "
                        + "FROM city WHERE District = '" + areaName + "'";

                sqlCity = "SELECT SUM(Population) AS CityPop "
                        + "FROM city WHERE District = '" + areaName + "'";
            }
            else
            {
                System.out.println("Invalid area type.");
                return;
            }

            // run total pop query
            ResultSet totalSet = stmt.executeQuery(sqlTotal);
            long totalPop = 0;
            if (totalSet.next())
            {
                totalPop = totalSet.getLong("TotalPop");
            }

            // run city pop query
            ResultSet citySet = stmt.executeQuery(sqlCity);
            long cityPop = 0;
            if (citySet.next())
            {
                cityPop = citySet.getLong("CityPop");
            }

            // calculate outside-city population
            long nonCityPop = totalPop - cityPop;

            // calculate percentages
            double pctCity = (totalPop == 0) ? 0 : ((double) cityPop / totalPop) * 100;
            double pctNonCity = (totalPop == 0) ? 0 : ((double) nonCityPop / totalPop) * 100;

            // print header
            System.out.println("\n=== Population Summary Report ===\n");

            if (areaType.equalsIgnoreCase("world"))
                System.out.println("Area: World");
            else
                System.out.println("Area: " + areaName + " (" + areaType + ")");

            // print results
            System.out.println(String.format("%-25s %-15d", "Total Population:", totalPop));
            System.out.println(String.format("%-25s %-15d (%.2f%%)", "In Cities:", cityPop, pctCity));
            System.out.println(String.format("%-25s %-15d (%.2f%%)", "Outside Cities:", nonCityPop, pctNonCity));

            totalSet.close();
            citySet.close();
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate population summary report");
        }
    }

    /**
     * Produces a report of capital cities in a given area
     * (world, continent, or region) sorted by population (highest → lowest).
     */
    public void getCapitalCitiesReport()
    {
        try
        {
            Scanner in = new Scanner(System.in);

            // ask area type
            System.out.print("Enter area type (world, continent, region): ");
            String areaType = in.nextLine();

            String areaName = "";

            // only ask for name if needed
            if (!areaType.equalsIgnoreCase("world"))
            {
                System.out.print("Enter the name of the " + areaType + ": ");
                areaName = in.nextLine();
            }

            // ask for number of capitals to display
            System.out.print("Enter number of capital cities to display: ");
            int n = in.nextInt();

            // new SQL statement
            Statement stmt = con.createStatement();
            String query = "";

            // world
            if (areaType.equalsIgnoreCase("world"))
            {
                query =
                        "SELECT city.Name AS Capital, country.Name AS Country, city.Population " +
                                "FROM city " +
                                "JOIN country ON city.ID = country.Capital " +
                                "ORDER BY city.Population DESC " +
                                "LIMIT " + n + ";";
            }
            // continent
            else if (areaType.equalsIgnoreCase("continent"))
            {
                query =
                        "SELECT city.Name AS Capital, country.Name AS Country, city.Population " +
                                "FROM city " +
                                "JOIN country ON city.ID = country.Capital " +
                                "WHERE country.Continent = '" + areaName + "' " +
                                "ORDER BY city.Population DESC " +
                                "LIMIT " + n + ";";
            }
            // region
            else if (areaType.equalsIgnoreCase("region"))
            {
                query =
                        "SELECT city.Name AS Capital, country.Name AS Country, city.Population " +
                                "FROM city " +
                                "JOIN country ON city.ID = country.Capital " +
                                "WHERE country.Region = '" + areaName + "' " +
                                "ORDER BY city.Population DESC " +
                                "LIMIT " + n + ";";
            }
            else
            {
                System.out.println("Invalid area type.");
                return;
            }

            // run query
            ResultSet rset = stmt.executeQuery(query);

            // header
            System.out.println("\n=== Capital Cities Report ===\n");
            System.out.println(String.format("%-35s %-35s %-15s",
                    "Capital City", "Country", "Population"));

            // results
            while (rset.next())
            {
                String capital = rset.getString("Capital");
                String country = rset.getString("Country");
                int pop = rset.getInt("Population");

                System.out.println(String.format("%-35s %-35s %-15d",
                        capital, country, pop));
            }

            rset.close();
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities report");
        }
    }
}

