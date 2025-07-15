package userInterface;

import java.util.Scanner;

public class UserInterface {

    private Database db = new Database();


    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }


    public void start() {
        Scanner input = new Scanner(System.in);
        // Authentication and creation of Postgresql table
        this.db.authenticate();
        this.db.createTable();

        while (true) {
            System.out.println("'quit' to exit program");
            System.out.println("Enter an email address");
            String emailAddress = input.nextLine();

            if (emailAddress.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter a first name");
            String firstName = input.nextLine();

            if (firstName.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter an last name");
            String lastName = input.nextLine();

            if (lastName.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter a telephone number (max 8 characters)");
            // Parses to string initially then converted after (once we've checked exit criteria)
            String telephoneNumber = input.nextLine();
            // TODO: Add data type validation here (when split into method)

            if (telephoneNumber.equalsIgnoreCase("quit")) {
                break;
            }

            this.addUser(emailAddress, firstName, lastName, Integer.parseInt(telephoneNumber));
        }
        input.close();
    }


    public void addUser(String email, String firstname, String lastname, int phoneNumber) {
        // TODO - Add check for duplicate items; rejects entry if they exist

        if (Integer.toString(phoneNumber).length() == 8) {
            Contact newUser = new Contact(email, firstname, lastname, phoneNumber);
            // Adds to DB
            this.db.addUser(email, firstname, lastname, phoneNumber);

        } else {
            System.out.println("Please enter an 8 digit phone number");
        }
    }

}
