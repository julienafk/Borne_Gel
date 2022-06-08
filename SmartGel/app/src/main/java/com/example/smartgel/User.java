package com.example.smartgel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class User {

    String name, lastMessage, lastMsgTime, phoneNo, country;
    int imageId;

    public User(String name, String lastMessage, String lastMsgTime, String phoneNo, String country, int imageId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMsgTime = lastMsgTime;
        this.phoneNo = phoneNo;
        this.country = country;
        this.imageId = imageId;
    }
}