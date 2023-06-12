package org.example;

import org.example.models.Character;
import org.example.models.Job;
import org.example.models.Property;
import org.example.models.User;

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

                    Information.users.add(new User(username,pass));
                }
                userRS.close();

                ResultSet jobRS = stmt.executeQuery("SELECT * FROM jobs");
                while (jobRS.next()) {
                    String title = jobRS.getString("title");
                    float income = jobRS.getFloat("income")
                    int id = jobRS.getInt("jobid");
                    int industryid = jobRS.getInt("industryid");

                    Job job = new Job(title,income,industryid);
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

                    Information.characters.add(new Character(username,jobid,ids,inpositionid));
                }
                characterRS.close();

                ResultSet serviceMenRS = stmt.executeQuery("SELECT * FROM servicemen");
                while (serviceMenRS.next()) {
                    String firstName = serviceMenRS.getString("firstName");
                    String lastName = serviceMenRS.getString("lastName");
                    String age = serviceMenRS.getString("age");
                    String nationalID = serviceMenRS.getString("NationalID");
                    String gender = serviceMenRS.getString("gender");
                    String salary = serviceMenRS.getString("salary");
                    String workExperience = serviceMenRS.getString("workExperience");
                    int id = serviceMenRS.getInt("id");

                    ServiceMen serviceMen = new ServiceMen(salary, workExperience, firstName, lastName,
                            age, nationalID, gender);
                    serviceMen.setId(id);
                    Hospital.plus(serviceMen);
                }
                serviceMenRS.close();

                ResultSet visitRS = stmt.executeQuery("SELECT * FROM visit");
                while (visitRS.next()) {
                    String patientNationalID = visitRS.getString("patientNationalID");
                    String doctorNationalID = visitRS.getString("doctorNationalID");
                    String nurseNationalID = visitRS.getString("nurseNationalID");
                    String date = visitRS.getString("date");
                    String sickness = visitRS.getString("sickness");
                    String prescription = visitRS.getString("prescription");
                    int id = visitRS.getInt("id");

                    Visit visit = new Visit(patientNationalID, doctorNationalID,
                            nurseNationalID, date, sickness, prescription);
                    visit.setId(id);
                    Hospital.plus(visit);
                }
                visitRS.close();

                ResultSet passRS = stmt.executeQuery("SELECT * FROM ownerpass");
                while (passRS.next()) {
                    String p = passRS.getString("pass");
                    Hospital.setOwnerPassword(p);
                }
                passRS.close();
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
                        " title, income, industryid) VALUES (?, ?, ?, ?)");
                for (Job j : Information.jobs) {
                    jobPS.setInt(1, j.getId());
                    jobPS.setString(2, j.getTitle());
                    jobPS.setFloat(3, j.getIncome());
                    jobPS.setInt(4, j.getIndustryId());
                    jobPS.executeUpdate();
                }

                // Truncate the characters table to remove any existing data
                stmt.executeUpdate("TRUNCATE TABLE characters");

                // Insert all characters from the array list into the characters table
                PreparedStatement characterPS = conn.prepareStatement("INSERT INTO characters(username," +
                        " jobid, propertiesid, inpositionid) VALUES (?, ?, ?, ?)");
                for (Character c: Information.characters) {
                    String s = "";
                    for (Property p:c.getProperties()) {
                        s = s.concat(Integer.toString(p.getId()));
                    }
                    characterPS.setString(1, c.getUserInfo().getUsername());
                    characterPS.setInt(2, c.getJob().getId());
                    characterPS.setString(3, s);
                    characterPS.setInt(4, c.getInPosition().getId());
                    characterPS.executeUpdate();
                }

                // Truncate the servicemen table to remove any existing data
                stmt.executeUpdate("TRUNCATE TABLE servicemen");

                // Insert all servicemens from the array list into the patient table
                PreparedStatement serviceMenPS = conn.prepareStatement("INSERT INTO serviceMen" +
                        "(firstName,lastName, age, NationalID, gender, salary, workExperience, id)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                for (ServiceMen s : Hospital.getServiceMens()) {
                    serviceMenPS.setString(1, s.firstName);
                    serviceMenPS.setString(2, s.lastName);
                    serviceMenPS.setString(3, s.age);
                    serviceMenPS.setString(4, s.NationalID);
                    serviceMenPS.setString(5, s.gender);
                    serviceMenPS.setString(6, s.getSalary());
                    serviceMenPS.setString(7, s.getWorkExperience());
                    serviceMenPS.setInt(8, s.id);
                    serviceMenPS.executeUpdate();
                }

                // Truncate the visit table to remove any existing data
                stmt.executeUpdate("TRUNCATE TABLE visit");

                // Insert all visits from the array list into the patient table
                PreparedStatement visitPS = conn.prepareStatement("INSERT INTO visit" +
                        "(patientNationalID,doctorNationalID, nurseNationalID, date, sickness," +
                        " prescription, id)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)");
                for (Visit v : Hospital.getVisits()) {
                    visitPS.setString(1, v.getPatientNationalID());
                    visitPS.setString(2, v.getDoctorNationalID());
                    visitPS.setString(3, v.getNurseNationalID());
                    visitPS.setString(4, v.getDate());
                    visitPS.setString(5, v.getSickness());
                    visitPS.setString(6, v.getPrescription());
                    visitPS.setInt(7, v.getId());
                    visitPS.executeUpdate();
                }

                // Truncate the ownerpass table to remove any existing data
                stmt.executeUpdate("TRUNCATE TABLE ownerpass");

                // Insert all visits from the array list into the patient table
                PreparedStatement passPS = conn.prepareStatement("INSERT INTO ownerpass" +
                        "(pass) VALUES (?)");
                passPS.setString(1, Hospital.getOwnerPassword());
                passPS.executeUpdate();

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
