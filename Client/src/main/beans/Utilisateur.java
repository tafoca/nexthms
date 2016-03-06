/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.beans;

import java.io.Serializable;
import java.rmi.Remote;

/**
 *
 * @author Adrien MOMO
 */
public class Utilisateur implements Serializable{
    private long num_utilisateur = -1;
    private String nom = "";
    private String prenom = "";
    private String login = "";
    protected String mot_de_passe = "";
    private String statut = "";
    private char type = ' ';
    private long phone = 600000000;
    private String mail = "";
    private String proil = "";
    private String adresse="";

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Utilisateur() {
    }

    public Utilisateur(long num_utilisateur, String nom, String prenom, String login, String mot_de_passe, String statut, char type, long phone, String mail, String proil,String adresse) {
        this.num_utilisateur = num_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mot_de_passe = mot_de_passe;
        this.statut = statut;
        this.type = type;
        this.phone = phone;
        this.mail = mail;
        this.proil = proil;
        this.adresse=adresse;
    }

    // Acceceur en lecture

    public long getNum_utilisateur() {
        return num_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLogin() {
        return login;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getStatut() {
        return statut;
    }

    public char getType() {
        return type;
    }

    public long getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getProil() {
        return proil;
    }

    // Acceceur en ecriture
    public void setNum_utilisateur(long num_utilisateur) {
        this.num_utilisateur = num_utilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setProil(String proil) {
        this.proil = proil;
    }

    @Override
    public String toString() {
        return "*******************************************\n"
                + "Id user : "+num_utilisateur+"\n"
                + "nom :"+nom+"\n"
                + "prenom : "+prenom+"\n"
                + "login : "+login+"\n"
                + "mot de passe : "+mot_de_passe+"\n"
                + "statut : "+statut+"\n"
                + "type : "+type+"\n"
                + "phone : "+phone+"\n"
                + "e-mail : "+mail+"\n"
                + "profil : "+proil+"\n";
    }


}
