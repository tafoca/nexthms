/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.actionMedicale;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.beans.Fiche_patient;
import main.beans.Rendez_vous;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RendezVousController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private ChoiceBox nom;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox heure;

    private Main application;
    private BorderPane conteneur;
    private Fiche_patient fiche;

    public void setApp(Main application) {this.application = application;}
    public void setContainer(BorderPane b){conteneur=b;}
    public void setFiche(Fiche_patient f){
        fiche=f;
        info.setText(info.getText()+" "+fiche.getNom()+" "+fiche.getPrenom()+" (Id Nro "+fiche.getNum_fiche()+")");
        // on recup all les docs ds la bd
        nom.getItems().addAll("Dr TCOUPE","Dr ...");
        heure.getItems().addAll(8,9,10,11,12,13,14,15,16,17);

        //on recup all les rdvs ds la bd

    }
    public void valider(){
        //On sauvegade le rendez-vous
        Rendez_vous r=new Rendez_vous(-1, new Time(Integer.parseInt(heure.getValue().toString()), 0, 0), new Date(date.getValue().getYear()-1900,date.getValue().getMonthValue(),date.getValue().getDayOfMonth()), 0, fiche.getNum_fiche());
        System.out.println(date.getValue().getYear());
        System.out.println(r.toString());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
