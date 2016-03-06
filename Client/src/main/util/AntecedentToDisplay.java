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
public class AntecedentToDisplay {
    private final SimpleStringProperty date;
    private final SimpleStringProperty categorie;
    private final SimpleStringProperty description;
    private final SimpleStringProperty pronostique;

    public AntecedentToDisplay(String date,String categorie,String description,String pronostique){
        this.date=new SimpleStringProperty(date);
        this.categorie=new SimpleStringProperty(categorie);
        this.description=new SimpleStringProperty(description);
        this.pronostique=new SimpleStringProperty(pronostique);
    }



    public String getDate() {
        return date.get();
    }
    public void setDate(String d){
        date.set(d);
    }

    public String getCategorie() {
        return categorie.get();
    }
    public void setCategorie(String c){
        categorie.set(c);
    }

    public String getDescription() {
        return description.get();
    }


    public void setDescription(String d){
        description.set(d);
    }

    public String getPronostique() {
        return pronostique.get();
    }
    public void setPronostique(String p){
        pronostique.set(p);
    }
}
