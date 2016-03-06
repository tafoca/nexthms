/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.actionMedicale;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.Main;
import main.beans.Antecedent;
import main.beans.Consultation;
import main.beans.Examen;
import main.beans.FicheMalade;
import main.beans.Fiche_patient;
import main.beans.Ordonance;
import main.util.ConsultationToDisplay;
import main.util.ExamenMedecinToDisplay;
import main.util.FactorielleChargeurCentre;
import main.util.OrdonanceToDisplay;
import uds.information.RemoteAntecedent;
import uds.information.RemoteConsultation;
import uds.information.RemoteExamen;
import uds.information.RemoteFicheMalade;
import uds.information.RemoteFiche_patient;
import uds.information.RemoteOrdonance;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CloreTraitementController implements Initializable {

    @FXML
    private Label erreur;
    @FXML
    private ComboBox categorie;
    @FXML
    private TextArea description;
    @FXML
    private TextField pronostique;
    @FXML
    private VBox all;

    private Main application;
    private BorderPane conteneur;
    private Fiche_patient fiche;

    public void setApp(Main application) {this.application = application;}
    public void setContainer(BorderPane b){conteneur=b;}
    public void setFiche(Fiche_patient f) throws Exception{

        fiche=f;
        categorie.getItems().addAll("Pediatrie","Ophtamologie","Sexologie");

        //tt consultations
        Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/consultation");
        ArrayList<Consultation> tabConsultation=((RemoteConsultation)r).getAllConsultationByIdPatient(fiche.getNum_fiche());

        ObservableList<ConsultationToDisplay> dataConsultation=FXCollections.observableArrayList();
        for(int i=0;i<tabConsultation.size();i++)
            dataConsultation.add(new ConsultationToDisplay(
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

        TableView consultations=new TableView();
        consultations.setPrefHeight(120);
        consultations.setItems(dataConsultation);
        consultations.getColumns().clear();
        consultations.getColumns().addAll(id,motif,date,poids,temperatur,tention,diagnostic,idMedecin);
        all.getChildren().add(new Label("Les consultations effectuees au cours de ce traitement:"));
        all.getChildren().add(consultations);


        //tt les ordonances
        r = Naming.lookup("rmi://127.0.0.1/serveurHospital/ordonance");
        ArrayList<Ordonance> tabOrdonance=((RemoteOrdonance)r).getAllOrdonanceByIdPatient(fiche.getNum_fiche());

        ObservableList<OrdonanceToDisplay> dataOrdonance = FXCollections.observableArrayList();
        for(int i=0;i<tabOrdonance.size();i++)
            dataOrdonance.add(new OrdonanceToDisplay(
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

        TableView ordonances = new TableView();
        ordonances.setPrefHeight(120);
        ordonances.setItems(dataOrdonance);
        ordonances.getColumns().clear();
        ordonances.getColumns().addAll(colNom,colFamille,colForme,colPosologie,colObservation);
        all.getChildren().add(new Label("Les Ordonances prescrites au cours de ce traitement:"));
        all.getChildren().add(ordonances);
        //ts les exams

        r = Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
        ArrayList<Examen> tabExamen=((RemoteExamen)r).getAllExamenByIdPatient(fiche.getNum_fiche());

        ObservableList<ExamenMedecinToDisplay> dataExamen=FXCollections.observableArrayList();
        for(int i=0;i<tabExamen.size();i++)
            dataExamen.add(new ExamenMedecinToDisplay(
                tabExamen.get(i).getNum_patient(),
                tabExamen.get(i).getType(),
                tabExamen.get(i).getNom(),
                tabExamen.get(i).getResulta()
            ));

        TableColumn colId=new TableColumn("id");colId.setPrefWidth(100);
        TableColumn colType=new TableColumn("Type d'exam");colType.setPrefWidth(100);
        TableColumn colNomEx=new TableColumn("Nom exam");colNom.setPrefWidth(100);
        TableColumn colResulta=new TableColumn("Resulta de l'examen");colResulta.setPrefWidth(100);

        colId.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("type"));
        colNomEx.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("nom"));
        colResulta.setCellValueFactory(new PropertyValueFactory<ExamenMedecinToDisplay,String>("resulta"));

        TableView examens = new TableView();
        examens.setPrefHeight(120);
        examens.setItems(dataExamen);
        examens.getColumns().clear();
        examens.getColumns().addAll(colId,colType,colNomEx,colResulta);
        all.getChildren().add(new Label("Les Examens effectuees au cours de ce traitement:"));
        all.getChildren().add(examens);
    }
    @FXML
    public void retour() throws IOException{
        FactorielleChargeurCentre chargeur=new FactorielleChargeurCentre();
        HomeMaladeController con=(HomeMaladeController)chargeur.sonFactory(conteneur, "vue/actionMedicale/HomeMalade.fxml");
        con.setApp(application);
        con.setContainer(conteneur);
        con.setFiche(fiche);
    }
    @FXML
    public void valider(){
        try{
            erreur.setText(null);
            // on recup primo la date a partir du dossier du malade
            Remote r=Naming.lookup("rmi://127.0.0.1/serveurHospital/ficheMalade");
            FicheMalade fm = (FicheMalade) ((RemoteFicheMalade)r).getFicheMalade(fiche);

            //tt consultations
            r = Naming.lookup("rmi://127.0.0.1/serveurHospital/consultation");
            ArrayList<Consultation> tabConsultation=((RemoteConsultation)r).getAllConsultationByIdPatient(fiche.getNum_fiche());
            for(int i=0;i<tabConsultation.size();i++){
                tabConsultation.get(i).setNouveauTraitement(false);
                ((RemoteConsultation)r).remove(tabConsultation.get(i));
            }
            //tt les ordonances
            r = Naming.lookup("rmi://127.0.0.1/serveurHospital/ordonance");
            ArrayList<Ordonance> tabOrdonance=((RemoteOrdonance)r).getAllOrdonanceByIdPatient(fiche.getNum_fiche());
            for(int i=0;i<tabOrdonance.size();i++){
                tabOrdonance.get(i).setNouveauTraitement(false);
                ((RemoteOrdonance)r).remove(tabOrdonance.get(i));
            }

            //ts les exams

            r = Naming.lookup("rmi://127.0.0.1/serveurHospital/examen");
            ArrayList<Examen> tabExamen=((RemoteExamen)r).getAllExamenByIdPatient(fiche.getNum_fiche());
            for(int i=0;i<tabExamen.size();i++){
                tabExamen.get(i).setNouveauTraitement(false);
                ((RemoteExamen)r).remove(tabExamen.get(i));
            }

            Antecedent a = new Antecedent(-1, categorie.getValue().toString(), description.getText(), fm.getDateArrive(), pronostique.getText(), fiche.getNum_fiche());

            r=Naming.lookup("rmi://127.0.0.1/serveurHospital/antecedent");
            a=(Antecedent) ((RemoteAntecedent)r).saveAntecedent(a);

            for(int i=0;i<application.getMalades().size();i++){
                if(application.getMalades().get(i).getNum_fiche()==fiche.getNum_fiche()){
                    application.getMalades().remove(i);
                    break;
                }
            }
            fiche.setEstEnCoursDeTraitement(false);
            r=Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
            fiche=(Fiche_patient) ((RemoteFiche_patient)r).updateFiche_patient(fiche);

            conteneur.setCenter(new Label("LOL"));
        }catch(Exception e){
            erreur.setText("Verifiez ce que vous entrez svp.");
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
