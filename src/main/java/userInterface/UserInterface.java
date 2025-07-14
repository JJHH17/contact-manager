package userInterface;


public class UserInterface {
    public static void main(String[] args) {

        Database db = new Database();
        // Authentication
        db.authenticate();
        db.createTable();
    }

    public void addUser(String email, String firstname, String lastname, int phoneNumber) {
        // phone numbers cannot be > or < 8 characters
        if (Integer.toString(phoneNumber).length() == 8) {
            Contact newUser = new Contact(email, firstname, lastname, phoneNumber);
            // Adds to DB
        } else {
            System.out.println("Please enter an 8 digit phone number");
        }
    }
}
