/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.util;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class ExamenMedecinToDisplay {
    private final SimpleLongProperty id;
    private final SimpleStringProperty type;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty resulta;

    public ExamenMedecinToDisplay() {
        this.id = new SimpleLongProperty();
        this.type = new SimpleStringProperty();
        this.nom = new SimpleStringProperty();
        this.resulta = new SimpleStringProperty();
    }

    public ExamenMedecinToDisplay(long id, String type, String nom, String resulta) {
        this.id = new SimpleLongProperty(id);
        this.type = new SimpleStringProperty(type);
        this.nom = new SimpleStringProperty(nom);
        this.resulta = new SimpleStringProperty(resulta);
    }

    public long getId() {return id.get();}
    public String getNom() {return nom.get();}
    public String getResulta() {return resulta.get();}
    public String getType() {return type.get();}

    public void setId(long l){id.set(l);}
    public void setNom(String s){nom.set(s);}
    public void setResulta(String s){resulta.set(s);}
    public void setType(String s){type.set(s);}

}
