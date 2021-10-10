package com.mycompany.tasks.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String phone;


    public User(String login){
        this.firstName="Ana";
        this.lastName="Li";
        this.login=login;
    }
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
