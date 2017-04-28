package main.models.pojo;

public class User {

    Integer id;
    String firstName;
    String secondName;
    String lastName;
    String phoneNumber;
    String email;
    boolean isAdmin;
    boolean isActive;
    boolean isDeleted;

    public User() {
    }

    public User(String firstName, String secondName, String lastName,
                String phoneNumber, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(int id, String firstName, String secondName, String lastName,
                String phoneNumber, String email,
                boolean isAdmin, boolean isActive, boolean isDeleted) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String value) {
        this.secondName = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean value) {
        this.isAdmin = value;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean value) {
        this.isDeleted = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
