/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur.utilisateur;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.beans.Utilisateur;
import main.util.Transformlist;
import main.util.UtilisateurToDisplay;
import uds.information.RemoteUtilisateur;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class CosulteController extends BorderPane implements Initializable {

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
    Main application;
    @FXML
    private TableView<UtilisateurToDisplay> UserTable;
    @FXML
    private TableColumn<UtilisateurToDisplay, String> firstNameColumn;
    @FXML
    private TableColumn<UtilisateurToDisplay, String> lastNameColumn;
    private final ObservableList<UtilisateurToDisplay> data_usr = FXCollections.observableArrayList();
    Transformlist transform = new Transformlist();

    public CosulteController() {
    }

    @FXML
    @Override
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

    public void setApp(Main application) {
        this.application = application;
        // Add observable list data to the table
        //UserTable.setItems(this.getData_usr());

    }

    public ObservableList<UtilisateurToDisplay> getData_usr() {
        data_usr.add(new UtilisateurToDisplay("cabrel", "cabrel", "cabrel", "cabrel", "Admin", "L", 67890338, Long.getLong("467767"), "cabrel@mail.com", "ACTIVE","bjn"));
        data_usr.add(new UtilisateurToDisplay("jean", "talus"));
//        data_usr.add(new UtilisateurToDisplay("jean", "tala"));
//        data_usr.add(new UtilisateurToDisplay("jean", "talus"));
//        data_usr.add(new UtilisateurToDisplay("jean", "tala"));
        return data_usr;
    }

    @FXML
    public void consultUser() {

        if (true) {
//System.setSecurityManager(new SecurityManager());
            try {
                Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/utilisateur");
                Utilisateur user = new Utilisateur();
                @SuppressWarnings("UnusedAssignment")
                LinkedList<Utilisateur> listUser = new LinkedList<>();
                LinkedList<UtilisateurToDisplay> l2 = new LinkedList<>();
                listUser = (LinkedList<Utilisateur>) ((RemoteUtilisateur) r).getAllUtilisateur();
                transform.setL1(listUser);
                l2 = transform.changelist(l2, listUser);
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
            droit.setText(person.getStatut_usr());
            id_usr.setText(Long.toString(person.getId_usr()));
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
