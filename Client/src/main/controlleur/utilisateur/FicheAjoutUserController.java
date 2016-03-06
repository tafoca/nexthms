/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur.utilisateur;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.beans.Utilisateur;
import main.util.UtilisateurToDisplay;
import uds.information.RemoteUtilisateur;

/**
 * FXML Controller class
 *
 * @author cabrel
 */
public class FicheAjoutUserController implements Initializable {

    @FXML
    TextField id_usr;
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
    ComboBox type;
    @FXML
    PasswordField password;
    @FXML
    TextField email;
    @FXML
    ComboBox droit;
    @FXML
    Button valider;
    @FXML
    Button reset;
    boolean isAjouter = false;
    private UtilisateurToDisplay person;
    Utilisateur usager;
    private Stage dialogStage;
    private boolean okClicked = false;
    Main application;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     * @param text
     */
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;

    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param
     */
    public void setPerson(UtilisateurToDisplay person) {
        this.person = person;
        id_usr.setText(Double.toString(person.getId_usr()));
        nom.setText(person.getNom_usr());
        prenom.setText(person.getPrenom_usr());
        login.setText(person.getLogin_usr());
        phone.setText(Integer.toString(person.getPhone_usr()));
        type.getItems().addAll("L", "M", "G", "S");
        type.setValue(person.getType_usr());
        password.setText(person.getPass_usr());
        addresse.setText(person.getAdresse());
        email.setText(person.getMail_usr());
        droit.getItems().addAll("DESACTIVED", "ACTIVED");
        droit.setValue(person.getStatut_usr());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setToAjouter() {
        isAjouter = true;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
             person.setNom_usr(nom.getText());
                person.setPrenom_usr(prenom.getText());
                person.setPass_usr(password.getText());
                person.setPhone_usr(Integer.parseInt(phone.getText()));
                person.setId_usr(Long.parseLong(id_usr.getText()));
                person.setLogin_usr(login.getText());
                person.setMail_usr(email.getText());
                person.setProfil_usr((String) droit.getSelectionModel().getSelectedItem());
               person.setAdresse(addresse.getText());
            if (isAjouter) {
                System.out.println("ajout");
                try {
                    Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/utilisateur");
                    usager = new Utilisateur();
                    usager.setLogin(person.getLogin_usr());
                    usager.setMail(person.getMail_usr());
                    usager.setMot_de_passe(person.getPass_usr());
                    usager.setNom(person.getNom_usr());
                    usager.setNum_utilisateur(person.getId_usr());
                    usager.setPhone(person.getPhone_usr());
                    usager.setProil(person.getProfil_usr());
                    usager.setType(person.getType_usr().charAt(0));
                    usager.setStatut(person.getStatut_usr());
                    usager = (Utilisateur) ((RemoteUtilisateur) r).AjouterUtilisateur(usager);
                } catch (MalformedURLException | NotBoundException | RemoteException e) {
                    System.err.println("erreur manque connection distante" + e.getMessage());
                }
            }else {
            System.out.println("modification");
                try {
                    Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/utilisateur");
                    usager = new Utilisateur();
                    usager.setLogin(person.getLogin_usr());
                    usager.setMail(person.getMail_usr());
                    usager.setMot_de_passe(person.getPass_usr());
                    usager.setNom(person.getNom_usr());
                    usager.setNum_utilisateur(person.getId_usr());
                    usager.setPhone(person.getPhone_usr());
                    usager.setProil(person.getProfil_usr());
                    usager.setType(person.getType_usr().charAt(0));
                    usager.setStatut(person.getStatut_usr());
                    usager = (Utilisateur) ((RemoteUtilisateur) r).UpdateUtilisateur(usager);
                } catch (MalformedURLException | NotBoundException | RemoteException e) {
                    System.err.println("erreur manque connection distante" + e.getMessage());
                }

            }

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (prenom.getText() == null || prenom.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }

        if (login.getText() == null || login.getText().length() == 0) {
            errorMessage += "No valid login code!\n";
        }
        if (type.getSelectionModel().getSelectedItem().toString() == null || type.getSelectionModel().getSelectedItem().toString().length() == 0) {
            errorMessage += "enter valid type code!\n";
        }

        if (addresse.getText() == null || addresse.getText().length() == 0) {
            errorMessage += "No valid  adrress enter!\n";
        }
        if (phone.getText() == null || phone.getText().length() == 0) {
            errorMessage += "No valid phone!\n";

        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(phone.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid phone number code (must be an integer)!\n";
            }
        }

        if (droit.getSelectionModel().getSelectedItem().toString() == null || droit.getSelectionModel().getSelectedItem().toString().length() == 0) {
            errorMessage += "No validmail Right  (more than 4 character)!\n";
        }

        if (email.getText() == null || email.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        } else {
            /*  if (!DateUtil.validDate(birthdayField.getText())) {
             errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
             }*/
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
           /* Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(dialogStage);
             alert.setTitle("Invalid Fields");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText(errorMessage);

             alert.showAndWait();*/

            return false;
        }
    }

}
