/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.util;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class OrdonanceToDisplay {
    private final SimpleStringProperty nom;
    private final SimpleStringProperty famille;
    private final SimpleStringProperty forme;
    private final SimpleStringProperty posologie;
    private final SimpleStringProperty observation;

    public OrdonanceToDisplay() {
        this.nom = new SimpleStringProperty();
        this.famille = new SimpleStringProperty();
        this.forme = new SimpleStringProperty();
        this.posologie = new SimpleStringProperty();
        this.observation = new SimpleStringProperty();
    }

    public OrdonanceToDisplay(String nom, String famille, String forme, String posologie, String observation) {
        this.nom = new SimpleStringProperty(nom);
        this.famille = new SimpleStringProperty(famille);
        this.forme = new SimpleStringProperty(forme);
        this.posologie = new SimpleStringProperty(posologie);
        this.observation = new SimpleStringProperty(observation);
    }

    public String getFamille() {return famille.get();}
    public String getForme() {return forme.get();}
    public String getNom() {return nom.get();}
    public String getObservation() {return observation.get();}
    public String getPosologie() {return posologie.get();}

    public void setNom(String s){nom.set(s);}
    public void setFamille(String s){famille.set(s);}
    public void setForme(String s){forme.set(s);}
    public void setObservation(String s){observation.set(s);}
    public void setPosologie(String s){posologie.set(s);}
}
