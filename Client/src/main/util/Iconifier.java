/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.util;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;

/**
 *
 * @author tabueu
 */
public class Iconifier {
    public Iconifier() {
    }
    public void init(String urlimg, Label obj) {
    Image image = new Image(Main.class.getResource(urlimg).toExternalForm(), false);

obj.setGraphic(new ImageView(image));
    }

}
