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
public class Fiche_patient implements Serializable{
    private long num_fiche = -1;
    private String nom = "";
    private String prenom = "";
    private long cni = 111111111;
    private Date date_naissance=new Date(00, 00, 00);
    private long tel = 600000000;
    private String profession ="";
    private char sexe= ' ';
    private boolean estEnCoursDeTraitement=false;
    private String gardeMalade="";
    private long telGarde = 600000000;

    public Fiche_patient() {
        super();
    }

    public Fiche_patient(long num_fiche,String nom,String prenom,long cni,Date date_naissance,long tel,String profession,char sexe,boolean b,String garde,long telGarde) {

       this.num_fiche=num_fiche;
       this.nom=nom;
       this.prenom=prenom;
       this.cni=cni;
       this.date_naissance=date_naissance;
       this.tel=tel;
       this.profession=profession;
       this.sexe=sexe;
       estEnCoursDeTraitement=b;
       this.gardeMalade=garde;
       this.telGarde=telGarde;
    }

    public long getNum_fiche() {
        return num_fiche;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public long getCni() {
        return cni;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public long getTel() {
        return tel;
    }

    public String getProfession() {
        return profession;
    }

    public char getSexe() {
        return sexe;
    }

    public boolean isEstEnCoursDeTraitement() {
        return estEnCoursDeTraitement;
    }

    public long getTelGarde() {
        return telGarde;
    }

    public String getGardeMalade() {
        return gardeMalade;
    }

    public void setNum_fiche(long num_fiche) {
        this.num_fiche = num_fiche;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCni(long cni) {
        this.cni = cni;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }


    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public void setEstEnCoursDeTraitement(boolean estEnCoursDeTraitement) {
        this.estEnCoursDeTraitement = estEnCoursDeTraitement;
    }

    public void setGardeMalade(String gardeMalade) {
        this.gardeMalade = gardeMalade;
    }

    public void setTelGarde(long telGarde) {
        this.telGarde = telGarde;
    }


    @Override
    public String toString() {
        return "*******************************************\n"
                + "numero de fiche : "+num_fiche+"\n"
                + "nom :"+nom+"\n"
                + "prenom : "+prenom+"\n"
                + "cni: "+cni+"\n"
                + "date de naissance :"+date_naissance+"\n"
                + "telephone: "+tel+"\n"
                + "profession: "+profession+"\n"
                + "sexe "+sexe+"\n"
                + (estEnCoursDeTraitement?"En cours de traitement.":"N'est pas en cours de traitement.")
                +"\n";

    }


}
