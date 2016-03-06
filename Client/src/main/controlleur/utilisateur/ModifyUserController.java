/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import main.beans.Utilisateur;
import main.util.Transformlist;
import main.util.UtilisateurToDisplay;
import uds.information.RemoteUtilisateur;

public class ModifyUserController extends BorderPane implements Initializable {

    @FXML
    Label id_usr;
    @FXML
    Label nom;
    @FXML
    Label prenom;
    @FXML
    Label phone;
    @FXML
    Label addresse;
    @FXML
    Label login;
    @FXML
    Label type;
    @FXML
    Label password;
    @FXML
    Label email;
    @FXML
    Label droit;
    @FXML
    Button Modifier;
    @FXML
    Button reset;
    Button valider;
    Main application;
    @FXML
    private TableView<UtilisateurToDisplay> UserTable;
    @FXML
    private TableColumn<UtilisateurToDisplay, String> firstNameColumn;
    @FXML
    private TableColumn<UtilisateurToDisplay, String> lastNameColumn;
    private final ObservableList<UtilisateurToDisplay> data_usr = FXCollections.observableArrayList();
    Transformlist transform = new Transformlist();

    public ModifyUserController() {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
// Initialize table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showUserDetails(null);
        // Listen for selection changes and show the person details when changed.
        UserTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));
    }


    public ObservableList<UtilisateurToDisplay> getData_usr() {
        data_usr.add(new UtilisateurToDisplay("cabrel", "cabrel"));
//        data_usr.add(new UtilisateurToDisplay("lore", "cabrel"));
//        data_usr.add(new UtilisateurToDisplay("lora", "cabrelito"));
//        data_usr.add(new UtilisateurToDisplay("anita", "gaelle"));
//        data_usr.add(new UtilisateurToDisplay("jean", "talus"));
//        data_usr.add(new UtilisateurToDisplay("cabrel", "cabrel"));
//        data_usr.add(new UtilisateurToDisplay("lore", "cabrel"));
//        data_usr.add(new UtilisateurToDisplay("lora", "cabrelito"));
//        data_usr.add(new UtilisateurToDisplay("anita", "gaelle"));
//        data_usr.add(new UtilisateurToDisplay("jean", "talus"));
        return data_usr;
    }

    public void setApp(Main application) {
        this.application = application;
        UserTable.setItems(this.getData_usr());
    }

    @FXML
    public void consultUser() {

        if (true) {
//System.setSecurityManager(new SecurityManager());
            try {
                Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/utilisateur");
                Utilisateur user = new Utilisateur();
                LinkedList<Utilisateur> listUser = new LinkedList<Utilisateur>();
                LinkedList<UtilisateurToDisplay> l2 = new LinkedList<UtilisateurToDisplay>();
                listUser = (LinkedList<Utilisateur>) ((RemoteUtilisateur) r).getAllUtilisateur();
                transform.setL1(listUser);
                l2=transform.changelist(l2, listUser);
                data_usr.addAll(l2);
                UserTable.setItems(data_usr);

            } catch (Exception ex) {
               // System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        } else {

        }
        //getData_usr();
        //UserTable.setItems(this.getData_usr());

    }

      public boolean showPersonEditDialog(UtilisateurToDisplay person,boolean isAdd) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vue/utilisateur/FicheAjoutUser.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(application.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            FicheAjoutUserController controller = loader.getController();
            if(isAdd)
                controller.setToAjouter();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showUserDetails(UtilisateurToDisplay person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            nom.setText(person.getNom_usr());
            prenom.setText(person.getPrenom_usr());
            login.setText(person.getLogin_usr());
            phone.setText(Integer.toString(person.getPhone_usr()));
            type.setText(person.getType_usr());
            password.setText(person.getPass_usr());
            addresse.setText(person.getAdresse());
            email.setText(person.getMail_usr());
            id_usr.setText(Double.toString(person.getId_usr()));
           droit.setText(person.getStatut_usr());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            nom.setText("");
            prenom.setText("");
            login.setText("");
            phone.setText("");
            type.setText("");
            password.setText("");
            addresse.setText("");
            email.setText("");
            droit.setText("");
            id_usr.setText("");

        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        UtilisateurToDisplay tempPerson = new UtilisateurToDisplay("", "");

        boolean okClicked = this.showPersonEditDialog(tempPerson,true);
        if (okClicked) {
            UserTable.getItems().clear();
            this.getData_usr().add(tempPerson);

        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * showUserDetails(selectedPerson); } details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        UtilisateurToDisplay selectedPerson = UserTable.getSelectionModel().getSelectedItem();
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

     /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteUser() {
        int selectedIndex = UserTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            UserTable.getItems().remove(selectedIndex);
        } else {
        // Nothing selected.
      /*  Alert alert = new Alert(AlertType.WARNING);
             alert.initOwner(application.getPrimaryStage());
             alert.setTitle("No Selection");
             alert.setHeaderText("No Person Selected");
             alert.setContentText("Please select a person in the table.");

             alert.showAndWait();*/
        }
    }

}
