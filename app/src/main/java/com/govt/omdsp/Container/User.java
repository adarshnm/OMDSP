package com.govt.omdsp.Container;

public class User {
    public String userId;
    public String name;
    public String email;
    public Long phone;
    public String gender;
    public String address;
    public String state;

    public User() {
    }

    public User(String userId, String name, String email, Long phone, String gender, String address, String state) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }
}
