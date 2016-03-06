/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.controlleur.patient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.Main;
import main.beans.Fiche_patient;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EditPatientController implements Initializable {
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField profetionField;
    @FXML
    private DatePicker ageField;
    @FXML
    private TextField telField;
    @FXML
    private ChoiceBox<String> sexeField;

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
        nomField.setPromptText(fiche.getNom());
        prenomField.setPromptText(fiche.getPrenom());
        profetionField.setPromptText(fiche.getProfession());
        sexeField.getItems().addAll("M","F");
        telField.setPromptText(fiche.getTel()+"");
    }
    @FXML
    public void valider() throws IOException{
        System.out.println("dddd\nssss");
        fiche.setDate_naissance(new Date(ageField.getValue().getYear(),ageField.getValue().getMonthValue(),ageField.getValue().getDayOfMonth()));
        fiche.setNom(nomField.getText());
        fiche.setPrenom(prenomField.getText());
        fiche.setProfession(profetionField.getText());
        fiche.setSexe(sexeField.getValue().toString().charAt(0));
        fiche.setTel(Long.parseLong(telField.getText()));

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
            border.setTop(page);
            InfoPatientController i = loader.getController();
            i.setAppAndCenterAndFile(application, border, fiche);

    }

}
