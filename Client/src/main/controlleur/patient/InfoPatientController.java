/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.patient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.Main;
import main.beans.Fiche_patient;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InfoPatientController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label profetion;
    @FXML
    private Label age;
    @FXML
    private Label sexe;
    @FXML
    private Label tel;

    private Main application;
    private BorderPane border;
    Fiche_patient fiche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setAppAndCenterAndFile(Main application,BorderPane b,Fiche_patient fiche) {
        this.application = application;
        this.border = b;
        this.fiche=fiche;
        nom.setText(fiche.getNom()+"  "+fiche.getPrenom());
        profetion.setText(fiche.getProfession());
        age.setText(fiche.getDate_naissance().toString());
        sexe.setText(fiche.getSexe()=='M'?"Homme":"Femme");
        tel.setText(fiche.getTel()+"");
    }

    @FXML
    public void editFichePatient() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            InputStream in = Main.class.getResourceAsStream("vue/patient/editPatient.fxml");
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("vue/patient/editPatient.fxml"));
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("vue/patient/editPatient.fxml"));
            HBox page;
            try {
                page = (HBox) loader.load(in);
            } finally {
                in.close();
            }
            border.setTop(page);
            EditPatientController e = loader.getController();
            e.setAppAndCenterAndFile(application, border, fiche);
    }

}
