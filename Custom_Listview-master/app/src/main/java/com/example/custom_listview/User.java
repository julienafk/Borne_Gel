package com.example.custom_listview;

public class User {

    String name, lastMessage, ID, phoneNo, country;
    int imageId;

    public User(String name, String mail, String idCompte, String phoneNo, String country, int imageId) {
        this.name = name;
        this.lastMessage = mail;
        this.ID = idCompte;
        this.phoneNo = phoneNo;
        this.country = country;
        this.imageId = imageId;
    }
}
