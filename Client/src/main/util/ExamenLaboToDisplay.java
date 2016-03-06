/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.util;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class ExamenLaboToDisplay {
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty typeExam;
    private final SimpleStringProperty nomExam;
    private final SimpleStringProperty resulta;
    private final SimpleLongProperty idMedecin;
    private final SimpleBooleanProperty valider;

    public ExamenLaboToDisplay() {
        this.nom = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.typeExam = new SimpleStringProperty();
        this.nomExam = new SimpleStringProperty();
        this.resulta = new SimpleStringProperty();
        this.idMedecin = new SimpleLongProperty();
        this.valider = new SimpleBooleanProperty();
    }

    public ExamenLaboToDisplay(String nom, String prenom, String typeExam, String nomExam, String resulta, Long idMedecin, Boolean valider) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.typeExam = new SimpleStringProperty(typeExam);
        this.nomExam = new SimpleStringProperty(nomExam);
        this.resulta = new SimpleStringProperty(resulta);
        this.idMedecin = new SimpleLongProperty(idMedecin);
        this.valider = new SimpleBooleanProperty(valider);
    }

    public Long getIdMedecin() {return idMedecin.get();}
    public String getNom() {return nom.get();}
    public String getNomExam() {return nomExam.get();}
    public String getPrenom() {return prenom.get();}
    public String getResulta() {return resulta.get();}
    public String getTypeExam() {return typeExam.get();}
    public Boolean getValider() {return valider.get();}

    public void setIdMedecin(long l) { idMedecin.set(l);}
    public void setNom(String s) { nom.set(s);}
    public void setNomExam(String s) { nomExam.set(s);}
    public void setPrenom(String s) { prenom.set(s);}
    public void setResulta(String b) { resulta.set(b);}
    public void setTypeExam(String s) { typeExam.set(s);}
    public void setValider(boolean b) { valider.set(b);}


}
