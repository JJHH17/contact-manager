package userInterface;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class UserInterface {
    public static void main(String[] args) {

        Database db = new Database();
        // Authentication
        db.authenticate();
        db.createTable();
        db.addUser("james.h@email.com", "James", "Hatfield");
        db.addUser("Samantha@email.com", "Samantha", "P");
    }
}
