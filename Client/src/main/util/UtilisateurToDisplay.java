/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cabrel
 */
public class UtilisateurToDisplay {

    private final StringProperty nom_usr;
    private final StringProperty prenom_usr;
    private final SimpleStringProperty login_usr;
    private final SimpleStringProperty pass_usr;
    private final SimpleStringProperty statut_usr;
    private final SimpleStringProperty type_usr;
    private final SimpleIntegerProperty phone_usr;
    private final SimpleLongProperty id_usr;
    private final SimpleStringProperty mail_usr;
    private final SimpleStringProperty profil_usr;
    private  SimpleStringProperty adresse;

    public UtilisateurToDisplay(String nom_usr, String prenom_usr, String login_usr, String pass_usr, String statut_usr, String  type_usr, Integer phone_usr, long id_usr, String mail_usr, String profil_usr,String adresse) {
        this.nom_usr = new SimpleStringProperty(nom_usr);
        this.prenom_usr = new SimpleStringProperty(prenom_usr);
        this.login_usr = new SimpleStringProperty(login_usr);
        this.pass_usr = new SimpleStringProperty(pass_usr);
        this.statut_usr = new SimpleStringProperty(statut_usr);
        this.type_usr =new SimpleStringProperty(type_usr);
        this.phone_usr = new SimpleIntegerProperty(phone_usr);
        this.id_usr = new SimpleLongProperty(id_usr);
        this.mail_usr = new SimpleStringProperty(mail_usr);
        this.profil_usr = new SimpleStringProperty(profil_usr);
        this.adresse=new SimpleStringProperty(adresse);

    }


    public UtilisateurToDisplay(String nom_usr, String prenom_usr) {
        this.nom_usr = new SimpleStringProperty(nom_usr);
        this.prenom_usr = new SimpleStringProperty(prenom_usr);
        this.login_usr = new SimpleStringProperty("login");
        this.pass_usr = new SimpleStringProperty("password");
        this.statut_usr = new SimpleStringProperty("state");
        this.type_usr =new SimpleStringProperty("type");
        this.phone_usr = new SimpleIntegerProperty(12);
        this.id_usr = new SimpleLongProperty(22);
        this.mail_usr = new SimpleStringProperty("hi@mailto.cn");
        this.profil_usr = new SimpleStringProperty("DR");
        this.adresse=new SimpleStringProperty("");
    }


    public String getNom_usr() {
        return nom_usr.get();
    }

    public String getPrenom_usr() {
        return prenom_usr.get();
    }

    public String getLogin_usr() {
        return login_usr.get();
    }

    public String getPass_usr() {
        return pass_usr.get();
    }

    public String getStatut_usr() {
        return statut_usr.get();
    }

    public String getType_usr() {
        return type_usr.get();
    }

    public int getPhone_usr() {
        return phone_usr.get();
    }

    public long getId_usr() {
        return id_usr.get();
    }

    public String getMail_usr() {
        return mail_usr.get();
    }

    public String getProfil_usr() {
        return profil_usr.get();
    }


     public void setAdresse(String adr) {
         adresse.set(adr);
    }
    public void setNom_usr(String nom) {
        nom_usr.set(nom);
    }

    public void setPrenom_usr(String prenom) {
         prenom_usr.set(prenom);
    }

    public void setLogin_usr(String lg) {
         login_usr.set(lg);
    }

    public void setPass_usr(String ps) {
         pass_usr.set(ps);
    }

    public void getStatut_usr(String st) {
         statut_usr.set(st);
    }

    public void getType_usr(String ty) {
        type_usr.set(ty);
    }

    public void setPhone_usr(int phone) {
         phone_usr.set(phone);
    }

    public void setId_usr(long id) {
       id_usr.set(id);
    }

    public void setMail_usr(String mail) {
         mail_usr.set(mail);
    }

    public void setProfil_usr(String profile) {
         profil_usr.set(profile);
    }

      public StringProperty firstNameProperty() {
        return nom_usr;
    }


    public StringProperty lastNameProperty() {
        return prenom_usr;
    }

    public StringProperty AdresseProperty() {
        return adresse;
    }

    public String getAdresse() {
       return adresse.get();
        }


}
