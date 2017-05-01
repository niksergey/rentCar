package main.models.pojo;

public class User {

    Integer id;
    String firstName;
    String secondName;
    String lastName;
    String phoneNumber;
    String email;
    boolean adminFlag;
    boolean activeFlag;
    boolean deletedFlag;

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
                boolean adminFlag, boolean activeFlag, boolean deletedFlag) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adminFlag = adminFlag;
        this.activeFlag = activeFlag;
        this.deletedFlag = deletedFlag;
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

    public boolean isAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(boolean value) {
        this.adminFlag = value;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean value) {
        this.activeFlag = value;
    }

    public boolean isDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(boolean value) {
        this.deletedFlag = value;
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
