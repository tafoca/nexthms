/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur;

import java.awt.TextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import main.Main;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class UtilisateurController extends BorderPane implements Initializable {

    @FXML
    TextField nom;
    @FXML
    TextField prenom;
    @FXML
    TextField phone;
    @FXML
    TextField addresse;
    @FXML
    TextField login;
    @FXML
    TextField type;
    @FXML
    PasswordField password;
    @FXML
    TextField email;
    @FXML
    TextField droit;
    @FXML
    Button valider;
    @FXML
    Button anuler;
    @FXML
    ChoiceBox operation_usr;
    //@FXML
    //Label indication;
    Main application;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operation_usr.getItems().addAll("ajouter ", "masquer", "modifier", "consuter");
        operation_usr.getSelectionModel().selectFirst();
    }

    public void setApp(Main application) {
        this.application = application;

    }

    @FXML
    public void ajouterUser() {
        if ("ajouter".equals(operation_usr.getSelectionModel().getSelectedItem().toString())) {
            String name = nom.getText();
            String lastname = prenom.getText();
            String tel = phone.getText();
            String add = addresse.getText();
            String log = login.getText();
            String typ = type.getText();
            String pass = password.getText();
            String emailu = email.getText();
            String categorie = droit.getText();
            String infos = name + lastname + tel + add + log + typ + pass + emailu + categorie;

            System.out.println("les donnes sur l' utililisateur sont les suivantes ::" + infos);

        } else {
        }
    }


    @FXML
    public void resetOperation(ActionEvent act) {
        nom.setText("");
        prenom.setText("");
        phone.setText("");
        addresse.setText("");
        login.setText("");
        type.setText("");
        password.setText("");
        email.setText("");
        droit.setText("");
    }
}
