/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controlleur.dossier;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.Remote;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import main.Main;
import main.beans.Fiche_patient;
import main.util.FactorielleChargeurCentre;
import main.util.Iconifier;
import uds.information.RemoteFiche_patient;



public class EnregistrerDossierDebutController implements Initializable {
    @FXML
    Label iconEnregistrement;
    @FXML
    TextField nom;
    @FXML
    TextField prenom;
    @FXML
    TextField tel;
    @FXML
    TextField cni;
    @FXML
    DatePicker naissance;
    @FXML
    TextField profetion;
    @FXML
    ComboBox<String> sexe;
    @FXML
    TextField garde;
    @FXML
    TextField telGarde;
    @FXML
    Label erreur;
    @FXML
    Label id_patient;

    Main application;
    BorderPane conteneur;
    FactorielleChargeurCentre charger=new FactorielleChargeurCentre();
    Iconifier icon=new Iconifier();

    boolean isModification=false;
    Fiche_patient fiche=new Fiche_patient();

    public void setApp(Main application) {
        this.application = application;
    }

    public void setContainer(BorderPane b){
        conteneur=b;
        sexe.getItems().addAll("Masculin","Feminin");
        sexe.setValue("Masculin");
        if (isModification) {
            id_patient.setText("Modification du patient numero "+fiche.getNum_fiche()+".");
            nom.setPromptText(fiche.getNom());
            prenom.setPromptText(fiche.getPrenom());
            tel.setPromptText(fiche.getTel()+"");
            cni.setPromptText(fiche.getCni()+"");
            naissance.setPromptText(fiche.getDate_naissance()+"");
            profetion.setPromptText(fiche.getProfession());
            sexe.setValue(fiche.getSexe()=='M'?"Masculin":"Feminin");
            garde.setPromptText(fiche.getGardeMalade());
            telGarde.setPromptText(fiche.getTelGarde()+"");
        }
    }

    @FXML
    public void goToNext() throws IOException{
        try{
            fiche.setNom(nom.getText());
            fiche.setPrenom(prenom.getText());
            fiche.setTel(Long.parseLong(tel.getText()));
            fiche.setCni(Long.parseLong(cni.getText()));
            fiche.setDate_naissance(Date.valueOf(naissance.getValue()));
            fiche.setProfession(profetion.getText());
            fiche.setSexe(sexe.getValue().charAt(0));
            fiche.setGardeMalade(garde.getText());
            fiche.setTelGarde(Long.parseLong(telGarde.getText()));


            Remote r = Naming.lookup("rmi://127.0.0.1/serveurHospital/fiche_patient");
            Fiche_patient f=new Fiche_patient();
            f=(Fiche_patient)((RemoteFiche_patient)r).getFiche_patientByCni(fiche.getCni());

            if(f.getNum_fiche()==-1||isModification){
                System.out.print(fiche.toString());
                EnregistrerDossierFinController en = (EnregistrerDossierFinController)charger.sonFactory(conteneur, "vue/dossier/EnregistrerDossierFin.fxml");
                en.setFiche(fiche);
                en.setApp(application);
                en.setContainer(conteneur);
                if(isModification){
                    en.setToModify();
                }
            }else{
                erreur.setText("Ce numero de cni existe deja.");
            }
        }catch(NullPointerException e){
            erreur.setText("!Le champs date est-il correct?");
            e.printStackTrace();
        }
        catch(NumberFormatException e){
            erreur.setText("!Rasurez-vous que tout est bien rempli.");
        }
        catch(Exception e){
            erreur.setText("!Probleme de connection au serveur.");
        }
    }

    public void setToModify(Fiche_patient fiche) {
        isModification=true;
        this.fiche=fiche;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        icon.init("image/PatientFile.png", iconEnregistrement);
    }

}
