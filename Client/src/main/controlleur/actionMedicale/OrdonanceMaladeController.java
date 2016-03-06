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
import javafx.scene.control.ComboBox;
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
import main.beans.Fiche_patient;
import main.beans.Ordonance;
import main.util.FactorielleChargeurCentre;
import main.util.OrdonanceToDisplay;
import uds.information.RemoteOrdonance;

/**
 * FXML Controller class
 *
 * @author user
 */
public class OrdonanceMaladeController implements Initializable {

    @FXML
    private Label erreur;
    @FXML
    private TextField nom_medicament;
    @FXML
    private ComboBox famille_medicament;
    @FXML
    private ComboBox forme_medicament;
    @FXML
    private TextArea posologie;
    @FXML
    private TextArea observations;

    @FXML
    private TableView ordonances;

    private ObservableList<OrdonanceToDisplay> data=FXCollections.observableArrayList();
    private ArrayList<Ordonance> tabOrdonance=new ArrayList();

    private Main application;
    private BorderPane conteneur;
    private Fiche_patient fiche;

    public void setApp(Main application) {this.application = application;}
    public void setContainer(BorderPane b){conteneur=b;}
    public void setFiche(Fiche_patient f) throws NotBoundException, MalformedURLException, RemoteException{
        fiche=f;


        famille_medicament.getItems().addAll("Virusticide","MicrobeTicide","BacterieTicide");
        forme_medicament.getItems().addAll("Comprime","Liquide","Capsule");

        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/ordonance");
        tabOrdonance=((RemoteOrdonance)r).getAllOrdonanceByIdPatient(fiche.getNum_fiche());

        for(int i=0;i<tabOrdonance.size();i++)
            data.add(new OrdonanceToDisplay(
                    tabOrdonance.get(i).getNom_medicament(),
                    tabOrdonance.get(i).getFamille_medicament(),
                    tabOrdonance.get(i).getForme_medicament(),
                    tabOrdonance.get(i).getPosologie(),
                    tabOrdonance.get(i).getObservation()
            ));

        TableColumn colNom=new TableColumn("Nom");colNom.setPrefWidth(100);
        TableColumn colFamille=new TableColumn("Famille");colFamille.setPrefWidth(100);
        TableColumn colForme=new TableColumn("Forme");colForme.setPrefWidth(100);
        TableColumn colPosologie=new TableColumn("Posologie");colPosologie.setPrefWidth(200);
        TableColumn colObservation=new TableColumn("Observation");colObservation.setPrefWidth(290);

        colNom.setCellValueFactory(new PropertyValueFactory<OrdonanceToDisplay,String>("nom"));
        colFamille.setCellValueFactory(new PropertyValueFactory<OrdonanceToDisplay,String>("famille"));
        colForme.setCellValueFactory(new PropertyValueFactory<OrdonanceToDisplay,String>("forme"));
        colPosologie.setCellValueFactory(new PropertyValueFactory<OrdonanceToDisplay,String>("posologie"));
        colObservation.setCellValueFactory(new PropertyValueFactory<OrdonanceToDisplay,String>("observation"));

        TableColumn<OrdonanceToDisplay, Boolean> btnCol = new TableColumn<>("Supprimer");
        btnCol.setPrefWidth(120);
        btnCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrdonanceToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<OrdonanceToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });
        btnCol.setCellFactory(new Callback<TableColumn<OrdonanceToDisplay, Boolean>, TableCell<OrdonanceToDisplay, Boolean>>() {

            @Override
            public TableCell<OrdonanceToDisplay, Boolean> call(TableColumn<OrdonanceToDisplay, Boolean> p) {
                return new ButtonCell();
            }

        });

        TableColumn<OrdonanceToDisplay, Boolean> btnColPrint = new TableColumn<>("Impprimer");
        btnColPrint.setPrefWidth(120);
        btnColPrint.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<OrdonanceToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<OrdonanceToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });
        btnColPrint.setCellFactory(new Callback<TableColumn<OrdonanceToDisplay, Boolean>, TableCell<OrdonanceToDisplay, Boolean>>() {

            @Override
            public TableCell<OrdonanceToDisplay, Boolean> call(TableColumn<OrdonanceToDisplay, Boolean> p) {
                return new ButtonCellPrint();
            }

        });
        ordonances.setItems(data);
        ordonances.getColumns().clear();
        ordonances.getColumns().addAll(colNom,colFamille,colForme,colPosologie,colObservation,btnCol,btnColPrint);

    }
    @FXML
    public void ajouter(){
        try{
            erreur.setText(null);
            Ordonance o=new Ordonance(-1,Date.valueOf(LocalDate.now()), nom_medicament.getText(), famille_medicament.getValue().toString(), forme_medicament.getValue().toString(), posologie.getText(),observations.getText(),true,application.getUser().getNum_utilisateur(),fiche.getNum_fiche());
            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/ordonance");
            o=(Ordonance) ((RemoteOrdonance)r).saveOrdonance(o);
            tabOrdonance.add(o);
            OrdonanceToDisplay otd=new OrdonanceToDisplay(o.getNom_medicament(), o.getFamille_medicament(), o.getForme_medicament(), o.getPosologie(), o.getObservation());
            data.add(otd);
        }catch(Exception e){
            erreur.setText("Erreur");
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
    private class ButtonCell extends TableCell<OrdonanceToDisplay, Boolean> {
        final Button cellButton = new Button("Supprimer");{
            cellButton.setPrefWidth(120);
        }

        ButtonCell(){

            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        OrdonanceToDisplay c=data.get(selectdIndex);
                        //puis on enleve c de la bd et apres...
                        data.remove(selectdIndex);
                        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/ordonance");
                        ((RemoteOrdonance)r).remove(tabOrdonance.get(selectdIndex));
                        tabOrdonance.remove(selectdIndex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(OrdonanceMaladeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(OrdonanceMaladeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(OrdonanceMaladeController.class.getName()).log(Level.SEVERE, null, ex);
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
    private class ButtonCellPrint extends TableCell<OrdonanceToDisplay, Boolean> {
        final Button cellButton = new Button("Imprimer l'ordonance");{
            cellButton.setPrefWidth(150);
        }

        ButtonCellPrint(){

            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    //On dit  au serveur d'imprimer
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
