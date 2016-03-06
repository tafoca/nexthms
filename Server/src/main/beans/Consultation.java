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
 * @author JOB
 */
public class Consultation implements Serializable{
    private long num_consultation = -1;
    private String motif= "";
    private Date date = new Date(0,0,0);
    private int poids=0;
    private int temperature=0;
    private int tention=0;
    private String diagnostic = "";
    private long id_medecin= -1;
    private long id_patient= -1;
    private boolean nouveauTraitement=true;

    public Consultation() {
    }

    public Consultation(long num_consultation,String motif,Date date,int poids,int temperature,int tention,String diagnostic,long id_medecin,long id_patient,boolean nouveauTraitement) {
        this.num_consultation=num_consultation;
        this.motif=motif;
        this.date=date;
        this.poids=poids;
        this.temperature=temperature;
        this.tention=tention;
        this.diagnostic=diagnostic;
        this.id_medecin=id_medecin;
        this.id_patient = id_patient;
        this.nouveauTraitement=nouveauTraitement;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setNouveauTraitement(boolean nouveauTraitement) {
        this.nouveauTraitement = nouveauTraitement;
    }

    public boolean isNouveauTraitement() {
        return nouveauTraitement;
    }

    public int getTemperature() {
        return temperature;
    }

    public long getId_patient(){
        return id_patient;
    }

    public void setId_patient(long id_patient){
        this.id_patient=id_patient;
    }

    public long getNum_consultation() {
        return num_consultation;
    }

    public String getMotif() {
        return motif;
    }

    public Date getDate() {
        return date;
    }

    public int getPoids() {
        return poids;
    }

    public int getTention() {
        return tention;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public long getId_medecin() {
        return id_medecin;
    }

    public void setNum_consultation(long num_consultation) {
        this.num_consultation = num_consultation;
    }


    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setId_medecin(long id_medecin) {
        this.id_medecin = id_medecin;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setTention(int tention) {
        this.tention = tention;
    }

    @Override
    public String toString() {
        return "*******************************************\n"
                + "numero consultation : "+num_consultation+"\n"
                + "motif : "+motif+"\n"
                + "date: "+date+"\n"
                + "diagnostique :"+diagnostic+"\n"
                + "id du medecin: "+id_medecin+"\n";

    }


}
