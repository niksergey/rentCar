package main.models.pojo;

import main.models.dto.UserDto;

import java.util.List;

public class User {
    private Integer id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean enabled;
    private String password;

    private List<String> roles;

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
                String phoneNumber, String email, boolean enabled, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
    }

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.secondName = userDto.getSecondName();
        this.lastName = userDto.getLastName();
        this.phoneNumber = userDto.getPhoneNumber();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean value) {
        this.enabled = value;
    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
