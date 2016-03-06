/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurhospital;

import main.beans.Utilisateur;
import dao.DAO;
import dao.UtilisateurDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import rmi.Serveur;

/**
 *
 * @author Adrien MOMO
 */
public class ServeurHospital extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
                Serveur serveurU = new Serveur();
                JOptionPane.showConfirmDialog(null, "Servueur en ecoute ....", STYLESHEET_MODENA, JOptionPane.DEFAULT_OPTION);

    }

}
