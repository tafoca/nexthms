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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import main.Main;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class UtilisateurActionController extends BorderPane implements Initializable {

    private Main application;
    @FXML
    ComboBox operation_user;

    public void setApp(Main application) {
        this.application = application;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        operation_user.getItems().addAll(
                "ajouter un utilisateur",
                "masquer un utilisateur",
                "modifier un utilisateur",
                "consuter un utilisateur" );
        operation_user.setValue("ajouter un utilisateur");
        System.out.println("le combo courant est ::"+operation_user.getSelectionModel().getSelectedItem().toString());
    }
}
