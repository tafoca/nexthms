/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class rendezVousToDisplay {
    private final SimpleLongProperty num_rdv;
    private final SimpleIntegerProperty heurev;
    private final SimpleStringProperty date;
    private final SimpleLongProperty id_medecin;
    private final SimpleLongProperty id_patient;
    private final SimpleStringProperty nom_patient;
    private final SimpleStringProperty nom_medecin;

    public rendezVousToDisplay() {
        this.num_rdv = new SimpleLongProperty();
        this.heurev = new SimpleIntegerProperty();
        this.date = new SimpleStringProperty();
        this.id_medecin = new SimpleLongProperty();
        this.id_patient = new SimpleLongProperty();
        this.nom_patient = new SimpleStringProperty();
        this.nom_medecin = new SimpleStringProperty();
    }

    public rendezVousToDisplay(long num_rdv, int heurev, String date, long id_medecin, long id_patient, String nom_patient, String nom_medecin) {
        this.num_rdv = new SimpleLongProperty(num_rdv);
        this.heurev = new SimpleIntegerProperty(heurev);
        this.date = new SimpleStringProperty(date);
        this.id_medecin = new SimpleLongProperty(id_medecin);
        this.id_patient = new SimpleLongProperty(id_patient);
        this.nom_patient = new SimpleStringProperty(nom_patient);
        this.nom_medecin = new SimpleStringProperty(nom_medecin);
    }

    public long getNum_rdv() {return num_rdv.get();}
    public int getHeurev() {return heurev.get();}
    public String getDate() {return date.get();}
    public long getId_medecin() {return id_medecin.get();}
    public long getId_patient() {return id_patient.get();}
    public String getNom_patient() {return nom_patient.get();}
    public String getNom_medecin() {return nom_medecin.get();}

    public void setNum_rdv(long l){num_rdv.set(l);}
    public void setHeurev(int i){heurev.set(i);}
    public void setDate(String s){date.set(s);}
    public void setId_medecin(long l){id_medecin.set(l);}
    public void setId_patient(long l){id_patient.set(l);}
    public void setNom_patient(String s){nom_patient.set(s);}
    public void setNom_medecin(String s){nom_medecin.set(s);}

}
