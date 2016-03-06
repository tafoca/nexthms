/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.dossier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.Main;
import main.beans.Allergie;
import main.beans.Antecedent;
import main.beans.FicheMalade;
import main.beans.Fiche_patient;
import main.controlleur.actionMedicale.HomeMaladeController;
import main.util.AllergieToDisplay;
import main.util.AntecedentToDisplay;
import main.util.FactorielleChargeurCentre;
import uds.information.RemoteAllergie;
import uds.information.RemoteAntecedent;
import uds.information.RemoteFicheMalade;
import uds.information.RemoteFiche_patient;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ConsulterDossierFinController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label tel;
    @FXML
    private Label cni;
    @FXML
    private Label dateNaissance;
    @FXML
    private Label proession;
    @FXML
    private Label sexe;
    @FXML
    private Label garde;
    @FXML
    private Label telGarde;

    @FXML
    private HBox panel;

    @FXML
    private TableView antecedents;
    @FXML
    private TableView allergies;

    private Fiche_patient fiche;
    private Main application;
    private BorderPane conteneur;

    public void setApp(Main a){ application=a;}
    public void setContainer(BorderPane b){ conteneur=b;}
    public void setFiche(Fiche_patient f) throws NotBoundException, MalformedURLException, RemoteException{
        fiche=f;
        nom.setText(f.getNom());
        prenom.setText(f.getPrenom());
        tel.setText(f.getTel()+"");
        cni.setText(f.getCni()+"");
        dateNaissance.setText(f.getDate_naissance().toString());
        proession.setText(f.getProfession());
        sexe.setText(f.getSexe()=='M'?"Masculin":"Feminin");
        garde.setText(f.getGardeMalade());
        telGarde.setText(fiche.getTelGarde()+"");

        if(fiche.isEstEnCoursDeTraitement()){

            Button b=new Button("Aller au traitement");

            b.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if(!application.getMalades().contains(fiche))
                        application.addMalade(fiche);
                    try {
                        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
                        HomeMaladeController con=(HomeMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/HomeMalade.fxml");
                        con.setApp(application);
                        con.setContainer(conteneur);
                        con.setFiche(fiche);
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterDossierFinController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            panel.getChildren().add(b);
        }else{

            Button b=new Button("Demarrer un traitement");

            b.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {

                        Remote r=Naming.lookup("rmi://127.0.0.1/serveurHospital/ficheMalade");
                        FicheMalade fm=(FicheMalade)((RemoteFicheMalade)r).saveFicheMalade(fiche);

                        r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
                        fiche.setEstEnCoursDeTraitement(true);
                        fiche=(Fiche_patient) ((RemoteFiche_patient)r).updateFiche_patient(fiche);

                        if(!application.getMalades().contains(fiche))
                            application.addMalade(fiche);
                        try {
                            FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
                            HomeMaladeController con=(HomeMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/HomeMalade.fxml");
                            con.setApp(application);
                            con.setContainer(conteneur);
                            con.setFiche(fiche);
                        } catch (IOException ex) {
                            Logger.getLogger(ConsulterDossierFinController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (NotBoundException ex) {
                        Logger.getLogger(ConsulterDossierFinController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ConsulterDossierFinController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ConsulterDossierFinController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            panel.getChildren().add(b);
        }
        // La tabe des antecedents .....................................
        // requere sur la table antecedent sur la cle etrangere de la fiche patient
        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/antecedent");
        ArrayList<Antecedent> tabAntecedent=new ArrayList();
        tabAntecedent=((RemoteAntecedent)r).getAllAntecedentByIdPatient(fiche.getNum_fiche());
        ObservableList<AntecedentToDisplay> dataAntecedents= FXCollections.observableArrayList();
        System.out.println(tabAntecedent.size());
        for(int i=0;i<tabAntecedent.size();i++){
            dataAntecedents.add(new AntecedentToDisplay(tabAntecedent.get(i).getDate().toString(), tabAntecedent.get(i).getCategorie(), tabAntecedent.get(i).getDescription(), tabAntecedent.get(i).getPronostique()));
        }

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setPrefWidth(70);
        TableColumn categorieCol = new TableColumn("Categorie");
        categorieCol.setPrefWidth(100);
        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setPrefWidth(240);
        TableColumn pronosticCol = new TableColumn("Pronostique");
        pronosticCol.setPrefWidth(87);

        dateCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("date"));
        categorieCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("categorie"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("description"));
        pronosticCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("pronostique"));

        antecedents.getColumns().clear();
        antecedents.setItems(dataAntecedents);

        antecedents.getColumns().addAll(dateCol, categorieCol, descriptionCol, pronosticCol);

        // La tabe des llergies .....................................
        // requere sur la table allergie sur la cle etrangere de la fiche patient

        r = Naming.lookup("rmi://127.0.0.1/serveurHospital/allergie");
        ArrayList<Allergie> tabAllergie;
        tabAllergie=((RemoteAllergie)r).getAllAllergieByIdPatient(fiche.getNum_fiche());
        ObservableList<AllergieToDisplay> dataAllergies= FXCollections.observableArrayList();
        for(int i=0;i<tabAllergie.size();i++)
            dataAllergies.add(new AllergieToDisplay(tabAllergie.get(i).getType(), tabAllergie.get(i).getNom(), tabAllergie.get(i).getDescription()));


        TableColumn typeCol = new TableColumn("Type");
        typeCol.setPrefWidth(100);
        TableColumn nomCol = new TableColumn("Nom");
        nomCol.setPrefWidth(100);
        TableColumn descripCol = new TableColumn("Description");
        descripCol.setPrefWidth(297);

        typeCol.setCellValueFactory(new PropertyValueFactory<AllergieToDisplay,String>("type"));
        nomCol.setCellValueFactory(new PropertyValueFactory<AllergieToDisplay,String>("nom"));
        descripCol.setCellValueFactory(new PropertyValueFactory<AllergieToDisplay,String>("description"));

        allergies.getColumns().clear();
        allergies.setItems(dataAllergies);

        allergies.getColumns().addAll(typeCol,nomCol,descripCol);
    }
    @FXML
    public void modiffier() throws IOException{
        FactorielleChargeurCentre charger=new FactorielleChargeurCentre();
        EnregistrerDossierDebutController en = (EnregistrerDossierDebutController)charger.sonFactory(conteneur, "vue/dossier/EnregistrerDossierDebut.fxml");
        en.setToModify(fiche);
        en.setApp(application);
        en.setContainer(conteneur);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
