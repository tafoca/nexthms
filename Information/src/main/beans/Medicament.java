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
 * @author JOB
 */
public class Medicament implements Serializable{
    private long id = -1;
    private String code ="";
    private String nom ="";
    private String description ="";

    public Medicament() {
    }

    public Medicament(long id,String code,String nom,String description) {
        this.id=id;
        this.code=code;
        this.nom=nom;
        this.description=description;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "*******************************************\n"
                + "id medicament : "+id+"\n"
                + "code :"+code+"\n"
                + "nom : "+nom+"\n"
                + "description: "+description+"\n";
    }


}
