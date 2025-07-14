package userInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserInterface {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // Temporary credentials to test SQL connection
        String url = "jdbc:postgresql://localhost:5432/ContactManager";
        String username = "postgres";
        String password = "";

        // Establishing a connection
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            // Where we execute query
            // st.executeQuery();

            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }


    }
}
