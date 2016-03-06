/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.actionMedicale;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.beans.Fiche_patient;
import main.controlleur.dossier.ConsulterDossierFinController;
import main.util.FactorielleChargeurCentre;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeMaladeController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date;
    @FXML
    private Label cni;
    @FXML
    private Label tel;
    @FXML
    private Label profession;
    @FXML
    private Label sexe;
    @FXML
    private Label garde;
    @FXML
    private Label telGarde;


    private Main application;
    private BorderPane conteneur;
    private Fiche_patient fiche;

    public void setApp(Main application) {
        this.application = application;
    }

    public void setContainer(BorderPane b){
        conteneur=b;
    }

    @FXML
    public void voirDossier() throws IOException, NotBoundException{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        ConsulterDossierFinController con=(ConsulterDossierFinController)chargeur.sonFactory(conteneur, "vue/dossier/ConsulterDossierFin.fxml");
        con.setApp(application);
        con.setContainer(conteneur);
        con.setFiche(fiche);
    }

    @FXML
    public void consultation() throws Exception{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        ConsultationMaladeController con=(ConsultationMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/ConsultationMalade.fxml");
        con.setApp(application);
        con.setContainer(conteneur);
        con.setFiche(fiche);
    }
    @FXML
    public void ordonance() throws Exception{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        OrdonanceMaladeController ord=(OrdonanceMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/OrdonanceMalade.fxml");
        ord.setApp(application);
        ord.setContainer(conteneur);
        ord.setFiche(fiche);
    }
    @FXML
    public void examens() throws Exception{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        ExamensMaladeController ex=(ExamensMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/ExamensMalade.fxml");
        ex.setApp(application);
        ex.setContainer(conteneur);
        ex.setFiche(fiche);
    }
    @FXML
    public void rendezVous() throws IOException{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        RendezVousController ex=(RendezVousController)chargeur.sonFactory(conteneur, "vue/actionMedicale/RendezVous.fxml");
        ex.setApp(application);
        ex.setContainer(conteneur);
        ex.setFiche(fiche);
    }
    @FXML
    public void clore() throws Exception{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        CloreTraitementController ex=(CloreTraitementController)chargeur.sonFactory(conteneur, "vue/actionMedicale/CloreTraitement.fxml");
        ex.setApp(application);
        ex.setContainer(conteneur);
        ex.setFiche(fiche);
    }

    public void setFiche(Fiche_patient f){
        fiche=f;
        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());
        tel.setText(f.getTel()+"");
        cni.setText(f.getCni()+"");
        date.setText(f.getDate_naissance().toString());
        profession.setText(f.getProfession());
        sexe.setText(f.getSexe()=='M'?"Masculin":"Feminin");
        garde.setText(f.getGardeMalade());
        telGarde.setText(f.getTelGarde()+"");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
