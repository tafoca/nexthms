/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author user
 */
public class AllergieToDisplay {
    private final SimpleStringProperty type;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty description;

    public AllergieToDisplay(String type,String nom,String description){
        this.type=new SimpleStringProperty(type);
        this.nom=new SimpleStringProperty(nom);
        this.description=new SimpleStringProperty(description);
    }





    public String getType() {
        return type.get();
    }


    public void setType(String d){
        type.set(d);
    }
    public String getNom() {
        return nom.get();
    }


    public void setNom(String d){
        nom.set(d);
    }
    public String getDescription() {
        return description.get();
    }


    public void setDescription(String d){
        description.set(d);
    }

}
