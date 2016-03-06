/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.patient;

import java.io.InputStream;
import main.beans.Fiche_patient;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import main.Main;
import uds.information.RemoteFiche_patient;

/**
 * FXML Controller class
 *
 * @author Adrien MOMO
 */
public class LoadPatientController extends BorderPane implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField id;
    @FXML
    private TextField cni;
    @FXML
    private Label messageError;

    private Main application;
    BorderPane border;
    @FXML
    private ToggleGroup group;
    /**
     * Initializes the contoller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setDisable(true);
        cni.setDisable(true);
    }


    public void setAppAndCenter(Main application,BorderPane b) {
        this.application = application;
        this.border = b;
    }

    @FXML
    public void activerNom(){
        nom.setDisable(false);
        id.setDisable(true);
        cni.setDisable(true);
    }
    @FXML
    public void activerId(){
        nom.setDisable(true);
        id.setDisable(false);
        cni.setDisable(true);
    }
    @FXML
    public void activerCni(){
        nom.setDisable(true);
        id.setDisable(true);
        cni.setDisable(false);
    }
    @FXML
    public void valider(){
        if(true){
            try {
                Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
                Fiche_patient patient= new Fiche_patient();
                if(!nom.isDisable()){
                    patient = (Fiche_patient)((RemoteFiche_patient)r).getFiche_patientByNom(nom.getText());
                }else if(!id.isDisable()){
                    patient = (Fiche_patient)((RemoteFiche_patient)r).getFiche_patientById(Long.parseLong(id.getText()));
                }else{
                    patient = (Fiche_patient)((RemoteFiche_patient)r).getFiche_patientById(Long.parseLong(id.getText()));
                }

                if(patient.getNum_fiche()!=-1){
                    FXMLLoader loader = new FXMLLoader();
                    InputStream in = Main.class.getResourceAsStream("vue/patient/homePatient.fxml");
                    loader.setBuilderFactory(new JavaFXBuilderFactory());
                    loader.setLocation(Main.class.getResource("vue/patient/homePatient.fxml"));
                    BorderPane page;
                    try {
                        page = (BorderPane) loader.load(in);
                    } finally {
                        in.close();
                    }

                    border.setCenter(page);
                    HomePatientController h = loader.getController();
                    h.setAppAndCenter(application, border);
                    h.setFiche_patient(patient);
                }else{
                    messageError.setText("Le patient n'existe pas.");
                }
            } catch (Exception ex) {
                //System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }else{

        }
    }


}
