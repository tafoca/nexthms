/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur;

import main.Main;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import main.beans.Utilisateur;
import uds.information.RemoteUtilisateur;

/**
 *
 * @author tabueu
 */
public class LoginController extends BorderPane implements Initializable {

    @FXML
    Button valider;
    @FXML
    private TextField login;
    @FXML
    private PasswordField passwd;
    @FXML
    private Label erreurMsg;

    private Main application;

    public void setApp(Main application) {
        this.application = application;
        //contenue initialisation accueil
    }

    @FXML
    private void seConnecter(ActionEvent e) {
        try{
            String log = login.getText();
            String pas = passwd.getText();
            // on va se connecter a la bd et voir si il existe
            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/utilisateur");
            Utilisateur u = new Utilisateur();
            u=(Utilisateur)((RemoteUtilisateur)r).getUtilisateurByLogin(log);

            if(log.length()>0){
                if ( (u.getLogin() == null ? log == null : u.getLogin().equals(log)) && (u.getMot_de_passe() == null ? pas == null : u.getMot_de_passe().equals(pas)) ) {
                    AcceiulController home;
                    home = (AcceiulController) application.chargeur("vue/Acceiul.fxml");
                    home.setApp(application);
                    home.setUser(u);
                    application.setUser(u);
                } else {
                    if( (u.getLogin() == null ? log == null : u.getLogin().equals(log)) )
                        erreurMsg.setText("Meauvais mot de passe");
                    else{
                        erreurMsg.setText("L'utilisateur n'existe pas");
                        System.out.println(u.getLogin()+" - "+log);
                        System.out.println(u.getMot_de_passe()+" - "+pas);
                    }
                }
            }else{
                erreurMsg.setText("Le champs login est vide.");
            }
        }catch(Exception ex){
            erreurMsg.setText("Probleme de connection avec le serveur veuillez contacter l'administrateur");
        }

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

}
