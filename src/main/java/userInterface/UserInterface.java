package userInterface;


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
