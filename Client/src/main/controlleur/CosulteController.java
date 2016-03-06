/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.beans.Utilisateur;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class CosulteController extends  BorderPane  implements Initializable {


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
    Button reset;
    Main application;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      }

    public void setApp(Main application) {
        this.application = application;

    }


     @FXML
    public void consultUser() {

            String name = nom.getText();
            String lastname = prenom.getText();
            String tel = phone.getText();
            String add = addresse.getText();
            String log = login.getText();
            String typ = type.getText();
            String pass = password.getText();
            String emailu = email.getText();
            String categorie = droit.getText();
           // String infos = name+ lastname +tel + add + log + typ + pass +emailu + categorie;


        }
    }



