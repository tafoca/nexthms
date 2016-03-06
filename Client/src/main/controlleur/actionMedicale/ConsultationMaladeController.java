/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.actionMedicale;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.Main;
import main.beans.Consultation;
import main.beans.Fiche_patient;
import main.util.ConsultationToDisplay;
import main.util.FactorielleChargeurCentre;
import uds.information.RemoteConsultation;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ConsultationMaladeController implements Initializable {

    @FXML
    private TableView consultations;
    @FXML
    private TextArea motif;
    @FXML
    private TextField temperature;
    @FXML
    private TextField tention;
    @FXML
    private TextField poids;
    @FXML
    private TextArea avis;
    @FXML
    private Label erreur;

    private Main application;
    private BorderPane conteneur;
    private Fiche_patient fiche;
    private ObservableList<ConsultationToDisplay> data=FXCollections.observableArrayList();
    private ArrayList<Consultation> tabConsultation=new ArrayList();

    public void setApp(Main application) {this.application = application;}
    public void setContainer(BorderPane b){conteneur=b;}
    public void setFiche(Fiche_patient f) throws NotBoundException, MalformedURLException, RemoteException{
        fiche=f;
        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/consultation");
        tabConsultation=((RemoteConsultation)r).getAllConsultationByIdPatient(fiche.getNum_fiche());

        for(int i=0;i<tabConsultation.size();i++)
            data.add(new ConsultationToDisplay(
                    tabConsultation.get(i).getNum_consultation(),
                    tabConsultation.get(i).getMotif(),
                    tabConsultation.get(i).getDate().toString(),
                    tabConsultation.get(i).getPoids(),
                    tabConsultation.get(i).getTemperature(),
                    tabConsultation.get(i).getTention(),
                    tabConsultation.get(i).getDiagnostic(),
                    tabConsultation.get(i).getId_medecin()
            ));

        TableColumn id=new TableColumn("Identifiant");id.setPrefWidth(70);
        TableColumn motif=new TableColumn("Motif");motif.setPrefWidth(300);
        TableColumn date=new TableColumn("Date");date.setPrefWidth(50);
        TableColumn poids=new TableColumn("Poids");poids.setPrefWidth(50);
        TableColumn temperatur=new TableColumn("Temperature"); temperatur.setPrefWidth(70);
        TableColumn tention=new TableColumn("Tention");tention.setPrefWidth(50);
        TableColumn diagnostic=new TableColumn("Diagnostic");diagnostic.setPrefWidth(200);
        TableColumn idMedecin=new TableColumn("Id medecin");idMedecin.setPrefWidth(85);

        id.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("num_consultation"));
        motif.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("motif"));
        date.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("date"));
        poids.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("poids"));
        temperatur.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("temperature"));
        tention.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("tention"));
        diagnostic.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("diagnostic"));
        idMedecin.setCellValueFactory(new PropertyValueFactory<ConsultationToDisplay,String>("id_medecin"));

        TableColumn<ConsultationToDisplay, Boolean> btnCol = new TableColumn<>("Supprimer");
        btnCol.setPrefWidth(120);
        btnCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ConsultationToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ConsultationToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });
        btnCol.setCellFactory(new Callback<TableColumn<ConsultationToDisplay, Boolean>, TableCell<ConsultationToDisplay, Boolean>>() {

            @Override
            public TableCell<ConsultationToDisplay, Boolean> call(TableColumn<ConsultationToDisplay, Boolean> p) {
                return new ButtonCell();
            }
        });        consultations.setItems(data);
        consultations.getColumns().clear();
        consultations.getColumns().addAll(id,motif,date,poids,temperatur,tention,diagnostic,idMedecin,btnCol);
    }
    public void ajouter(){
        try {
            erreur.setText(null);
            Consultation c=new Consultation(-1, motif.getText(), Date.valueOf(LocalDate.now()), Integer.parseInt(poids.getText()), Integer.parseInt(temperature.getText()), Integer.parseInt(temperature.getText()), avis.getText(), application.getUser().getNum_utilisateur(), fiche.getNum_fiche(), true);
            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/consultation");
            c=(Consultation) ((RemoteConsultation)r).saveConsultation(c);
            tabConsultation.add(c);
            data.add(new ConsultationToDisplay(c.getNum_consultation(), c.getMotif(),c.getDate().toString(),c.getPoids(),c.getTemperature(),c.getTention(),c.getDiagnostic(),c.getId_medecin()));
        } catch (Exception e) {
            erreur.setText("Verifiez l'integralite des donnes");
            e.printStackTrace();
        }
    }
    @FXML
    public void retour() throws IOException{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        HomeMaladeController con=(HomeMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/HomeMalade.fxml");
        con.setApp(application);
        con.setContainer(conteneur);
        con.setFiche(fiche);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private class ButtonCell extends TableCell<ConsultationToDisplay, Boolean> {
        final Button cellButton = new Button("Supprimer");{
            cellButton.setPrefWidth(120);
        }

        ButtonCell(){

            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        ConsultationToDisplay c=data.get(selectdIndex);
                        //puis on enleve c de la bd et apres...
                        data.remove(selectdIndex);
                        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/consultation");
                        ((RemoteConsultation)r).remove(tabConsultation.get(selectdIndex));
                        tabConsultation.remove(selectdIndex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(ConsultationMaladeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ConsultationMaladeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ConsultationMaladeController.class.getName()).log(Level.SEVERE, null, ex);
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
