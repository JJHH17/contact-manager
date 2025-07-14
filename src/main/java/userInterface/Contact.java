package userInterface;

public class Contact {

    private String email;
    private String firstname;
    private String lastname;
    private int telephone;

    public Contact(String email, String firstname, String lastname, int telephone) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
