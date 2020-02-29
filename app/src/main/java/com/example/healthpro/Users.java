package com.example.healthpro;

public class Users {
    private String name,email,number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Users() {
    }

    public Users(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }
}
