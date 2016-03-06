/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.actionMedicale;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import main.Main;
import main.beans.Fiche_patient;
import main.util.FactorielleChargeurCentre;
import main.util.FichePatientToDisplay;
import uds.information.RemoteFiche_patient;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoadMaladeController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cni;
    @FXML
    private Label erreur;
    @FXML
    private TableView dejaOuvert;
    @FXML
    private VBox verticalBox;

    ObservableList<FichePatientToDisplay> dataFichePatient=FXCollections.observableArrayList();
    ObservableList<FichePatientToDisplay> dataFichePatientAll=FXCollections.observableArrayList();
    ArrayList<Fiche_patient> patients=new ArrayList();

    private Main application;
    private BorderPane conteneur;

    public void setApp(Main application) {
        this.application = application;
    }

    public void setContainer(BorderPane b){
        conteneur=b;

        patients=application.getMalades();
        for (Fiche_patient patient : patients) {
            dataFichePatient.add(new FichePatientToDisplay(((Fiche_patient) patient).getNum_fiche(), ((Fiche_patient) patient).getNom(), ((Fiche_patient) patient).getPrenom(), ((Fiche_patient) patient).getCni(), ((Fiche_patient) patient).getDate_naissance().toString(), ((Fiche_patient) patient).getTel(), ((Fiche_patient) patient).getProfession(), ((Fiche_patient) patient).getSexe() == 'M' ? "Masculin" : "Feminin"));
        }

        TableColumn colNom = new TableColumn("Nom");
        colNom.setPrefWidth(50);
        TableColumn colPrenom = new TableColumn("Prenom");
        colPrenom.setPrefWidth(100);

        colNom.setCellValueFactory(new PropertyValueFactory<FichePatientToDisplay,String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<FichePatientToDisplay,String>("prenom"));

        TableColumn<FichePatientToDisplay, Boolean> btnCol = new TableColumn<>("Voir le dossier");
        btnCol.setPrefWidth(100);
        btnCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FichePatientToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<FichePatientToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });
        btnCol.setCellFactory(new Callback<TableColumn<FichePatientToDisplay, Boolean>, TableCell<FichePatientToDisplay, Boolean>>() {

            @Override
            public TableCell<FichePatientToDisplay, Boolean> call(TableColumn<FichePatientToDisplay, Boolean> p) {
                return new ButtonCell();
            }
        });

        dejaOuvert.getColumns().clear();
        dejaOuvert.setItems(dataFichePatient);
        dejaOuvert.getColumns().addAll(colNom,colPrenom,btnCol);
    }
    @FXML
    public void findByName(){
        nom.setDisable(false);
        prenom.setDisable(false);
        cni.setDisable(true);
    }
    @FXML
    public void findByCNI(){
        nom.setDisable(true);
        prenom.setDisable(true);
        cni.setDisable(false);
    }
    @FXML
    public void chercherMalade() throws NotBoundException, MalformedURLException, RemoteException{
        erreur.setText(null);
        Fiche_patient fiche=new Fiche_patient();
        if(!nom.isDisable()){
            //on recherche suivant le nom et le prenom
            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
            fiche=(Fiche_patient)((RemoteFiche_patient)r).getFiche_patientByNom(nom.getText(),prenom.getText());
        }else{
            //on recherche suivant le numero de la cni
            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
            fiche=(Fiche_patient)((RemoteFiche_patient)r).getFiche_patientByCni(Long.parseLong(cni.getText()));
        }
        if(fiche.getNum_fiche()!=-1){
            FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
            try{
                HomeMaladeController con=(HomeMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/HomeMalade.fxml");
                con.setApp(application);
                con.setContainer(conteneur);
                con.setFiche(fiche);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            erreur.setText("Desole! Le patient cherche n'existe pas.");
        }
    }

    @FXML
    public void voirTousLesPatients() throws NotBoundException, MalformedURLException, RemoteException{
        // om recupere les beans des patients en cours de traitement
        System.out.println(1);
        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
        System.out.println(2);
        patients=((RemoteFiche_patient)r).getAllFiche_patientMalade();
        System.out.println(3);

        //on les chargent dans une observablelist


        for(int i=0;i<patients.size();i++)
            dataFichePatientAll.add(new FichePatientToDisplay(((Fiche_patient)patients.get(i)).getNum_fiche(), ((Fiche_patient)patients.get(i)).getNom(), ((Fiche_patient)patients.get(i)).getPrenom(), ((Fiche_patient)patients.get(i)).getCni(), ((Fiche_patient)patients.get(i)).getDate_naissance().toString(), ((Fiche_patient)patients.get(i)).getTel(), ((Fiche_patient)patients.get(i)).getProfession(), ((Fiche_patient)patients.get(i)).getSexe()=='M'?"Masculin":"Feminin"));

        System.out.println(4);
        // la suite

        TableView tousLesPatients =new TableView();
        tousLesPatients.setPrefHeight(TableView.USE_COMPUTED_SIZE);

        TableColumn id=new TableColumn("identifiant");
        id.setPrefWidth(65);
        TableColumn nom=new TableColumn("Nom");
        nom.setPrefWidth(90);
        TableColumn prenom=new TableColumn("Prenom");
        prenom.setPrefWidth(100);
        TableColumn cni=new TableColumn("Numero de CNI");
        cni.setPrefWidth(100);

        id.setCellValueFactory(new PropertyValueFactory<FichePatientToDisplay,String>("num_fiche"));
        nom.setCellValueFactory(new PropertyValueFactory<FichePatientToDisplay,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<FichePatientToDisplay,String>("prenom"));
        cni.setCellValueFactory(new PropertyValueFactory<FichePatientToDisplay,String>("cni"));

        TableColumn<FichePatientToDisplay, Boolean> btnCol = new TableColumn<>("Voir le dossier");
        btnCol.setPrefWidth(120);
        btnCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FichePatientToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<FichePatientToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });
        btnCol.setCellFactory(new Callback<TableColumn<FichePatientToDisplay, Boolean>, TableCell<FichePatientToDisplay, Boolean>>() {

            @Override
            public TableCell<FichePatientToDisplay, Boolean> call(TableColumn<FichePatientToDisplay, Boolean> p) {
                return new ButtonCell();
            }
        });

        tousLesPatients.getColumns().clear();
        tousLesPatients.setItems(dataFichePatientAll);
        tousLesPatients.getColumns().addAll(id,nom,prenom,cni,btnCol);
        verticalBox.getChildren().removeAll(tousLesPatients);
        verticalBox.getChildren().add(tousLesPatients);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private class ButtonCell extends TableCell<FichePatientToDisplay, Boolean> {
        final Button cellButton = new Button("Voir le dossier");{
            cellButton.setPrefWidth(120);
        }

        ButtonCell(){

            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex();

                    //delete the selected item in data

                    Fiche_patient fiche=patients.get(selectdIndex);
                    if(!application.getMalades().contains(fiche))
                        application.addMalade(fiche);

                    FactorielleChargeurCentre charger=new FactorielleChargeurCentre();
                    try {
                        HomeMaladeController con=(HomeMaladeController)charger.sonFactory(conteneur, "vue/actionMedicale/HomeMalade.fxml");
                        con.setApp(application);
                        con.setContainer(conteneur);
                        con.setFiche(fiche);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }else{
                setGraphic(null);
            }
        }
    }

}
