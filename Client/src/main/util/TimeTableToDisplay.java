/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.util;

import java.sql.Time;
import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cabrel
 */
public class TimeTableToDisplay {

    ObjectProperty<Time> horaire;
    StringProperty lundi;
    StringProperty mardi;
    StringProperty mercredi;
    StringProperty jeudi;
    StringProperty  vendredi;
    StringProperty samedi;

    public TimeTableToDisplay(Time horaire, String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi) {
        this.horaire =new SimpleObjectProperty<Time>(Time.valueOf(LocalTime.of(12,0)));
        this.lundi = new SimpleStringProperty(lundi);
        this.mardi = new SimpleStringProperty(mardi);
        this.mercredi = new SimpleStringProperty(mercredi);
        this.jeudi = new SimpleStringProperty(jeudi);
        this.vendredi = new SimpleStringProperty(vendredi);
        this.samedi = new SimpleStringProperty(samedi);
    }

    public Time getHoraire() {
        return horaire.get();
    }

    public String getLundi() {
        return lundi.get();
    }

    public String getMardi() {
        return mardi.get();
    }

    public String getMercredi() {
        return mercredi.get();
    }

    public String getJeudi() {
        return jeudi.get();
    }

    public String getVendredi() {
        return vendredi.get();
    }

    public String getSamedi() {
        return samedi.get();
    }

    public void setHoraire(Time horaire) {
         this.horaire.set(horaire);
    }

    public void setLundi(String lundi) {
        this.lundi.set(lundi);
    }

    public void setMardi(String mardi) {
        this.mardi.set(mardi);
    }

    public void setMercredi(String mercredi) {
        this.mercredi.set(mercredi);
    }

    public void setJeudi(String jeudi) {
        this.jeudi.set(jeudi);
    }

    public void setVendredi(String vendredi) {
        this.vendredi.set(vendredi);
    }

    public void setSamedi(String samedi) {
        this.samedi.set(samedi);
    }

    public ObjectProperty<Time> horaireProperty() {
        return horaire;
    }

    public StringProperty lundiProperty() {
        return lundi;
    }

    public StringProperty mardiProperty() {
        return mardi;
    }

    public StringProperty mercrediProperty() {
        return mercredi;
    }

    public StringProperty jeudiProperty() {
        return jeudi;
    }

    public StringProperty vendrediProperty() {
        return vendredi;
    }

    public StringProperty samediProperty() {
        return samedi;
    }

}
