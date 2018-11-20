/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import java.util.ArrayList;

/**
 *
 * @author Sade-Tuuli
 */
public class Grid {
    int[][] places;
    int MINE = 10;
    
    public Grid(int x, int y) {
        places = new int[x][y]; 
        placeBombs(30);
    }
    
    public void placeBombs(int nmbr) {
        ArrayList<Integer> bombsNotPut = new ArrayList<Integer>();
        for(int x = 0; x < places.length; x++) {
            for(int y = 0; y < places[0].length;y++) {
                bombsNotPut.add(x*100+y);
            }
        }
        for(int m = 0; m < nmbr; m++) {
            int random = (int) Math.random() * bombsNotPut.size();
            places[bombsNotPut.get(random)/100][bombsNotPut.get(random)%100] = MINE; 
            bombsNotPut.remove(random);
        }
    }
    public void checkNeighbors(){
        for(int x = 0; x < places.length; x++) {
            for(int y = 0;  y < places[0].length; y++) {
                if(places[x][y] != MINE) {
                    int neighborCount = 0;
                    if(x > 0 && y > 0 && places[x-1][y-1] == MINE) { //up left
                        neighborCount++;
                    }
                    if(y > 0 && places[x][y-1] == MINE) { // up
                        neighborCount++;
                    }
                    if(y > 0 && x <places.length-1 && places[x+1][y-1] == MINE) { // up right
                        neighborCount++;
                    }
                    if(y > 0 && places[x][y+1] == MINE) { // down
                        neighborCount++;
                    }
                    if(x > 0 && places[x-1][y] == MINE) { // left
                        neighborCount++;
                    }
                    if(x < places.length-1 && places[x+1][y] == MINE) { // right
                        neighborCount++;
                    }
                    if(y < places[0].length-1 && x >0 && places[x-1][y+1] == MINE) { // down left
                        neighborCount++;
                    }
                    if(y < places[0].length-1 && x < places.length && places[x+1][y+1] == MINE) { // down right
                        neighborCount++;
                    }
                    places[x][y] = neighborCount;
                }
            }
        }
    }
    
}
