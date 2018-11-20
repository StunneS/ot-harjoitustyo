/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

/**
 *
 * @author Sade-Tuuli
 */
public class Minesweeper {

    public static void main(String[] args) {
        Grid grid = new Grid(20,20);
        grid.placeBombs(30);
        grid.checkNeighbors();
        System.out.println("Hello!");
    } 
}
