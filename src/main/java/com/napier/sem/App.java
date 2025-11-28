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

        // New scanner to read user-input throughout menu choices
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do
        {
            // Main Menu System
            System.out.println("\nMAIN MENU");
            System.out.println("---------");
            System.out.println("1. Get Country Report");
            System.out.println("2. Get City Report");
            System.out.println("3. Get Capital City Report");
            System.out.println("4. Get Language Report");
            System.out.println("5. Get Population Report");
            System.out.println("6. Get Population Summary");
            System.out.println("7. EXIT");

            System.out.print("User Choice: ");

            if (System.console() == null) {
                // Running in CI → skip Scanner, use defaults
                choice = 7;
            } else {
                choice = input.nextInt();
            }

            switch(choice)
            {
                case 1:
                    int country_choice = 0;
                    do {
                        // Country Menu System
                        System.out.println("\nCOUNTRY MENU");
                        System.out.println("------------");
                        System.out.println("1. Print All Countries");
                        System.out.println("2. Print Countries By Continent");
                        System.out.println("3. Print Countries By Region");
                        System.out.println("4. Print Single Country");
                        System.out.println("5. BACK");

                        System.out.print("User Choice: ");

                        while (!input.hasNextInt()) {
                            System.out.print("User Choice: ");
                            input.next();
                        }

                        country_choice = input.nextInt();

                        switch(country_choice)
                        {
                            case 1:
                                System.out.println("\nAll Countries");
                                ArrayList<Country> allCountries = a.getTopCountries();
                                a.printCountries(allCountries);
                                break;
                            case 2:
                                System.out.print("\nEnter Continent: ");
                                String continentName = input.next();
                                ArrayList<Country> countriesByContinent = a.getTopCountriesByContinent(continentName);
                                a.printCountries(countriesByContinent);
                                break;
                            case 3:
                                System.out.print("\nEnter Region: ");
                                String regionName = input.next();
                                ArrayList<Country> countriesByRegion = a.getTopCountriesByRegion(regionName);
                                a.printCountries(countriesByRegion);
                                break;
                            case 4:
                                System.out.print("\nEnter Country Name: ");
                                String countryName = input.next();
                                Country country = a.getCountry(countryName);
                                a.displayCountry(country);
                                break;
                        }
                    } while(country_choice != 5);
                    break;
                case 2:
                    int city_choice = 0;
                    do {
                        // City Menu System
                        System.out.println("\nCITY MENU");
                        System.out.println("------------");
                        System.out.println("1. Print All Cities");
                        System.out.println("2. Print Cities By Continent");
                        System.out.println("3. Print Cities By Region");
                        System.out.println("4. Print Cities By District");
                        System.out.println("5. Print Cities By Country");
                        System.out.println("6. Print Single City");
                        System.out.println("7. BACK");

                        System.out.print("User Choice: ");

                        while (!input.hasNextInt()) {
                            System.out.print("User Choice: ");
                            input.next();
                        }

                        city_choice = input.nextInt();
                        Integer n = 0;

                        switch(city_choice)
                        {
                            case 1:
                                System.out.println("\nAll Cities");
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> allCities = a.getCitiesByArea("world","",n);
                                a.printCities(allCities);
                                break;
                            case 2:
                                System.out.print("\nEnter Continent: ");
                                String continentName = input.next();
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> citiesByContinent = a.getCitiesByArea("continent",continentName,n);
                                a.printCities(citiesByContinent);
                                break;
                            case 3:
                                System.out.print("\nEnter Region: ");
                                String regionName = input.next();
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> citiesByRegion = a.getCitiesByArea("region",regionName,n);
                                a.printCities(citiesByRegion);
                                break;
                            case 4:
                                System.out.print("\nEnter District: ");
                                String districtName = input.next();
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> citiesByDistrict = a.getCitiesByArea("district",districtName,n);
                                a.printCities(citiesByDistrict);
                                break;
                            case 5:
                                System.out.print("\nEnter Country: ");
                                String countryName = input.next();
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> citiesByCountry = a.getCitiesByArea("country",countryName,n);
                                a.printCities(citiesByCountry);
                                break;
                            case 6:
                                System.out.print("\nEnter City Name: ");
                                String cityName = input.next();
                                City city = a.getCity(cityName);
                                a.displayCity(city);
                                break;
                        }
                    } while(city_choice != 7);
                    if(city_choice == 7)
                    {
                        System.out.println("Exiting City Menu...");
                    }
                    break;
                case 3:
                    int capital_choice = 0;
                    do {
                        // Capital City Menu System
                        System.out.println("\nCAPITAL CITY MENU");
                        System.out.println("--------------------");
                        System.out.println("1. Print All Capital Cities");
                        System.out.println("2. Print Capital Cities By Continent");
                        System.out.println("3. Print Capital Cities By Region");
                        System.out.println("4. Print Country Capital City");
                        System.out.println("5. BACK");

                        System.out.print("User Choice: ");

                        while (!input.hasNextInt()) {
                            System.out.print("User Choice: ");
                            input.next();
                        }

                        capital_choice = input.nextInt();
                        Integer n = 0;

                        switch(capital_choice)
                        {
                            case 1:
                                System.out.println("\nAll Capital Cities");
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> allCities = a.getCapitalCities("world","",n);
                                a.printCapitalCities(allCities);
                                break;
                            case 2:
                                System.out.print("\nEnter Continent: ");
                                String continentName = input.next();
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> citiesByContinent = a.getCapitalCities("continent",continentName,n);
                                a.printCapitalCities(citiesByContinent);
                                break;
                            case 3:
                                System.out.print("\nEnter Region: ");
                                String regionName = input.next();
                                System.out.print("Enter Number Of Lines To Output: ");
                                n = input.nextInt();
                                ArrayList<City> citiesByRegion = a.getCapitalCities("region",regionName,n);
                                a.printCapitalCities(citiesByRegion);
                                break;
                            case 4:
                                System.out.print("\nEnter City Name: ");
                                String cityName = input.next();
                                City capitalCity = a.getCapitalCity(cityName);
                                a.displayCapitalCity(capitalCity);
                                break;
                        }
                    } while(capital_choice != 5);
                    if(capital_choice == 5)
                    {
                        System.out.println("Exiting Capital City Menu...");
                    }
                    break;
                case 4:
                    // Language Report Display
                    ArrayList<CountryLanguage> langReport = a.getLanguageReport();
                    System.out.println("\nLANGUAGE REPORT");
                    System.out.println("---------------");
                    a.printLanguageReport(langReport);
                    break;
                case 5:
                    int pop_choice = 0;
                    do {
                        // Population Report Menu System
                        System.out.println("\nPOPULATION REPORT MENU");
                        System.out.println("--------------------");
                        System.out.println("1. Print World Population Report");
                        System.out.println("2. Print Population Report By Continent");
                        System.out.println("3. Print Population Report By Region");
                        System.out.println("4. Print Population Report By District");
                        System.out.println("5. Print Population Report By Country");
                        System.out.println("6. Print Population Report By City");
                        System.out.println("7. BACK");

                        System.out.print("User Choice: ");

                        while (!input.hasNextInt()) {
                            System.out.print("User Choice: ");
                            input.next();
                        }

                        pop_choice = input.nextInt();
                        Integer n = 0;

                        switch(pop_choice)
                        {
                            case 1:
                                PopulationSummary worldPop = a.getPopulation("world", "");
                                a.printPopulation(worldPop);
                                break;
                            case 2:
                                System.out.print("\nEnter Continent: ");
                                String continentName = input.next();
                                PopulationSummary contPop = a.getPopulation("continent",continentName);
                                a.printPopulation(contPop);
                                break;
                            case 3:
                                System.out.print("\nEnter Region: ");
                                String regionName = input.next();
                                PopulationSummary regionPop = a.getPopulation("region",regionName);
                                a.printPopulation(regionPop);
                                break;
                            case 4:
                                System.out.print("\nEnter District: ");
                                String districtName = input.next();
                                PopulationSummary districtPop = a.getPopulation("district",districtName);
                                a.printPopulation(districtPop);
                                break;
                            case 5:
                                System.out.print("\nEnter Country: ");
                                String countryName = input.next();
                                PopulationSummary countryPop = a.getPopulation("country",countryName);
                                a.printPopulation(countryPop);
                                break;
                            case 6:
                                System.out.print("\nEnter City: ");
                                String cityName = input.next();
                                PopulationSummary cityPop = a.getPopulation("city",cityName);
                                a.printPopulation(cityPop);
                                break;
                        }
                    } while(pop_choice != 7);
                    if(pop_choice == 7)
                    {
                        System.out.println("Exiting Population Report Menu...");
                    }
                    break;
                case 6:
                    int popsum_choice = 0;
                    do {
                        // Population Summary Menu System
                        System.out.println("\nPOPULATION SUMMARY MENU");
                        System.out.println("--------------------");
                        System.out.println("1. Print World Population Summary");
                        System.out.println("2. Print Population Summary By Continent");
                        System.out.println("3. Print Population Summary By Region");
                        System.out.println("4. Print Population Summary By Country");
                        System.out.println("5. BACK");

                        System.out.print("User Choice: ");

                        while (!input.hasNextInt()) {
                            System.out.print("User Choice: ");
                            input.next();
                        }

                        popsum_choice = input.nextInt();
                        Integer n = 0;

                        switch(popsum_choice)
                        {
                            case 1:
                                PopulationSummary worldPopSum = a.getPopulationSummary("world","");
                                a.printPopulationSummary(worldPopSum);
                                break;
                            case 2:
                                System.out.print("\nEnter Continent: ");
                                String continentName = input.next();
                                PopulationSummary contPopSum = a.getPopulationSummary("continent",continentName);
                                a.printPopulationSummary(contPopSum);
                                break;
                            case 3:
                                System.out.print("\nEnter Region: ");
                                String regionName = input.next();
                                PopulationSummary regionPopSum = a.getPopulationSummary("region",regionName);
                                a.printPopulationSummary(regionPopSum);
                                break;
                            case 4:
                                System.out.print("\nEnter Country: ");
                                String countryName = input.next();
                                PopulationSummary countryPopSum = a.getPopulationSummary("country",countryName);
                                a.printPopulationSummary(countryPopSum);
                                break;
                        }
                    } while(popsum_choice != 5);
                    if(popsum_choice == 6)
                    {
                        System.out.println("Exiting Population Summary Menu...");
                    }
                    break;
            }
        } while (choice != 7);
        if(choice == 7)
        {
            System.out.println("Exiting App..");
        }

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

    /**
     * Retrieve specified city details.
     * @param name - String for selected city name
     * @return city
     */
    public City getCity(String name) {
        if(name == null)
        {
            System.out.println("Name is null.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "WHERE Name = '" + name + "';";
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

    /**
     * Retrieve specified city details.
     * @param name - String for selected city name
     * @return city
     */
    public City getCapitalCity(String name) {
        if(name == null)
        {
            System.out.println("Name is null.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name AS Capital, country.Name AS Country, city.Population, city.CountryCode, city.District " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "WHERE city.Name = '" + name + "';";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                City city = new City();
                city.id = rset.getInt("city.ID");
                city.name = rset.getString("Capital");
                city.country_code = rset.getString("CountryCode");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                return city;
            } else
                return null;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Retrieve specified country details.
     * @param name - String for selected country name
     * @return
     */
    public Country getCountry(String name) {
        if(name == null)
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
                            + "WHERE Name = '" + name + "'";
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

    /**
     * Displays a single country variables details.
     * @param country - Country variable to be displayed
     */
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

    /**
     * Displays a single city variables details.
     * @param city - Country variable to be displayed
     */
    public static void displayCity(City city) {
        if (city != null) {
            System.out.println(
                    city.id + "\n"
                            + city.name + "\n"
                            + city.country_code + "\n"
                            + city.district + "\n"
                            + city.country + "\n"
                            + "Population: " + city.population + "\n");
        }
        else{
            System.out.println("City is null");
        }
    }

    /**
     * Displays a single capital city variables details.
     * @param capitalCity - Country variable to be displayed
     */
    public static void displayCapitalCity(City capitalCity) {
        if (capitalCity != null) {
            System.out.println(
                    capitalCity.id + "\n"
                            + capitalCity.name + "\n"
                            + capitalCity.country_code + "\n"
                            + capitalCity.district + "\n"
                            + "Population: " + capitalCity.population + "\n");
        }
        else{
            System.out.println("Capital City is null");
        }
    }

    /**
     * Retrieves details for all countries and stores them in an ArrayList.
     * @return countries
     */
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

    /**
     * Retrieves details for all countries in specified continent and stores them in an ArrayList.
     * @param continent - String for continent to filter by
     * @return countries
     */
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

    /**
     * Retrieves details for all countries for specified region and stores them in an ArrayList.
     * @param region - String for region to filter by
     * @return countries
     */
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

    /**
     * Returns ArrayList of top 'n' countries (where 'n' is user-input)
     * @return countries
     */
    public ArrayList<Country> getTopCountries() {
        try {
            Statement stmt = con.createStatement();

            // Get user input
            Scanner lineRead = new Scanner(System.in);
            System.out.print("Enter Number Of Lines To Output: ");
            Integer n = lineRead.nextInt();

            // Check if input is null
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

    /**
     * Returns ArrayList of top 'n' countries in specified continent (where 'n' is user-input)
     * @param continent - String for continent to filter by
     * @return countries
     */
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

    /**
     * Returns ArrayList of top 'n' countries in specified region (where 'n' is user-input)
     * @param region - String for region to filter by
     * @return countries
     */
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
    public ArrayList<CountryLanguage> getLanguageReport() {
        ArrayList<CountryLanguage> reports = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT cl.Language, " +
                            "       SUM((c.Population * cl.Percentage) / 100) AS TotalSpeakers, " +
                            "       (SUM((c.Population * cl.Percentage) / 100) / " +
                            "        (SELECT SUM(Population) FROM country)) * 100 AS WorldPercentage " +
                            "FROM countrylanguage cl " +
                            "JOIN country c ON c.Code = cl.CountryCode " +
                            "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                            "GROUP BY cl.Language " +
                            "ORDER BY TotalSpeakers DESC;";

            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                CountryLanguage cl = new CountryLanguage();
                cl.language = rset.getString("Language");
                cl.total_speakers = rset.getInt("TotalSpeakers");
                cl.percentage = rset.getDouble("WorldPercentage");
                reports.add(cl);
            }

            rset.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language report");
        }

        return reports;
    }

    /**
     * Prints a list of language reports.
     */
    public void printLanguageReport(ArrayList<CountryLanguage> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("No language data.");
            return;
        }

        System.out.println(String.format("%-15s %-20s %-15s", "Language", "Total Speakers", "World %"));

        for (CountryLanguage cl : reports) {
            if (cl == null)
                continue;

            System.out.println(String.format("%-15s %-20s %-15.2f",
                    cl.language, cl.total_speakers, cl.percentage));
        }
    }

    /**
     * Returns a population summary containing the area name and total population.
     * @param type - specifies area type (world/continent/region/country/district/city)
     * @param area - area name for WHERE queries
     * @return PopulationSummary ps
     */
    public PopulationSummary getPopulation(String type, String area) {
        if(type == null)
        {
            System.out.println("Area is null.");
            return null;
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String SlctQuery = "";
            switch(type)
            {
                case "world":
                    SlctQuery = "SELECT SUM(Population) AS TotalPop FROM country;";
                    break;
                case "continent":
                    SlctQuery = "SELECT SUM(Population) AS TotalPop FROM country WHERE Continent = '"+area+"';";
                    break;
                case "region":
                    SlctQuery = "SELECT SUM(Population) AS TotalPop FROM country WHERE Region = '"+area+"';";
                    break;
                case "country":
                    SlctQuery = "SELECT SUM(Population) AS TotalPop FROM country WHERE Name = '"+area+"';";
                    break;
                case "district":
                    SlctQuery = "SELECT SUM(Population) AS TotalPop FROM city" +
                            " WHERE District = '"+area+"';";
                    break;
                case "city":
                    SlctQuery = "SELECT SUM(Population) AS TotalPop FROM city WHERE Name = '"+area+"';";
                    break;
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(SlctQuery);
            // Return new employee if valid.
            // Check one is returned


            if (rset.next()) {
                PopulationSummary ps = new PopulationSummary();
                ps.areaName = area;
                ps.totalPop = rset.getLong("TotalPop");
                return ps;
            } else
                return null;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Prints the area name and the total population to console.
     * @param pop - PopulationSummary object containing areaName and total population info
     */
    public void printPopulation(PopulationSummary pop)
    {
        try{
            System.out.println("Total Population of " + pop.areaName + ": " + pop.totalPop);
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to print population details.");
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
    public ArrayList<City> getCitiesByArea(String areaType, String areaName, int limit) {
        if (areaType == null || limit <= 0)
            return null;

        ArrayList<City> cities = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String sql;

            if (areaType.equalsIgnoreCase("world")) {
                sql = "SELECT ci.ID, ci.Name, ci.CountryCode, ci.District, ci.Population, c.Name AS Country " +
                        "FROM city ci " +
                        "JOIN country c ON ci.CountryCode = c.Code " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT " + limit;
            } else if (areaType.equalsIgnoreCase("continent")) {
                sql = "SELECT ci.ID, ci.Name, ci.CountryCode, ci.District, ci.Population, c.Name AS Country " +
                        "FROM city ci " +
                        "JOIN country c ON ci.CountryCode = c.Code " +
                        "WHERE c.Continent = '" + areaName + "' " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT " + limit;
            } else if (areaType.equalsIgnoreCase("region")) {
                sql = "SELECT ci.ID, ci.Name, ci.CountryCode, ci.District, ci.Population, c.Name AS Country " +
                        "FROM city ci " +
                        "JOIN country c ON ci.CountryCode = c.Code " +
                        "WHERE c.Region = '" + areaName + "' " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT " + limit;
            } else if (areaType.equalsIgnoreCase("country")) {
                sql = "SELECT ci.ID, ci.Name, ci.CountryCode, ci.District, ci.Population, c.Name AS Country " +
                        "FROM city ci " +
                        "JOIN country c ON ci.CountryCode = c.Code " +
                        "WHERE c.Name = '" + areaName + "' " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT " + limit;
            } else if (areaType.equalsIgnoreCase("district")) {
                sql = "SELECT ID, Name, CountryCode, District, Population " +
                        "FROM city " +
                        "WHERE District = '" + areaName + "' " +
                        "ORDER BY Population DESC " +
                        "LIMIT " + limit;
            } else {
                return null;
            }

            ResultSet rset = stmt.executeQuery(sql);

            while (rset.next()) {
                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.country_code = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                try {
                    city.country = rset.getString("Country");
                } catch (Exception e) {
                    city.country = null;
                }
                cities.add(city);
            }

            rset.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report");
            return null;
        }

        return cities;
    }

    public void printCities(ArrayList<City> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities to display.");
            return;
        }

        System.out.println(String.format("%-40s %-25s %-15s %-20s",
                "City", "District", "Population", "Country"));

        for (City city : cities) {
            if (city == null)
                continue;

            System.out.println(String.format("%-40s %-25s %-15d %-20s",
                    city.name,
                    city.district,
                    city.population,
                    city.country != null ? city.country : city.country_code));
        }
    }

    /**
     * Produces a report containing the population of an area
     * (area name, total population, population in cities, % in cities,
     * population outside cities, % outside cities)
     */
    public PopulationSummary getPopulationSummary(String areaType, String areaName) {
        if (areaType == null)
            return null;

        PopulationSummary ps = new PopulationSummary();
        ps.areaType = areaType;
        ps.areaName = areaName;

        try {
            Statement stmt = con.createStatement();
            String sqlTotal = "";
            String sqlCity = "";

            switch (areaType.toLowerCase()) {
                case "world":
                    sqlTotal = "SELECT SUM(Population) AS TotalPop FROM country";
                    sqlCity = "SELECT SUM(Population) AS CityPop FROM city";
                    break;

                case "continent":
                    sqlTotal = "SELECT SUM(Population) AS TotalPop FROM country WHERE Continent = '" + areaName + "'";
                    sqlCity = "SELECT SUM(ci.Population) AS CityPop " +
                            "FROM city ci JOIN country c ON ci.CountryCode = c.Code " +
                            "WHERE c.Continent = '" + areaName + "'";
                    break;

                case "region":
                    sqlTotal = "SELECT SUM(Population) AS TotalPop FROM country WHERE Region = '" + areaName + "'";
                    sqlCity = "SELECT SUM(ci.Population) AS CityPop " +
                            "FROM city ci JOIN country c ON ci.CountryCode = c.Code " +
                            "WHERE c.Region = '" + areaName + "'";
                    break;

                case "country":
                    sqlTotal = "SELECT Population AS TotalPop FROM country WHERE Name = '" + areaName + "'";
                    sqlCity = "SELECT SUM(Population) AS CityPop " +
                            "FROM city WHERE CountryCode = (SELECT Code FROM country WHERE Name = '" + areaName + "')";
                    break;
                default:
                    return null;
            }

            ResultSet totalSet = stmt.executeQuery(sqlTotal);
            if (totalSet.next())
                ps.totalPop = totalSet.getLong("TotalPop");

            ResultSet citySet = stmt.executeQuery(sqlCity);
            if (citySet.next())
                ps.cityPop = citySet.getLong("CityPop");

            ps.nonCityPop = ps.totalPop - ps.cityPop;
            ps.pctCity = (ps.totalPop == 0) ? 0 : ((double) ps.cityPop / ps.totalPop) * 100;
            ps.pctNonCity = (ps.totalPop == 0) ? 0 : ((double) ps.nonCityPop / ps.totalPop) * 100;

            totalSet.close();
            citySet.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population summary");
            return null;
        }

        return ps;
    }

    public void printPopulationSummary(PopulationSummary ps) {
        if (ps == null) {
            System.out.println("No summary available.");
            return;
        }

        System.out.println("\n=== Population Summary Report ===\n");

        if (ps.areaType.equalsIgnoreCase("world"))
            System.out.println("Area: World");
        else
            System.out.println("Area: " + ps.areaName + " (" + ps.areaType + ")");

        System.out.println(String.format("%-25s %-15d", "Total Population:", ps.totalPop));
        System.out.println(String.format("%-25s %-15d (%.2f%%)", "In Cities:", ps.cityPop, ps.pctCity));
        System.out.println(String.format("%-25s %-15d (%.2f%%)", "Outside Cities:", ps.nonCityPop, ps.pctNonCity));
    }

    public void getPopulationSummaryReport() {
        try {
            Scanner in = new Scanner(System.in);

            System.out.print("Enter area type (world, continent, region, country, district): ");
            String areaType = in.nextLine();

            String areaName = "";
            if (!areaType.equalsIgnoreCase("world")) {
                System.out.print("Enter the name of the " + areaType + ": ");
                areaName = in.nextLine();
            }

            PopulationSummary ps = getPopulationSummary(areaType, areaName);
            printPopulationSummary(ps);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to generate population summary report");
        }
    }


    /**
     * Produces a report of capital cities in a given area
     * (world, continent, or region) sorted by population (highest → lowest).
     */
    public ArrayList<City> getCapitalCities(String areaType, String areaName, int limit) {
        if (areaType == null || limit <= 0)
            return null;

        ArrayList<City> capitals = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String query;

            if (areaType.equalsIgnoreCase("world")) {
                query =
                        "SELECT city.ID, city.Name AS Capital, country.Name AS Country, city.Population, city.CountryCode, city.District " +
                                "FROM city " +
                                "JOIN country ON city.ID = country.Capital " +
                                "ORDER BY city.Population DESC " +
                                "LIMIT " + limit + ";";
            } else if (areaType.equalsIgnoreCase("continent")) {
                query =
                        "SELECT city.ID, city.Name AS Capital, country.Name AS Country, city.Population, city.CountryCode, city.District " +
                                "FROM city " +
                                "JOIN country ON city.ID = country.Capital " +
                                "WHERE country.Continent = '" + areaName + "' " +
                                "ORDER BY city.Population DESC " +
                                "LIMIT " + limit + ";";
            } else if (areaType.equalsIgnoreCase("region")) {
                query =
                        "SELECT city.ID, city.Name AS Capital, country.Name AS Country, city.Population, city.CountryCode, city.District " +
                                "FROM city " +
                                "JOIN country ON city.ID = country.Capital " +
                                "WHERE country.Region = '" + areaName + "' " +
                                "ORDER BY city.Population DESC " +
                                "LIMIT " + limit + ";";
            } else {
                return null;
            }

            ResultSet rset = stmt.executeQuery(query);

            while (rset.next()) {
                City c = new City();
                c.id = rset.getInt("ID");
                c.name = rset.getString("Capital");
                c.country = rset.getString("Country");
                c.population = rset.getInt("Population");
                c.country_code = rset.getString("CountryCode");
                c.district = rset.getString("District");
                capitals.add(c);
            }

            rset.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities report");
            return null;
        }

        return capitals;
    }

    public void printCapitalCities(ArrayList<City> capitals)
    {
        if (capitals == null || capitals.isEmpty())
        {
            System.out.println("No capital cities to display.");
            return;
        }

        System.out.println("\n=== Capital Cities Report ===\n");
        System.out.println(String.format("%-35s %-35s %-15s",
                "Capital City", "Country", "Population"));

        for (City c : capitals)
        {
            if (c == null)
                continue;

            System.out.println(String.format("%-35s %-35s %-15d",
                    c.name, c.country, c.population));
        }
    }
}

