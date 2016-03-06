/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.dossier;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.Main;
import main.beans.Allergie;
import main.beans.Antecedent;
import main.beans.Fiche_patient;
import main.util.AllergieToDisplay;
import main.util.AntecedentToDisplay;
import main.util.FactorielleChargeurCentre;
import uds.information.RemoteAllergie;
import uds.information.RemoteAntecedent;
import uds.information.RemoteFiche_patient;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EnregistrerDossierFinController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Fiche_patient fiche;
    private Main application;
    private BorderPane conteneur;
    private boolean isModification=false;

    // antecedents
    private ObservableList<AntecedentToDisplay> data_antecedent= FXCollections.observableArrayList();
    private ArrayList<Antecedent> tabAntecedent=new ArrayList();
    @FXML
    private Label erreur_antecedent;
    @FXML
    private TableView antecedent;
    @FXML
    private DatePicker date_antecedent;
    @FXML
    private TextField nom_antecedent;
    @FXML
    private ChoiceBox<String> categorie_antecedent;
    @FXML
    private TextArea description_antecedent;
    @FXML
    private TextField pronostique_antecedent;


    //Allergie
    private ObservableList<AllergieToDisplay> data_allergie= FXCollections.observableArrayList();
    private ArrayList<Allergie> tabAllergie = new ArrayList();
    @FXML
    private Label erreur_allergie;
    @FXML
    private TableView allergie;
    @FXML
    private ChoiceBox<String> type_allergie;
    @FXML
    private TextField nom_allergie;
    @FXML
    private TextArea description_allergie;

    public void setFiche(Fiche_patient fiche){
        this.fiche=fiche;
    }
    public void setApp(Main application) {
        this.application = application;
        categorie_antecedent.getItems().addAll("Neuronologie","Pediatrie","Cardiologie");
        type_allergie.getItems().addAll("comprimee","Nouriture");
    }
    public void setContainer(BorderPane b){
        conteneur=b;
    }
    public void ajouterAtecedant(){
        try{
            erreur_antecedent.setText(null);
            String date=date_antecedent.getValue().toString();
            String categorie=categorie_antecedent.getValue().toString();
            String description=description_antecedent.getText();
            String pronostique=pronostique_antecedent.getText();
            Antecedent a=new Antecedent(-1,categorie,description,Date.valueOf(date_antecedent.getValue()),pronostique,fiche.getNum_fiche());
            tabAntecedent.add(a);
            AntecedentToDisplay ant=new AntecedentToDisplay(date, categorie, description, pronostique);
            data_antecedent.add(ant);
        }catch(Exception e){
            erreur_antecedent.setText("Erreur! Rassurez vous que tout est bien rempli.");
        }
    }
    public void ajouterAlergie(){
        try{
            erreur_allergie.setText(null);
            String type=type_allergie.getValue().toString();
            String nom=nom_allergie.getText();
            String description=description_allergie.getText();
            Allergie a=new Allergie(-1, type, nom, description,-1);
            tabAllergie.add(a);
            AllergieToDisplay all=new AllergieToDisplay(type, nom, description);
            data_allergie.add(all);
        }catch(Exception e){
            erreur_allergie.setText("Erreur! Rassurez vous que tout est bien rempli.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        //**********************************************
        //          La table des antecedents
        //**********************************************

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setPrefWidth(75);
        TableColumn categorieCol = new TableColumn("Categorie");
        categorieCol.setPrefWidth(80);
        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setPrefWidth(170);
        TableColumn pronosticCol = new TableColumn("Pronostique");
        pronosticCol.setPrefWidth(100);


        dateCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("date"));
        categorieCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("categorie"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("description"));
        pronosticCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("pronostique"));

        TableColumn<AntecedentToDisplay, AntecedentToDisplay> btnCol = new TableColumn<>("Supprimer");
        btnCol.setMinWidth(75);
        btnCol.setCellValueFactory(new Callback<CellDataFeatures<AntecedentToDisplay, AntecedentToDisplay>, ObservableValue<AntecedentToDisplay>>() {
            @Override
            public ObservableValue<AntecedentToDisplay> call(CellDataFeatures<AntecedentToDisplay, AntecedentToDisplay> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }
        });
        btnCol.setCellFactory(new Callback<TableColumn<AntecedentToDisplay, AntecedentToDisplay>, TableCell<AntecedentToDisplay, AntecedentToDisplay>>() {
          @Override
          public TableCell<AntecedentToDisplay, AntecedentToDisplay> call(TableColumn<AntecedentToDisplay, AntecedentToDisplay> btnCol) {
            return new TableCell<AntecedentToDisplay, AntecedentToDisplay>() {
              final Button button = new Button("Supprimer"); {
              button.setMinWidth(75);
              }
              @Override
              public void updateItem(final AntecedentToDisplay person, boolean empty) {
                super.updateItem(person, empty);
                if (!empty) {
                  setGraphic(button);
                  button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int selectdIndex = getTableRow().getIndex();
                        data_antecedent.remove(selectdIndex);
                        tabAntecedent.remove(selectdIndex);
                    }
                  });
                } else {
                  setGraphic(null);
                }
              }
            };
          }
        });

        antecedent.getColumns().clear();
        antecedent.setItems(data_antecedent);

        antecedent.getColumns().addAll(dateCol, categorieCol, descriptionCol, pronosticCol,btnCol);

        //**********************************************
        //          La table des alergies
        //**********************************************
        TableColumn typeAllCol = new TableColumn("Type");
        typeAllCol.setPrefWidth(50);
        TableColumn nomAllCol = new TableColumn("Nom");
        nomAllCol.setPrefWidth(75);
        TableColumn descriptionAllCol = new TableColumn("Description");
        descriptionAllCol.setPrefWidth(100);


        typeAllCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("type"));
        nomAllCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,String>("nom"));
        descriptionAllCol.setCellValueFactory(new PropertyValueFactory<AntecedentToDisplay,Button>("description"));

        TableColumn<AllergieToDisplay, AllergieToDisplay> btnAllCol = new TableColumn<>("Supprimer");
        btnAllCol.setMinWidth(75);
        btnAllCol.setCellValueFactory(new Callback<CellDataFeatures<AllergieToDisplay, AllergieToDisplay>, ObservableValue<AllergieToDisplay>>() {
            @Override
            public ObservableValue<AllergieToDisplay> call(CellDataFeatures<AllergieToDisplay, AllergieToDisplay> features) {
                return new ReadOnlyObjectWrapper(features.getValue());
            }

        });
        btnAllCol.setCellFactory(new Callback<TableColumn<AllergieToDisplay, AllergieToDisplay>, TableCell<AllergieToDisplay, AllergieToDisplay>>() {
          @Override
          public TableCell<AllergieToDisplay, AllergieToDisplay> call(TableColumn<AllergieToDisplay, AllergieToDisplay> btnAllCol) {
            return new TableCell<AllergieToDisplay, AllergieToDisplay>() {
              final ImageView buttonGraphic = new ImageView();
              final Button button = new Button("Supprimer"); {
                button.setGraphic(buttonGraphic);
                button.setMinWidth(75);
              }
              @Override
              public void updateItem(final AllergieToDisplay person, boolean empty) {
                super.updateItem(person, empty);
                if (!empty) {
                  setGraphic(button);
                  button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int selectdIndex = getTableRow().getIndex();
                        data_allergie.remove(selectdIndex);
                        tabAllergie.remove(selectdIndex);
                    }
                  });
                } else {
                  setGraphic(null);
                }
              }
            };
          }
        });


        allergie.getColumns().clear();
        allergie.setItems(data_allergie);

        allergie.getColumns().addAll(typeAllCol,nomAllCol,descriptionAllCol,btnAllCol);
    }

    @FXML
    public void valider(){
        try {
            if(!isModification){
                Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
                fiche=(Fiche_patient)((RemoteFiche_patient)r).createFiche_patient(fiche);

                r = Naming.lookup("rmi://127.0.0.1/serveurHospital/antecedent");
                for(int i=0;i<tabAntecedent.size();i++){
                    tabAntecedent.get(i).setId_patient(fiche.getNum_fiche());
                    ((RemoteAntecedent)r).saveAntecedent(tabAntecedent.get(i));
                }

                r = Naming.lookup("rmi://127.0.0.1/serveurHospital/allergie");
                for(int i=0;i<tabAllergie.size();i++){
                    tabAllergie.get(i).setId_patient(fiche.getNum_fiche());
                    ((RemoteAllergie)r).saveAllergie(tabAllergie.get(i));
                }

                conteneur.setCenter(new Label("cool"));
            }else{
                Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
                fiche=(Fiche_patient)((RemoteFiche_patient)r).updateFiche_patient(fiche);

                r = Naming.lookup("rmi://127.0.0.1/serveurHospital/antecedent");
                //removeAllAntecedent
                for(int i=0;i<tabAntecedent.size();i++){
                    tabAntecedent.get(i).setId_patient(fiche.getNum_fiche());
                    ((RemoteAntecedent)r).saveAntecedent(tabAntecedent.get(i));
                }

                r = Naming.lookup("rmi://127.0.0.1/serveurHospital/allergie");
                //removeAllAllergie
                for(int i=0;i<tabAllergie.size();i++){
                    tabAllergie.get(i).setId_patient(fiche.getNum_fiche());
                    ((RemoteAllergie)r).saveAllergie(tabAllergie.get(i));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void setToModify() throws NotBoundException, MalformedURLException, RemoteException {
        isModification=true;
        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/antecedent");
        tabAntecedent.clear();
        tabAntecedent=((RemoteAntecedent)r).getAllAntecedentByIdPatient(fiche.getNum_fiche());
        data_antecedent.clear();
        for(int i=0;i<tabAntecedent.size();i++)
            data_antecedent.add(new AntecedentToDisplay(tabAntecedent.get(i).getDate().toString(), tabAntecedent.get(i).getCategorie(), tabAntecedent.get(i).getDescription(), tabAntecedent.get(i).getPronostique()));

        r = Naming.lookup("rmi://127.0.0.1/serveurHospital/allergie");
        tabAllergie.clear();
        tabAllergie=((RemoteAllergie)r).getAllAllergieByIdPatient(fiche.getNum_fiche());
        for(int i=0;i<tabAllergie.size();i++)
            data_allergie.add(new AllergieToDisplay(tabAllergie.get(i).getType(), tabAllergie.get(i).getNom(), tabAllergie.get(i).getDescription()));
    }

}
