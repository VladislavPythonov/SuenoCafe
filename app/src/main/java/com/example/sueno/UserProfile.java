package com.example.sueno;

public class UserProfile {
    public String name;
    public String email;
    public String dateBirth;
    public String phone;
    public String address;

    public UserProfile() {
    }

    public UserProfile(String name, String email, String dateBirth, String phone, String address) {
        this.name = name;
        this.email = email;
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.address = address;
    }
}

