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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;
import main.beans.Examen;
import main.beans.Fiche_patient;
import main.controlleur.dossier.ConsulterDossierFinController;
import main.util.ExamenLaboToDisplay;
import main.util.FactorielleChargeurCentre;
import main.util.FichePatientToDisplay;
import uds.information.RemoteExamen;
import uds.information.RemoteFiche_patient;


/**
 * FXML Controller class
 *
 * @author user
 */
public class LaboratoireController implements Initializable {

    @FXML
    private TableView tableau;
    @FXML
    private TableColumn nom;
    @FXML
    private TableColumn prenom;
    @FXML
    private TableColumn typeExam;
    @FXML
    private TableColumn nomExam;
    @FXML
    private TableColumn resulta;
    @FXML
    private TableColumn idMedecin;
    @FXML
    private TableColumn valider;

    ArrayList<Examen> tab;
    ObservableList<ExamenLaboToDisplay> exams;

    private Main application;
    private BorderPane conteneur;

    public void setApp(Main application) {
        this.application = application;
    }
    public void setContainer(BorderPane b) throws Exception{
        conteneur=b;

        nom.setCellValueFactory(new PropertyValueFactory<ExamenLaboToDisplay,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<ExamenLaboToDisplay,String>("prenom"));
        typeExam.setCellValueFactory(new PropertyValueFactory<ExamenLaboToDisplay,String>("typeExam"));
        nomExam.setCellValueFactory(new PropertyValueFactory<ExamenLaboToDisplay,String>("nomExam"));

        idMedecin.setCellValueFactory(new PropertyValueFactory<ExamenLaboToDisplay,String>("idMedecin"));

        resulta.setCellValueFactory(new PropertyValueFactory<ExamenLaboToDisplay,String>("resulta"));
        valider.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenLaboToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ExamenLaboToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });

        valider.setCellFactory(new Callback<TableColumn<ExamenLaboToDisplay, Boolean>, TableCell<ExamenLaboToDisplay, Boolean>>() {

            @Override
            public TableCell<ExamenLaboToDisplay, Boolean> call(TableColumn<ExamenLaboToDisplay, Boolean> p) {
                return new ButtonCell();
            }
        });

        Remote r=Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
        tab = ((RemoteExamen)r).getAllExamen();
        exams=FXCollections.observableArrayList();
        for(int i=0;i<tab.size();i++){
            Remote rm=Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
            Fiche_patient f = (Fiche_patient) ((RemoteFiche_patient)rm).getFiche_patientById(tab.get(i).getNum_patient());
            exams.add(new ExamenLaboToDisplay(f.getNom(), f.getPrenom(), tab.get(i).getType(), tab.get(i).getNom(), tab.get(i).getResulta(), tab.get(i).getId_medecin(), Boolean.TRUE));
        }

        tableau.setItems(exams);
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private class ButtonCell extends TableCell<ExamenLaboToDisplay, Boolean> {

        final Button cellButton = new Button("valider l'examen");{
            cellButton.setPrefWidth(120);
        }

        ButtonCell(){

            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex();

                    //delete the selected item in data
                    Stage tmp=new Stage();
                    VBox h=new VBox();
                    TextField text=new TextField();
                    Button b=new Button("Valider l'examen");
                    b.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                Remote r =Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
                                tab.get(selectdIndex).setResulta(text.getText());
                                Examen exam=tab.get(selectdIndex);
                                exam = (Examen)((RemoteExamen)r).updateExamen(exam);
                                exams.get(selectdIndex).setResulta(exam.getResulta());
                                exams.remove(selectdIndex);
                                tmp.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    h.getChildren().addAll(text,b);
                    Scene sc=new Scene(h, 200 , 100);
                    tmp.setScene(sc);
                    tmp.show();

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
