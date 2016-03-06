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
public class Examen implements Serializable{
    private long numero = -1;
    private long num_patient=-1;
    private String nom="";
    private String resulta="";
    private long id_medecin=-1;
    private String type= "";
    private boolean nouveauTraitement=true;


    public Examen() {
    }

    public Examen(long numero,String type,long num_patient,String nom,String resulta,long id_med,boolean b) {
        this.numero=numero;
        this.num_patient=num_patient;
        id_medecin=id_med;
        this.type=type;
        this.nom=nom;
        this.resulta=resulta;
        nouveauTraitement=b;
    }

    public String getResulta() {
        return resulta;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setResulta(String resulta) {
        this.resulta = resulta;
    }



    public void setNum_patient(long num_patient) {
        this.num_patient = num_patient;
    }

    public boolean isNouveauTraitement() {
        return nouveauTraitement;
    }

    public void setNouveauTraitement(boolean nouveauTraitement) {
        this.nouveauTraitement = nouveauTraitement;
    }

    public void setId_medecin(long id_medecin) {
        this.id_medecin = id_medecin;
    }

    public long getNum_patient() {
        return num_patient;
    }

    public long getId_medecin() {
        return id_medecin;
    }

    public long getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "*******************************************\n"
                + "numero: "+numero+"\n"
                + "type :"+type+"\n";

    }


}

