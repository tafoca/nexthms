package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.beans.Fiche_patient;
import main.beans.Utilisateur;
import main.controlleur.AcceiulController;
import main.controlleur.LoginController;
import main.controlleur.UtilisateurActionController;
import main.controlleur.UtilisateurController;

/**
 *
 * @author tabueu
 */
public class Main extends Application {

    Stage stage;
    BorderPane root;
    ArrayList<Fiche_patient> fichesMalades=new ArrayList<Fiche_patient>();
    Utilisateur utilisateurConnecte;

    public void setUser(Utilisateur u) {
        this.utilisateurConnecte=u;
    }
    public Utilisateur getUser() {
        return this.utilisateurConnecte;
    }
    public void addMalade(Fiche_patient f){
        fichesMalades.add(f);
    }
    public ArrayList<Fiche_patient> getMalades(){
        return fichesMalades;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle(STYLESHEET_MODENA);
        gotoLogin();
      // chargeurUser();
        primaryStage.show();
    }

    public void gotoLogin() {
        try {
            LoginController login = (LoginController) chargeurLogin("vue/Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//chargeur retourne le controlleur du fichier charger

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vue/Acceiul.fxml"));
            root = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(root, 1000, 800);
            // Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
            AcceiulController controller = (AcceiulController) loader.getController();
            controller.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Shows the person overview inside the root layout.
     */
    public void showviewuser() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            InputStream in = Main.class.getResourceAsStream("vue/Utilisateur.fxml");
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("vue/Utilisateur.fxml"));
            BorderPane pageCentre = (BorderPane) loader.load(in);
            // Set person overview into the center of root layout.
            root.setCenter(pageCentre);

            // Give the controller access to the main app.
           UtilisateurController controller = (UtilisateurController) loader.getController();
          //  controller.setApp(this);

        } catch (IOException e) {
        }

    }

    public void showviewuseraction() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            InputStream in = Main.class.getResourceAsStream("vue/UtilisateurAction.fxml");
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(Main.class.getResource("vue/UtilisateurAction.fxml"));
            BorderPane pageCentre = (BorderPane) loader.load(in);
            // Set person overview into the center of root layout.
            root.setLeft(pageCentre);

            // Give the controller access to the main app.
            UtilisateurActionController controller = (UtilisateurActionController) loader.getController();
            controller.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Initializable chargeur(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        BorderPane page;
        try {
            page = (BorderPane) loader.load(in);
        } finally {
            in.close();
        }
        //charge nouvelle scene et l' associe austage
        Scene scene = new Scene(page, 900, 500);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public Initializable chargeurCentre(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        BorderPane page;
        try {
            page = (BorderPane) loader.load(in);
            root.setCenter(page);
        } finally {
            in.close();
        }
        //charge nouvelle scene et l' associe austage
        //Scene scene = new Scene(page, 800, 600);
        //stage.setScene(scene);
        //stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public void chargeurUser() {
        this.initRootLayout();
        this.showviewuser();
        this.showviewuseraction();

    }

    private Initializable chargeurLogin(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        BorderPane page;
        try {
            page = (BorderPane) loader.load(in);
        } finally {
            in.close();
        }
        //charge nouvelle scene et l' associe austage
        Scene scene = new Scene(page, 650, 310);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
