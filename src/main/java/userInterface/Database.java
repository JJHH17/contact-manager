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

        // Creating an example call to create a table if it doesnt exist
        String sql = "CREATE TABLE IF NOT EXISTS contactManager (" +
                "ID SERIAL PRIMARY KEY, " +
                "Email varchar(50) NOT NULL, " +
                "FirstName varchar(30) NOT NULL, " +
                "LastName varchar(30) NOT NULL);";

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

    public void addUser(String email, String firstname, String lastname) {
        String sql = "INSERT INTO contactManager (Email, FirstName, LastName) VALUES (?, ?, ?)";

        // Attempting to prevent SQL injection
        try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement prep = con.prepareStatement(sql)) {

            prep.setString(1, email);
            prep.setString(2, firstname);
            prep.setString(3, lastname);
            prep.execute();
            System.out.println("Entry added successfully.");

        } catch (SQLException e) {
            System.out.println("An error occurred when writing these entries");
            e.printStackTrace();
        }
    }
}
