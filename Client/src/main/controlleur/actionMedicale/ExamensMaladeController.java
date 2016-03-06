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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import main.Main;
import main.beans.Examen;
import main.beans.Fiche_patient;
import main.util.ExamenMedecinToDisplay;
import main.util.FactorielleChargeurCentre;
import uds.information.RemoteExamen;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ExamensMaladeController implements Initializable {

    @FXML
    private ComboBox type;
    @FXML
    private TextField nom;
    @FXML
    private TableView examens;
    @FXML
    private Label ereur;

    private ObservableList<ExamenMedecinToDisplay> data=FXCollections.observableArrayList();
    private ArrayList<Examen> tabExamen=new ArrayList();

    private Main application;
    private BorderPane conteneur;
    private Fiche_patient fiche;

    public void setApp(Main application) {this.application = application;}
    public void setContainer(BorderPane b){conteneur=b;}
    public void setFiche(Fiche_patient f) throws RemoteException, NotBoundException, MalformedURLException{
        fiche=f;

        type.getItems().addAll("Cardiologie","Neurologie","Organique","depistage");

        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
        tabExamen=((RemoteExamen)r).getAllExamenByIdPatient(fiche.getNum_fiche());

        for(int i=0;i<tabExamen.size();i++)
            data.add(new ExamenMedecinToDisplay(
                tabExamen.get(i).getNum_patient(),
                tabExamen.get(i).getType(),
                tabExamen.get(i).getNom(),
                tabExamen.get(i).getResulta()
            ));

        TableColumn colId=new TableColumn("id");colId.setPrefWidth(100);
        TableColumn colType=new TableColumn("Type d'exam");colType.setPrefWidth(100);
        TableColumn colNom=new TableColumn("Nom exam");colNom.setPrefWidth(100);
        TableColumn colResulta=new TableColumn("Resulta de l'examen");colResulta.setPrefWidth(100);

        colId.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("type"));
        colNom.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("nom"));
        colResulta.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("resulta"));


        TableColumn<ExamenMedecinToDisplay, Boolean> btnCol = new TableColumn<>("Supprimer");
        btnCol.setPrefWidth(120);
        btnCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExamenMedecinToDisplay, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ExamenMedecinToDisplay, Boolean> features) {
                return new ReadOnlyObjectWrapper(features.getValue()!=null);
            }
        });
        btnCol.setCellFactory(new Callback<TableColumn<ExamenMedecinToDisplay, Boolean>, TableCell<ExamenMedecinToDisplay, Boolean>>() {

            @Override
            public TableCell<ExamenMedecinToDisplay, Boolean> call(TableColumn<ExamenMedecinToDisplay, Boolean> p) {
                return new ButtonCell();
            }

        });




        examens.setItems(data);
        examens.getColumns().clear();
        examens.getColumns().addAll(colId,colType,colNom,colResulta,btnCol);

    }

    @FXML
    public void valider(){
        try{
            Examen e =new Examen(-1, type.getValue().toString(), fiche.getNum_fiche(), nom.getText(), null, application.getUser().getNum_utilisateur(), true);
            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
            e=(Examen) ((RemoteExamen)r).saveExamen(e);
            tabExamen.add(e);
            ExamenMedecinToDisplay emtd=new ExamenMedecinToDisplay(e.getId_medecin(), e.getType(), e.getNom(), e.getResulta()==null?"...":e.getResulta());
            data.add(emtd);
        }catch(NullPointerException e){
            ereur.setText("Erreur! les donnes entrees ne sont pas correctes");
        }catch(Exception e){
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
    private class ButtonCell extends TableCell<ExamenMedecinToDisplay, Boolean> {
        final Button cellButton = new Button("Supprimer");{
            cellButton.setPrefWidth(120);
        }

        ButtonCell(){

            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        ExamenMedecinToDisplay c=data.get(selectdIndex);
                        //puis on enleve c de la bd et apres...
                        data.remove(selectdIndex);
                        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
                        ((RemoteExamen)r).remove(tabExamen.get(selectdIndex));
                        tabExamen.remove(selectdIndex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(ExamensMaladeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ExamensMaladeController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ExamensMaladeController.class.getName()).log(Level.SEVERE, null, ex);
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
