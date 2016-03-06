/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur.Ressource;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Transform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import main.beans.Utilisateur;
import main.beans.emploi_de_temps;
import main.util.TimeTableToDisplay;
import main.util.TransformListe;
import uds.information.RemoteEmploiTemps;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class UpdateTimesTableController extends BorderPane implements Initializable {

    @FXML
    TextField id_usr;
    @FXML
    TextField nom;
    @FXML
    Button ajouter;
    @FXML
    Button modifier;
    @FXML
    Button reset;
    @FXML
    Label lundi;
    @FXML
    Label mardi;
    @FXML
    Label mercredi;
    @FXML
    Label jeudi;
    @FXML
    Label vendredi;
    @FXML
    Label samedi;
    @FXML
    Label heure;
    emploi_de_temps empl;
    TransformListe transform = new TransformListe();
    @FXML
    private TableView<TimeTableToDisplay> timeTable;
    @FXML
    private TableColumn<TimeTableToDisplay, String> lundiColumn;
    @FXML
    private TableColumn<TimeTableToDisplay, String> mardiColumn;
    @FXML
    private TableColumn<TimeTableToDisplay, String> mercrediColumn;
    @FXML
    private TableColumn<TimeTableToDisplay, String> jeudiColumn;
    @FXML
    private TableColumn<TimeTableToDisplay, String> vendrediColumn;
    @FXML
    private TableColumn<TimeTableToDisplay, String> samediColumn;
    @FXML
    private TableColumn<TimeTableToDisplay, Time> horaireColumn;

    private final ObservableList<TimeTableToDisplay> data_usr = FXCollections.observableArrayList();
    private Main application;

    public UpdateTimesTableController() {
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        lundiColumn.setCellValueFactory(cellData -> cellData.getValue().lundiProperty());
        mardiColumn.setCellValueFactory(cellData -> cellData.getValue().mardiProperty());
        mercrediColumn.setCellValueFactory(cellData -> cellData.getValue().mercrediProperty());
        jeudiColumn.setCellValueFactory(cellData -> cellData.getValue().jeudiProperty());
        vendrediColumn.setCellValueFactory(cellData -> cellData.getValue().vendrediProperty());
        samediColumn.setCellValueFactory(cellData -> cellData.getValue().samediProperty());
        horaireColumn.setCellValueFactory(cellData -> cellData.getValue().horaireProperty());
        // Clear person details.
        showUserDetails(null);
        // Listen for selection changes and show the person details when changed.
        timeTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));

             //   timeTable.setItems(this.getData_usr());
    }

    public ObservableList<TimeTableToDisplay> getData_usr() {
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(7, 0)), "cabrel", "cabrel", "cabrel", "cabrel", "cabrel", "cabrel"));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(8, 0)), "cabrel", "cabrel", "cabrel", "cabrel", "cabrel", "cabrel"));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(9, 0)), "cabrel", "", "", "cabrel", "", ""));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(10, 0)), "", "", "cabrel", "cabrel", "", ""));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(11, 0)), "", "", "", "cabrel", "", ""));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(11, 0)), "", "", "", "cabrel", "", ""));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(11, 0)), "", "", "", "cabrel", "", ""));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(11, 0)), "", "", "", "cabrel", "", ""));
        data_usr.add(new TimeTableToDisplay(Time.valueOf(LocalTime.of(11, 0)), "", "", "", "cabrel", "", ""));

        return data_usr;
    }

    public void setApp(Main application) {
        this.application = application;
          }


    @FXML
    public void consultUser() {

        if (true)
//System.setSecurityManager(new SecurityManager());
            try {


Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/emploi_de_temps");
            emploi_de_temps user = new emploi_de_temps();
            LinkedList<emploi_de_temps> listUser = new LinkedList<emploi_de_temps>();
            LinkedList<TimeTableToDisplay> l2 = new LinkedList<TimeTableToDisplay>();
            listUser = (LinkedList<emploi_de_temps>) ((RemoteEmploiTemps) r).sellectAlluser(Long.valueOf("1"));
            transform.setL1(listUser);
            l2 = transform.changelist(l2, listUser);
            data_usr.addAll(l2);
            timeTable.setItems(data_usr);

            } catch (Exception ex) {
               // System.err.println(ex.getMessage());
                ex.printStackTrace();
            }

        //getData_usr();
        //UserTable.setItems(this.getData_usr());

    }


    private void showUserDetails(TimeTableToDisplay timeperson) {
        if (timeperson != null) {
            // Fill the labels with info from the person object.
            lundi.setText(timeperson.getLundi());
            mardi.setText(timeperson.getMardi());
            mercredi.setText(timeperson.getMercredi());
            jeudi.setText(timeperson.getJeudi());
            vendredi.setText(timeperson.getVendredi());
            heure.setText(String.valueOf(timeperson.getHoraire().getHours()));
            samedi.setText(timeperson.getSamedi());

        } else {
            lundi.setText("");
            mardi.setText("");
            mercredi.setText("");
            jeudi.setText("");
            samedi.setText("");
            vendredi.setText("");
            heure.setText("");
        }
    }

    public boolean showPersonEditDialog(TimeTableToDisplay person, boolean isAdd) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vue/Ressource/AjoutHeureTravail.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Time Table User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(application.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AjoutHeureTravailController controller = loader.getController();
            if (isAdd) {
                controller.setToAjouter();
            }
            controller.setDialogStage(dialogStage);
            controller.setTimePerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewPerson() {
        TimeTableToDisplay tempPerson = new TimeTableToDisplay(Time.valueOf(LocalTime.of(0, 0)), "", "", "", "", "", "");
        boolean okClicked = this.showPersonEditDialog(tempPerson,true);
        if (okClicked) {
            timeTable.getItems().clear();
            this.getData_usr().add(tempPerson);

        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * showUserDetails(selectedPerson); } details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        TimeTableToDisplay selectedPerson = timeTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = this.showPersonEditDialog(selectedPerson,false);
            if (okClicked) {
                showUserDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
        /*Alert alert = new Alert(AlertType.WARNING);
             alert.initOwner(mainApp.getPrimaryStage());
             alert.setTitle("No Selection");
             alert.setHeaderText("No Person Selected");
             alert.setContentText("Please select a person in the table.");

             alert.showAndWait();*/
        }

    }

}
