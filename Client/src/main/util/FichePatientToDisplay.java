/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.util;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author user
 */
public class FichePatientToDisplay {
    private final SimpleLongProperty num_fiche;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleLongProperty cni;
    private final SimpleStringProperty date_naissance;
    private final SimpleLongProperty tel;
    private final SimpleStringProperty profetion;
    private final SimpleStringProperty sexe;

    public FichePatientToDisplay() {
        this.num_fiche = new SimpleLongProperty(-1);
        this.nom = null;
        this.prenom = null;
        this.cni = null;
        this.date_naissance = null;
        this.tel = null;
        this.profetion = null;
        this.sexe = null;
    }

    public FichePatientToDisplay(long num_fiche, String nom, String prenom, long cni, String date_naissance, long tel, String profetion, String sexe) {
        this.num_fiche=new SimpleLongProperty(num_fiche);
        this.nom=new SimpleStringProperty(nom);
        this.prenom=new SimpleStringProperty(prenom);
        this.cni=new SimpleLongProperty(cni);
        this.date_naissance=new SimpleStringProperty(date_naissance);
        this.tel=new SimpleLongProperty(tel);
        this.profetion=new SimpleStringProperty(profetion);
        this.sexe=new SimpleStringProperty(sexe);
    }


    public long getNum_fiche(){return num_fiche.get();}
    public String getNom(){return nom.get();}
    public String getPrenom(){return prenom.get();}
    public long getCni(){return cni.get();}
    public String getDate_naissance(){return date_naissance.get();}
    public long getTel(){return tel.get();}
    public String getProfetion(){return profetion.get();}
    public String getSexe(){return sexe.get();}

    public void setNum_fiche(long l){num_fiche.set(l);}
    public void setNom(String s){nom.set(s);}
    public void setPrenom(String s){prenom.set(s);}
    public void setCni(long l){cni.set(l);}
    public void setDate_naissance(String s){date_naissance.set(s);}
    public void setTel(long l){tel.set(l);}
    public void setProfetion(String s){profetion.set(s);}
    public void setSexe(String s){sexe.set(s);}
}
