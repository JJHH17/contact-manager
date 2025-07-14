package userInterface;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class UserInterface {
    public static void main(String[] args) {
        // Pulling from credential file
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("db.properties")) {
            props.load(input);
        } catch (Exception e) {
            System.out.print("An error occurred when pulling credentials");
        }

        // Temporary credentials to test SQL connection
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        // Creating an example call to create a table if it doesnt exist
        String sql = "CREATE TABLE IF NOT EXISTS contactManager (" +
                "id SERIAL PRIMARY KEY, " +
                "email CHAR NOT NULL, " +
                "firstname CHAR NOT NULL, " +
                "lastname CHAR NOT NULL);";

        // Establishing a connection
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            // Where we execute query
            st.execute(sql);
            System.out.println("Table created (if it didn't already exist!)");

            // Closing the connection is extremely important...
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }


    }
}
