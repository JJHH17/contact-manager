package userInterface;

import java.util.Scanner;

public class UserInterface {

    private Database db = new Database();

    public void start() {
        Scanner input = new Scanner(System.in);
        // Authentication and creation of Postgresql table
        this.db.authenticate();
        this.db.createTable();

        // TODO: Main loop to be placed into its own class and called into the main method
        while (true) {
            System.out.println("'quit' to exit program");

            System.out.println("\n'Add' to add a user \n'Delete email' to delete a user with their email address\n'Delete name' to delete a user with their name \n" +
                    "'Update user' to update a users details \n'Print users' to print all users");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("quit")) {
                break;
            }

            if (choice.equalsIgnoreCase("add")) {
                System.out.println("Enter an email address");
                String emailAddress = input.nextLine();

                System.out.println("Enter a first name");
                String firstName = input.nextLine();

                System.out.println("Enter an last name");
                String lastName = input.nextLine();

                System.out.println("Enter a telephone number (max 8 characters)");
                // Parses to string initially then converted after (once we've checked exit criteria)
                int telephoneNumber = Integer.parseInt(input.nextLine());
                // TODO: Add data type validation here (when split into method)

                this.addUser(emailAddress, firstName, lastName, telephoneNumber);
            }

            if (choice.equalsIgnoreCase("delete email")) {
                System.out.println("Enter the email address of the user you wish to delete");
                String emailAddress = input.nextLine();

                this.db.deleteUser(emailAddress);
            }

            if (choice.equalsIgnoreCase("delete name")) {
                System.out.println("Enter the user firstname");
                String firstName = input.nextLine();

                System.out.println("Enter the user lastname");
                String lastName = input.nextLine();

                this.db.deleteUser(firstName, lastName);
            }

            if (choice.equalsIgnoreCase("update user")) {
                System.out.println("Enter the email address of the user you wish to update");
                String emailToUpdate = input.nextLine();

                System.out.println("Enter the new email address");
                String newEmailAddress = input.nextLine();

                System.out.println("Enter the new first name");
                String firstName = input.nextLine();

                System.out.println("Enter the new last name");
                String lastName = input.nextLine();

                System.out.println("Enter the new telephone number");
                int telephoneNumber = Integer.parseInt(input.nextLine());

                this.db.updateUser(emailToUpdate, newEmailAddress, firstName, lastName, telephoneNumber);
            }

            if (choice.equalsIgnoreCase("print users")) {
                this.db.returnUsers();
            }
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

    // Addition of deletion method (based on email)
    public void deleteUser(String email) {
        this.db.deleteUser(email);
    }


    // Addition of deletion method (based on firstname + lastname)
    public void deleteUser(String firstname, String lastname) {
        this.db.deleteUser(firstname, lastname);
    }

    // Addition of update method
    public void updateUser(String emailToUpdate, String newEmail, String firstName, String lastName, int telephoneNumber) {
        this.db.updateUser(emailToUpdate, newEmail, firstName, lastName, telephoneNumber);
    }
}
