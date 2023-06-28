package org.example;

import org.example.defualtSystem.Life;
import org.example.models.Character;
import org.example.models.*;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static String url;
    private static String user = "root";
    private static String password = "mahdi_ramezani1234";

    public static void read() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://127.0.0.1:3306/lightcity";

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet userRS = stmt.executeQuery("SELECT * FROM users");

            while (userRS.next()) {
                String username = userRS.getString("username");
                String pass = userRS.getString("password");

                Information.users.add(new User(username, pass));
            }
            userRS.close();

            ResultSet jobRS = stmt.executeQuery("SELECT * FROM jobs");
            while (jobRS.next()) {
                String title = jobRS.getString("title");
                float income = jobRS.getFloat("income");
                int id = jobRS.getInt("jobid");
                int industryid = jobRS.getInt("industryid");
                float level = jobRS.getFloat("level");

                Job job = new Job(title, income, industryid, level);
                job.setId(id);
                Information.jobs.add(job);
            }
            jobRS.close();

            ResultSet characterRS = stmt.executeQuery("SELECT * FROM characters");
            while (characterRS.next()) {
                String username = characterRS.getString("username");
                int jobid = characterRS.getInt("jobid");
                String propertiesid = characterRS.getString("propertiesid");
                int inpositionid = characterRS.getInt("inpositionid");

                String[] wordsArray = propertiesid.split(" ");
                ArrayList<Integer> ids = new ArrayList<>();

                for (String word : wordsArray) {
                    ids.add(Integer.parseInt(word));
                }

                Information.characters.add(new Character(username, jobid, ids, inpositionid));
            }
            characterRS.close();

            ResultSet propertiesRS = stmt.executeQuery("SELECT * FROM properties");
            while (propertiesRS.next()) {
                String ownerusername = propertiesRS.getString("ownerusername");
                String coordinate = propertiesRS.getString("coordinate");
                String scales = propertiesRS.getString("scales");
                int id = propertiesRS.getInt("id");
                float price = propertiesRS.getFloat("price");

                String[] coordinateArray = coordinate.split(" ");
                ArrayList<Float> coordinates = new ArrayList<>();

                for (String s : coordinateArray) {
                    coordinates.add(Float.parseFloat(s));
                }

                String[] scaleArray = scales.split(" ");
                ArrayList<Float> floatScale = new ArrayList<>();

                for (String s : scaleArray) {
                    floatScale.add(Float.parseFloat(s));
                }

                float[] finalScale = {floatScale.get(0), floatScale.get(1)};
                float[] finalCoordinate = {coordinates.get(0), coordinates.get(1)};

                Information.properties.add(new Property(finalScale, finalCoordinate, ownerusername, id, price));
            }
            propertiesRS.close();

            ResultSet industrysRS = stmt.executeQuery("SELECT * FROM industrys");
            while (industrysRS.next()) {
                int id = industrysRS.getInt("propertyid");
                float income = industrysRS.getFloat("income");
                String title = industrysRS.getString("title");
                Industry industry = new Industry(title, income, id);
                String require = industrysRS.getString("request");
                String[] requireArray = require.split(" ");
                int requireId;
                for (String s:requireArray) {
                    requireId = Integer.parseInt(s);
                    for (Job j:Information.jobs) {
                        if (j.getId()==requireId)
                            industry.addRequiredJob(j);
                    }
                }

                Information.industry.add(industry);
            }
            industrysRS.close();

            ResultSet lifeRS = stmt.executeQuery("SELECT * FROM lives");
            while (lifeRS.next()) {
                String username = lifeRS.getString("username");
                float sleep = lifeRS.getFloat("sleep");
                float water = lifeRS.getFloat("water");
                float food = lifeRS.getFloat("food");

                Information.lives.add(new Life(food, water, sleep, username));
            }
            lifeRS.close();

            ResultSet foodsRS = stmt.executeQuery("SELECT * FROM foods");
            while (foodsRS.next()) {
                int id = foodsRS.getInt("id");
                String title = foodsRS.getString("title");
                float water = foodsRS.getFloat("water");
                float food = foodsRS.getFloat("food");
                float price = foodsRS.getFloat("price");
                int isAvailable = foodsRS.getInt("available");
                boolean available;

                available = isAvailable != 0;

                Information.foods.add(new Food(title, water, food, id, available, price));
            }
            foodsRS.close();

            ResultSet liquidsRS = stmt.executeQuery("SELECT * FROM liquids");
            while (liquidsRS.next()) {
                int id = liquidsRS.getInt("id");
                float liquid = liquidsRS.getFloat("liquid");
                int isAvailable = liquidsRS.getInt("available");
                boolean available;
                float price = liquidsRS.getFloat("price");

                available = isAvailable != 0;

                Information.liquids.add(new Liquid(liquid, id, available));
            }
            liquidsRS.close();

            ResultSet bankaccountsRS = stmt.executeQuery("SELECT * FROM bankaccounts");
            while (bankaccountsRS.next()) {
                String ownerusername = bankaccountsRS.getString("ownerusername");
                float money = bankaccountsRS.getFloat("money");
                Date lastchange = bankaccountsRS.getDate("lastchange");

                Information.bankAccounts.add(new BankAccount(ownerusername, money, lastchange));
            }
            bankaccountsRS.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void write() {
        url = "jdbc:mysql://127.0.0.1:3306/information?useSSL=false";

        // Create a connection to the MySQL database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // Truncate the users table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE users");

            // Insert all users from the array list into the users table
            PreparedStatement userPS = conn.prepareStatement("INSERT INTO users(username," +
                    "password) VALUES (?, ?)");
            for (User u : Information.users) {
                userPS.setString(1, u.getUsername());
                userPS.setString(2, u.getPassword());
                userPS.executeUpdate();
            }

            // Truncate the jobs table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE jobs");

            // Insert all jobs from the array list into the jobs table
            PreparedStatement jobPS = conn.prepareStatement("INSERT INTO jobs(jobid," +
                    " title, income, industryid, level) VALUES (?, ?, ?, ?, ?)");
            for (Job j : Information.jobs) {
                jobPS.setInt(1, j.getId());
                jobPS.setString(2, j.getTitle());
                jobPS.setFloat(3, j.getIncome());
                jobPS.setInt(4, j.getIndustryId());
                jobPS.setFloat(5, j.getLevel());
                jobPS.executeUpdate();
            }

            // Truncate the characters table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE characters");

            // Insert all characters from the array list into the characters table
            PreparedStatement characterPS = conn.prepareStatement("INSERT INTO characters(username," +
                    " jobid, propertiesid, inpositionid) VALUES (?, ?, ?, ?)");
            for (Character c : Information.characters) {
                String s = "";
                for (Property p : c.getProperties()) {
                    s = s.concat(Integer.toString(p.getId()));
                    s = s.concat(" ");
                }
                characterPS.setString(1, c.getUserInfo().getUsername());
                characterPS.setInt(2, c.getJob().getId());
                characterPS.setString(3, s);
                characterPS.setInt(4, c.getInPosition().getId());
                characterPS.executeUpdate();
            }

            // Truncate the properties table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE properties");

            // Insert all properties from the array list into the properties table
            PreparedStatement propertiesPS = conn.prepareStatement("INSERT INTO properties" +
                    "(id,ownerusername, coordinate, scales, price) VALUES (?, ?, ?, ?, ?)");
            for (Property p : Information.properties) {
                propertiesPS.setInt(1, p.getId());
                propertiesPS.setString(2, p.getOwner().getUserInfo().getUsername());

                String coordinateAsString = "";
                coordinateAsString = coordinateAsString.concat(Float.toString(p.getCoordinate()[0]));
                coordinateAsString = coordinateAsString.concat(" ");
                coordinateAsString = coordinateAsString.concat(Float.toString(p.getCoordinate()[1]));
                coordinateAsString = coordinateAsString.concat(" ");
                propertiesPS.setString(3, coordinateAsString);

                String scaleAsString = "";
                scaleAsString = scaleAsString.concat(Float.toString(p.getScales()[0]));
                scaleAsString = scaleAsString.concat(" ");
                scaleAsString = scaleAsString.concat(Float.toString(p.getScales()[1]));
                scaleAsString = scaleAsString.concat(" ");
                propertiesPS.setString(4, scaleAsString);
                propertiesPS.setFloat(5,p.getPropertyPrice());

                propertiesPS.executeUpdate();
            }

            // Truncate the industrys table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE industrys");

            // Insert all industrys from the array list into the industrys table
            PreparedStatement industrysPS = conn.prepareStatement("INSERT INTO industrys" +
                    "(propertyid,income, title, request) VALUES (?, ?, ?, ?)");
            for (Industry i : Information.industry) {
                industrysPS.setInt(1, i.getId());
                industrysPS.setFloat(2, i.getIncome());
                industrysPS.setString(3, i.getTitle());
                String s = "";
                for (Job j:i.getRequiredJobs()) {
                    s = s.concat(Integer.toString(j.getId()));
                    s = s.concat(" ");
                }
                industrysPS.setString(4,s);
                industrysPS.executeUpdate();
            }

            // Truncate the lives table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE lives");

            // Insert all lives from the array list into the lives table
            PreparedStatement livesPS = conn.prepareStatement("INSERT INTO lives" +
                    "(username,sleep, water, food) VALUES (?, ?, ?, ?)");
            for (Life l : Information.lives) {
                livesPS.setString(1, l.getUsername());
                livesPS.setFloat(2, l.getSleep());
                livesPS.setFloat(3, l.getWater());
                livesPS.setFloat(4, l.getFood());

                livesPS.executeUpdate();
            }

            // Truncate the liquids table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE liquids");

            // Insert all liquids from the array list into the liquids table
            PreparedStatement liquidsPS = conn.prepareStatement("INSERT INTO liquids" +
                    "(id,liquid, available, price) VALUES (?, ?, ?, ?)");
            for (Liquid l : Information.liquids) {
                liquidsPS.setInt(1, l.getId());
                liquidsPS.setFloat(2, l.getLiquid());
                int intAvailable;

                if (l.isAvailable())
                    intAvailable = 1;
                else
                    intAvailable = 0;
                liquidsPS.setInt(3, intAvailable);
                liquidsPS.setFloat(4, l.consumerPrice);

                liquidsPS.executeUpdate();
            }

            // Truncate the foods table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE foods");

            // Insert all liquids from the array list into the liquids table
            PreparedStatement foodsPS = conn.prepareStatement("INSERT INTO foods" +
                    "(id,title,water,food, available, price) VALUES (?, ?, ?, ?, ?, ?)");
            for (Food f : Information.foods) {
                foodsPS.setInt(1, f.getId());
                foodsPS.setString(2, f.getTitle());
                foodsPS.setFloat(3, f.getWater());
                foodsPS.setFloat(4, f.getFood());
                int intAvailable;

                if (f.isAvailable())
                    intAvailable = 1;
                else
                    intAvailable = 0;
                foodsPS.setInt(5, intAvailable);
                foodsPS.setFloat(6, f.consumerPrice);

                foodsPS.executeUpdate();
            }

            // Truncate the bankaccounts table to remove any existing data
            stmt.executeUpdate("TRUNCATE TABLE bankaccounts");

            // Insert all bankaccounts from the array list into the bankaccounts table
            PreparedStatement bankaccountsPS = conn.prepareStatement("INSERT INTO bankaccounts" +
                    "(ownerusername,money,lastchange) VALUES (?,?,?)");
            for (BankAccount b : Information.bankAccounts) {
                bankaccountsPS.setString(1, b.getOwner());
                bankaccountsPS.setFloat(2, b.getMoney());
                bankaccountsPS.setDate(3, (Date) b.getLastChange());


                bankaccountsPS.executeUpdate();
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}