package userInterface;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {

    private String url;
    private String username;
    private String password;


    public void authenticate() {
        // Pulling from credential file
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("db.properties")) {
            props.load(input);
        } catch (Exception e) {
            System.out.print("An error occurred when pulling credentials");
        }

        // Temporary credentials to test SQL connection
        this.url = props.getProperty("db.url");
        this.username = props.getProperty("db.username");
        this.password = props.getProperty("db.password");
    }


    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS contactManager (" +
                "ID SERIAL PRIMARY KEY, " +
                "Email varchar(50) NOT NULL, " +
                "FirstName varchar(30) NOT NULL, " +
                "LastName varchar(30) NOT NULL, " +
                "PhoneNumber integer NOT NULL);";

        // Establishing a connection
        try {
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            Statement st = con.createStatement();
            // Where we execute query
            st.execute(sql);
            System.out.println("Table created (if it didn't already exist!)");

            // Closing the connection is extremely important...
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }


    public void addUser(String email, String firstname, String lastname, int phoneNumber) {
        String sql = "INSERT INTO contactManager (Email, FirstName, LastName, PhoneNumber) VALUES (?, ?, ?, ?)";

        // Attempting to prevent SQL injection
        try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement prep = con.prepareStatement(sql)) {

            prep.setString(1, email);
            prep.setString(2, firstname);
            prep.setString(3, lastname);
            prep.setInt(4, phoneNumber);
            prep.execute();
            System.out.println("Entry added successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred when writing these entries");
            e.printStackTrace();
        }
    }


    public void deleteUser(String email) {
        String sql = "DELETE FROM contactManager WHERE Email=(?)";

        // Attempting to prevent injection
        try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement prep = con.prepareStatement(sql)) {

            prep.setString(1, email);
            int rowsDeleted = prep.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found with email address: " + email);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred when trying to delete.");
            e.printStackTrace();
        }
    }

    public void deleteUser(String firstname, String lastname) {
        String sql = "DELETE FROM contactManager WHERE FirstName = ? AND LastName = ?;";

        try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement prep = con.prepareStatement(sql)) {

            prep.setString(1, firstname);
            prep.setString(2, lastname);
            int rowsDeleted = prep.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User not found with name: " + firstname + " " + lastname);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred when trying to delete.");
            e.printStackTrace();
        }
    }

    public void updateUser(String emailAddress, String updatedEmail, String firstname, String lastName, int telephoneNumber) {
        String sql = "UPDATE contactManager SET Email = ?, FirstName = ?, LastName = ?, PhoneNumber = ? WHERE Email = ?;";

        try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement prep = con.prepareStatement(sql)) {

            prep.setString(1, updatedEmail);
            prep.setString(2, firstname);
            prep.setString(3, lastName);
            prep.setInt(4, telephoneNumber);
            prep.setString(5, emailAddress); // Email in WHERE call
            int rowsDeleted = prep.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("User not found with email address: " + emailAddress);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred when trying to update.");
            e.printStackTrace();
        }
    }

    public void returnUsers() {
        String sql = "SELECT Email, FirstName, LastName, PhoneNumber FROM contactManager";

        try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement prep = con.prepareStatement(sql);
             ResultSet results = prep.executeQuery()) {

            while (results.next()) {
                String email = results.getString("Email");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                int phoneNumber = results.getInt("PhoneNumber");

                System.out.println("Email: " + email + ", Name: " + firstName + " " + lastName + ", Telephone Number: " + phoneNumber);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred when printing users.");
            e.printStackTrace();
        }
    }
}
