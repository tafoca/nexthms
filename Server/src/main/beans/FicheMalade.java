/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author user
 */
public class FicheMalade implements Serializable{
    private Date dateArrive;
    private Date dateSortie;
    private boolean estClos;
    private long id_patient=-1;

    public FicheMalade() {
    }

    public FicheMalade(Date dateArrive, Date dateSortie,boolean statut, long id_patient) {
        this.dateArrive = dateArrive;
        this.dateSortie = dateSortie;
        this.estClos = statut;
        this.id_patient = id_patient;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setEstClos(boolean estClos) {
        this.estClos = estClos;
    }

    public boolean isEstClos() {
        return estClos;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }


}
