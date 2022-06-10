package com.example.smartgel;

public class User {

    String Prenom, Nom, idCompte, Email, UAid;
    int imageId;

    public User(String Prenom, String Nom, String idCompte, String Email, String UAid, int imageId) {
        this.Prenom = Prenom;
        this.Nom = Nom;
        this.idCompte = idCompte;
        this.Email = Email;
        this.UAid = UAid;
        this.imageId = imageId;
    }
}