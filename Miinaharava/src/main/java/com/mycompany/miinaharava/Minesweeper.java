/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Sade-Tuuli
 */
public class Minesweeper extends Application {
    
    @Override
    public void start(Stage window) {
        window.setTitle("Minesweeper");
        BorderPane fullview = new BorderPane();
        GridPane gridview = new GridPane();

        for (int x = 1; x <= 20; x++) {
            for (int y = 1; y <= 20; y++) {
                gridview.add(new Button("  "), x, y);
            }
        }
        
        fullview.setLeft(gridview);
        fullview.setRight(new Label("  Minesweeper  "));

        Scene nakyma = new Scene(fullview);

        window.setScene(nakyma);
        
        window.show();
    }
    public static void main(String[] args) {

        launch(Minesweeper.class);
        //System.out.println("Hei maailma!");
    }

}
