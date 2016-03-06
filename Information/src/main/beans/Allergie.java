/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.beans;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Allergie implements Serializable{
    private long num_allergie=-1;
    private String type="";
    private String nom="";
    private String description="";
    private long id_patient=-1;

    public Allergie() {
    }

    public Allergie(long num_allergie, String type, String nom, String description,long id_patient) {
        this.num_allergie = num_allergie;
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.id_patient=id_patient;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

    public long getNum_allergie() {
        return num_allergie;
    }

    public void setNum_allergie(long num_allergie) {
        this.num_allergie = num_allergie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }


}
