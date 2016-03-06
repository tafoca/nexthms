/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur.Ressource;

import com.sun.jmx.snmp.BerDecoder;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.beans.emploi_de_temps;
import main.util.TimeTableToDisplay;
import uds.information.RemoteEmploiTemps;

/**
 * FXML Controller class
 *
 * @author cabrel
 */
public class AjoutHeureTravailController implements Initializable {

    @FXML
    TextField lundi;
    @FXML
    TextField mardi;
    @FXML
    TextField mercredi;
    @FXML
    TextField jeudi;
    @FXML
    TextField vendredi;
    @FXML
    TextField samedi;
    @FXML
    ComboBox heure;
    @FXML
    Button ok;
    @FXML
    Button cancel;
    @FXML
    TextField user;
    @FXML
    Label errormesssage;
    private TimeTableToDisplay timeperson;
    private Stage dialogStage;
    private boolean okClicked = false;
    Main application;
    private boolean isAjouter=false;
    emploi_de_temps usager;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }

    /**
     * Sets the time person to be edited in the dialog.
     *
     * @param
     */
    public void setTimePerson(TimeTableToDisplay timeperson) {
        this.timeperson = timeperson;
        lundi.setText(timeperson.getLundi());
        mardi.setText(timeperson.getMardi());
        mercredi.setText(timeperson.getMercredi());
        jeudi.setText(timeperson.getJeudi());
        vendredi.setText(timeperson.getVendredi());
        heure.getItems().addAll("7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");
        heure.setValue(timeperson.getHoraire().getHours());
        samedi.setText(timeperson.getSamedi());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void handleOk() {
        if (isInputValid()) {
            timeperson.setLundi(lundi.getText());
            timeperson.setMardi(mardi.getText());
            timeperson.setMercredi(mercredi.getText());
            timeperson.setJeudi(jeudi.getText());
            timeperson.setVendredi(vendredi.getText());
            timeperson.setSamedi(samedi.getText());
            timeperson.setHoraire(Time.valueOf(LocalTime.of(Integer.parseInt(heure.getSelectionModel().getSelectedItem().toString()), 0)));
             if (isAjouter) {
                System.out.println("ajout");
                try {
                    Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/emploi_de_temps");
                    usager = new emploi_de_temps();
                    usager.setLundi(timeperson.getLundi());
                    usager.setMardi(timeperson.getMardi());
                    usager.setMercredi(timeperson.getMercredi());
                    usager.setJeudi(timeperson.getJeudi());
                    usager.setVendredi(timeperson.getVendredi());
                    usager.setSamedi(timeperson.getSamedi());
                    usager.setHoraire(timeperson.getHoraire());
                 //   usager.setMercredi(timeperson.get);
                    usager = (emploi_de_temps)((RemoteEmploiTemps) r).Ajouteremploi_de_temps(usager);
                } catch (MalformedURLException | NotBoundException | RemoteException e) {
                    System.err.println("erreur manque connection distante" + e.getMessage());
                }
            }else {
            System.out.println("modification");
                try {
                    Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/emploi_de_temps");
                    usager = new emploi_de_temps();
                    usager = new emploi_de_temps();
                    usager.setLundi(timeperson.getLundi());
                    usager.setMardi(timeperson.getMardi());
                    usager.setMercredi(timeperson.getMercredi());
                    usager.setJeudi(timeperson.getJeudi());
                    usager.setVendredi(timeperson.getVendredi());
                    usager.setSamedi(timeperson.getSamedi());
                    usager.setHoraire(timeperson.getHoraire());
                  usager = (emploi_de_temps) ((RemoteEmploiTemps) r).Updateemploi_de_temps(usager);
                } catch (MalformedURLException | NotBoundException | RemoteException e) {
                    System.err.println("erreur manque connection distante" + e.getMessage());
                }

            }

            okClicked = true;
            dialogStage.close();
        }
    }

     public void setToAjouter() {
        isAjouter = true;
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "nom utilisateur incorrecte !";
        if ((lundi.getText().equals(this.user.getText()) || lundi.getText().length() == 0) && (mardi.getText().equals(this.user.getText()) || mardi.getText().length() == 0) && (mercredi.getText().equals(this.user.getText()) || mercredi.getText().length() == 0) && (jeudi.getText().equals(this.user.getText()) || jeudi.getText().length() == 0) && (vendredi.getText().equals(this.user.getText()) || vendredi.getText().length() == 0) && (samedi.getText().equals(this.user.getText()) || samedi.getText().length() == 0) && (heure.getSelectionModel().getSelectedItem().toString() != null || heure.getSelectionModel().getSelectedItem().toString().length() != 0)) {
            return true;

        } else {
            // Show the error message.
           /* Alert alert = new Alert(AlertType.ERROR);
             alert.initOwner(dialogStage);
             alert.setTitle("Invalid Fields");
             alert.setHeaderText("Please correct invalid fields");
             alert.setContentText(errorMessage);

             alert.showAndWait();*/
errormesssage.setText(errorMessage);
            return false;
        }
    }
}
