/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.beans;

import java.io.Serializable;
import java.rmi.Remote;
import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author JOB
 */
public class Rendez_vous implements Serializable{
    private long num_rdv = -1;
    private Time heure;
    private Date date = new Date(0,0,0);
    private long id_medecin = -1;
    private long id_patient = -1;

    public Rendez_vous() {
    }

    public Rendez_vous(long num_rdv,Time heure,Date date,long id_medecin,long id_patient){
        this.num_rdv=num_rdv;
        this.heure=heure;
        this.date=date;
        this.id_medecin=id_medecin;
        this.id_patient=id_patient;

    }

    public long getNum_rdv() {
        return num_rdv;
    }

    public Time getHeure() {
        return heure;
    }

    public Date getDate() {
        return date;
    }

    public long getId_medecin() {
        return id_medecin;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setNum_rdv(long num_rdv) {
        this.num_rdv = num_rdv;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId_medecin(long id_medecin) {
        this.id_medecin = id_medecin;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "*******************************************\n"
                + "numero rendez_vous : "+num_rdv+"\n"
                + "heure : "+heure+"\n"
                + "date: "+date+"\n"
                + "id medecin :"+id_medecin+"\n"
                + "id patient: "+id_patient+"\n";
    }


}
