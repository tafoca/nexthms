/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.beans;

import java.io.Serializable;
import java.rmi.Remote;
import java.sql.Date;
/**
 *
 * @author JOB
 */
public class Antecedent implements Serializable{
    private long num_antecedent = -1;
    private String categorie= "";
    private String description = "";
    private Date date= new Date(00, 00, 00);
    private String pronostique="";
    private long id_patient=-1;

    public Antecedent(long num_antecedent, String categorie, String description, Date date, String pronostique, long id_patient) {
        this.num_antecedent=num_antecedent;
        this.categorie=categorie;
        this.description=description;
        this.date=date;
        this.pronostique=pronostique;
        this.id_patient=id_patient;
    }

    public Antecedent(){

    }

    public long getNum_antecedent() {
        return num_antecedent;
    }


    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setNum_antecedent(long num_antecedent) {
        this.num_antecedent = num_antecedent;
    }


    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setPronostique(String pronostique) {
        this.pronostique = pronostique;
    }

    public String getPronostique() {
        return pronostique;
    }


    @Override
    public String toString() {
        return "*******************************************\n"
                + "numero antecedent : "+num_antecedent+"\n"
                + "categorie : "+categorie+"\n"
                + "description: "+description+"\n";

    }


}

/* je suis job déé */
