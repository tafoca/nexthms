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
public class ConsultationToDisplay {
    private final SimpleLongProperty num_consultation;
    private final SimpleStringProperty motif;
    private final SimpleStringProperty date;
    private final SimpleIntegerProperty poids;
    private final SimpleIntegerProperty temperature;
    private final SimpleIntegerProperty tention;
    private final SimpleStringProperty diagnostic;
    private final SimpleLongProperty id_medecin;

    public ConsultationToDisplay() {
        this.num_consultation = new SimpleLongProperty();
        this.motif = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.poids = new SimpleIntegerProperty();
        this.temperature=new SimpleIntegerProperty();
        this.tention=new SimpleIntegerProperty();
        this.diagnostic = new SimpleStringProperty();
        this.id_medecin = new SimpleLongProperty();
    }

    public ConsultationToDisplay(long num_consultation, String motif, String date,int poids,int temperature, int tention, String diagnostic, long id_medecin) {
        this.num_consultation = new SimpleLongProperty(num_consultation);
        this.motif = new SimpleStringProperty(motif);
        this.date = new SimpleStringProperty(date);
        this.poids = new SimpleIntegerProperty(poids);
        this.temperature=new SimpleIntegerProperty(temperature);
        this.tention=new SimpleIntegerProperty(tention);
        this.diagnostic = new SimpleStringProperty(diagnostic);
        this.id_medecin = new SimpleLongProperty(id_medecin);
    }

    public long getNum_consultation() {return num_consultation.get();}
    public String getMotif() {return motif.get();}
    public String getDate() {return date.get();}
    public int getPoids() {return poids.get();}
    public int getTemperature() {return temperature.get();}
    public int getTention() {return tention.get();}
    public String getDiagnostic() {return diagnostic.get();}
    public long getId_medecin() {return id_medecin.get();}

    public void setNum_consultation(long l){num_consultation.set(l);}
    public void setMotif(String s){motif.set(s);}
    public void setDate(String s){date.set(s);}
    public void setPoids(int i){poids.set(i);}
    public void setTemperature(int i){temperature.set(i);}
    public void setTention(int i){tention.set(i);}
    public void setDiagnostic(String s){diagnostic.set(s);}
    public void setId_medecin(long l){id_medecin.set(l);}
}
