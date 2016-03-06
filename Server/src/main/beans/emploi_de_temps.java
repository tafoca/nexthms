/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.beans;

import java.io.Serializable;
import java.rmi.Remote;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author JOB
 */
public class emploi_de_temps implements Serializable {

    private long id_emploi = -1;
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
    private String samedi;
    private Time horaire;
    private long num_utilisateur;

    public emploi_de_temps() {

    }


    @Override
    public String toString() {
        return "*******************************************\n"
                + "id_emploi : " + id_emploi + "\n"
                + "num_utilisateur : " + num_utilisateur + "\n";
    }

    public emploi_de_temps(String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi, Time horaire, long num_utilisateur) {
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi = samedi;
        this.horaire = horaire;
        this.num_utilisateur = num_utilisateur;
    }

    public long getId_emploi() {
        return id_emploi;
    }

    public String getLundi() {
        return lundi;
    }

    public String getMardi() {
        return mardi;
    }

    public String getMercredi() {
        return mercredi;
    }

    public String getJeudi() {
        return jeudi;
    }

    public String getVendredi() {
        return vendredi;
    }

    public String getSamedi() {
        return samedi;
    }

    public Time getHoraire() {
        return horaire;
    }

    public long getNum_utilisateur() {
        return num_utilisateur;
    }

    public void setId_emploi(long id_emploi) {
        this.id_emploi = id_emploi;
    }

    public void setLundi(String lundi) {
        this.lundi = lundi;
    }

    public void setMardi(String mardi) {
        this.mardi = mardi;
    }

    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }

    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }

    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }

    public void setSamedi(String samedi) {
        this.samedi = samedi;
    }

    public void setHoraire(Time horaire) {
        this.horaire = horaire;
    }

    public void setNum_utilisateur(long num_utilisateur) {
        this.num_utilisateur = num_utilisateur;
    }


}
