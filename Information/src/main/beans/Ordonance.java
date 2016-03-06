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
public class Ordonance implements Serializable{
    private long num_ordonance = -1;
    private Date date = new Date(0,0,0);
    private String nom_medicament="";
    private String famille_medicament="";
    private String forme_medicament="";
    private String posologie="";
    private String observation="";
    private boolean nouveauTraitement=true;
    private long id_medecin = -1;
    private long id_patient = -1;

    public Ordonance() {
    }

    public Ordonance(long num_ordonance,Date date,String nom_medicament,String famille_medicament,String forme_medicament,String posologie,String observation,boolean b,long id_medecin,long id_patient) {
        this.num_ordonance=num_ordonance;
        this.date=date;
        this.nom_medicament=nom_medicament;
        this.famille_medicament=famille_medicament;
        this.forme_medicament=forme_medicament;
        this.posologie=posologie;
        this.observation=observation;
        nouveauTraitement=b;
        this.id_medecin=id_medecin;
        this.id_patient=id_patient;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setId_patient(long id_patient){
        this.id_patient=id_patient;
    }

    public long getNum_ordonance() {
        return num_ordonance;
    }

    public void setNouveauTraitement(boolean nouveauTraitement) {
        this.nouveauTraitement = nouveauTraitement;
    }

    public boolean isNouveauTraitement() {
        return nouveauTraitement;
    }

    public Date getDate() {
        return date;
    }

    public String getPosologie() {
        return posologie;
    }

    public String getForme_medicament() {
        return forme_medicament;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getObservation() {
        return observation;
    }

    public String getNom_medicament() {
        return nom_medicament;
    }

    public String getFamille_medicament() {
        return famille_medicament;
    }

    public long getId_medecin() {
        return id_medecin;
    }

    public void setNum_ordonance(long num_ordonance) {
        this.num_ordonance = num_ordonance;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public void setForme_medicament(String forme_medicament) {
        this.forme_medicament = forme_medicament;
    }

    public void setFamille_medicament(String famille_medicament) {
        this.famille_medicament = famille_medicament;
    }

    public void setId_medecin(long id_medecin) {
        this.id_medecin = id_medecin;
    }

    @Override
    public String toString() {
         return "*******************************************\n"
                + "numero ordonance : "+num_ordonance+"\n"
                + "date : "+date+"\n"
                + "id du medecin: "+id_medecin+"\n";

    }


}
