/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.util;

import java.io.IOException;
import java.io.InputStream;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.BorderPane;
import main.Main;

/**
 *
 * @author tabueu
 */
public class FactorielleChargeurCentre {

    public FactorielleChargeurCentre(){

    }

   public Initializable sonFactory(BorderPane principal,String name)throws IOException{

        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(name);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(name));
        BorderPane page;
        try {
            page = (BorderPane) loader.load(in);
        } finally {
            //in.close();
        }

        principal.setCenter(page);

        return (Initializable)loader.getController();
    }
}



