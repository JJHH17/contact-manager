package userInterface;

public class Contact {

    private String email;
    private String firstname;
    private String lastname;
    private String telephone;

    public Contact(String email, String firstname, String lastname, String telephone) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
