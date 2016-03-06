/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.patient;

import java.io.InputStream;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.Main;
import main.beans.Antecedent;
import main.beans.Fiche_patient;
import main.util.AntecedentToDisplay;
import uds.information.RemoteAntecedent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomePatientController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField profetionField;
    @FXML
    private DatePicker ageField;
    @FXML
    private ChoiceBox sexeField;
    @FXML
    private TextField telField;
    @FXML
    private TableView antecedents;
    @FXML
    private BorderPane patientPane;
    private Main application;
    BorderPane border;

    Fiche_patient fiche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initalise");
    }


    public void setAppAndCenter(Main application,BorderPane b) {
        this.application = application;
        this.border = b;
    }
    public void setFiche_patient(Fiche_patient f){
        fiche = f;
        displayIdentite();
    }
    public void displayIdentite(){
        try {
            displayAntecedent();
            FXMLLoader loader = new FXMLLoader();
            InputStream in = Main.class.getResourceAsStream("vue/patient/infoPatient.fxml");
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("vue/patient/infoPatient.fxml"));
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("vue/patient/infoPatient.fxml"));
            HBox page;
            try {
                page = (HBox) loader.load(in);
            } finally {
                in.close();
            }

            patientPane.setTop(page);
            InfoPatientController i=loader.getController();
            i.setAppAndCenterAndFile(application, patientPane,fiche);
        } catch (Exception ex) {
            Logger.getLogger(HomePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editFichePatient(){
        try {
            HBox box = FXMLLoader.load(application.getClass().getResource("vue/patient/editPatient.fxml"));
            patientPane.setTop(box);
        } catch (Exception ex) {
            Logger.getLogger(HomePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void displayAntecedent() throws NotBoundException, MalformedURLException, RemoteException{
        Remote r=Naming.lookup("rmi://127.0.0.1/serveurHospital/antecedent");
        ArrayList<Antecedent> antecedentsTab=((RemoteAntecedent)r).getAllAntecedentByIdPatient(fiche.getNum_fiche());
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setPrefWidth(70);
        TableColumn categorieCol = new TableColumn("Categorie");
        categorieCol.setPrefWidth(100);
        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setPrefWidth(240);
        TableColumn pronosticCol = new TableColumn("Pronostique");
        pronosticCol.setPrefWidth(150);

        ObservableList<AntecedentToDisplay> data= FXCollections.observableArrayList();
        for(int i=0;i<antecedentsTab.size();i++){
            data.add(new AntecedentToDisplay(antecedentsTab.get(i).getDate().toString(),antecedentsTab.get(i).getCategorie(),antecedentsTab.get(i).getDescription(),antecedentsTab.get(i).getPronostique()));
        }
        dateCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("date"));
        categorieCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("categorie"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("description"));
        pronosticCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("pronostique"));

        antecedents.getColumns().clear();
        antecedents.setItems(data);

        antecedents.getColumns().addAll(dateCol, categorieCol, descriptionCol, pronosticCol);
    }
}
