/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.beans.Utilisateur;
import main.controlleur.Ressource.UpdateTimesTableController;
import main.controlleur.actionMedicale.LaboratoireController;
import main.controlleur.dossier.ConsulterDossierDebutController;
import main.controlleur.dossier.EnregistrerDossierDebutController;
import main.controlleur.actionMedicale.LoadMaladeController;
import main.controlleur.utilisateur.CosulteController;
import main.controlleur.utilisateur.ModifyUserController;
import main.util.FactorielleChargeurCentre;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class AcceiulController implements Initializable {

@FXML
Menu administration;
@FXML
Menu dossierM;
@FXML
Menu actionM;
@FXML
Menu planification;
@FXML
Menu laboratoire;

@FXML
MenuItem ajout;
//@FXML
//MenuItem consulter;
@FXML
BorderPane principal;
@FXML
Label connecte;

Utilisateur utilisateurConnecte;

private Main application;
        FactorielleChargeurCentre charger=new FactorielleChargeurCentre();

    public void setApp(Main application) {
        this.application = application;
        //contenue initialisation accueil
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    public void gotouserPage(ActionEvent e) throws Exception{
                FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream("vue/utilisateur/Utilisateur.fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("vue/utilisateur/Utilisateur.fxml"));
        BorderPane page;
        try {
            page = (BorderPane) loader.load(in);
        } finally {
            in.close();
        }
        principal.setCenter(page);
    }

    //*******************************************************
    //                  Utilisateur du systeme
    //*******************************************************

	@FXML
    public void gotoconsultUser(ActionEvent e) throws Exception{

    CosulteController consulter = (CosulteController)charger.sonFactory(principal, "vue/utilisateur/cosulte.fxml");
    consulter.setApp(application);
    }

    @FXML
    public void gotoModifyUser(ActionEvent e) throws Exception{

    ModifyUserController consulter = (ModifyUserController)charger.sonFactory(principal, "vue/utilisateur/ModifyUser.fxml");
    consulter.setApp(application);
    }

    //**********************************************************
    //                  Actions medicales
    //**********************************************************
    public void gotoLoadMalade() throws IOException{
        LoadMaladeController consulter = (LoadMaladeController)charger.sonFactory(principal, "vue/actionMedicale/loadMalade.fxml");
        consulter.setApp(application);
        consulter.setContainer(principal);
    }
    public void gotoLoadPatient() throws IOException{
        System.out.println("Fonctionalite temorairement supprime");
    }

     public void loadPatient(){
        System.out.println("yoo!");
        try {
            gotoLoadPatient();
        } catch (IOException ex) {
            Logger.getLogger(AcceiulController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     // labo
     public void allerAuLabo() throws  Exception{
        LaboratoireController lbc = (LaboratoireController)charger.sonFactory(principal, "vue/actionMedicale/laboratoire.fxml");
        lbc.setApp(application);
        lbc.setContainer(principal);
     }

    //**********************************************************
    //                  Acceuil
    //**********************************************************
    @FXML
    public void gotoacceiul(ActionEvent ee) throws Exception{
            AcceiulController home;
            home = (AcceiulController) application.chargeur("vue/Acceiul.fxml");
            home.setApp(application);
    }
     @FXML
    public void deconnexion(){
             application.gotoLogin();
    }

    //**********************************************************
    //                  Dossier medical
    //**********************************************************

    @FXML
    public void  cretiondossiermedicla() throws IOException{
    charger.sonFactory(principal,"vue/EnregistrerDossier.fxml");
    }

    @FXML
    public void createMedicaleDocument(ActionEvent e) throws Exception{
        EnregistrerDossierDebutController en = (EnregistrerDossierDebutController)charger.sonFactory(principal, "vue/dossier/EnregistrerDossierDebut.fxml");
        en.setApp(application);
        en.setContainer(principal);
    }
    @FXML
    public void consultMedicaleDocument(ActionEvent e) throws Exception{
        ConsulterDossierDebutController con = (ConsulterDossierDebutController)charger.sonFactory(principal, "vue/dossier/ConsulterDossierDebut.fxml");
        con.setApp(application);
        con.setContainer(principal);
    }

    //*************************************************************************
    //         Timme Table
    //*************************************************************************


   @FXML
    public void gotoModifyTimesTableUser(ActionEvent e) throws Exception{

    UpdateTimesTableController consult = (UpdateTimesTableController)charger.sonFactory(principal, "vue/Ressource/updateTimesTable.fxml");
    consult.setApp(application);
    }

    void setUser(Utilisateur u) {
        this.utilisateurConnecte=u;
        connecte.setText("Bienvennue Mr/Mdm "+utilisateurConnecte.getNom()+" "+utilisateurConnecte.getPrenom());
        System.err.println(utilisateurConnecte.getType());
        if(utilisateurConnecte.getType()=='L'){
            administration.setDisable(true);
            dossierM.setDisable(true);
            actionM.setDisable(true);
            planification.setDisable(true);
            laboratoire.setDisable(false);
        }
        if(utilisateurConnecte.getType()=='M'){
            administration.setDisable(false);
            dossierM.setDisable(false);
            actionM.setDisable(false);
            planification.setDisable(false);
            laboratoire.setDisable(false);
        }
        if(utilisateurConnecte.getType()=='S'){
            administration.setDisable(true);
            dossierM.setDisable(false);
            actionM.setDisable(true);
            planification.setDisable(true);
            laboratoire.setDisable(true);
        }
        if(utilisateurConnecte.getType()=='G'){
            administration.setDisable(false);
            dossierM.setDisable(true);
            actionM.setDisable(true);
            planification.setDisable(false);
            laboratoire.setDisable(true);
        }
    }

}
