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
    int bombs = 0;
    ArrayList<Integer> zeros;
    
    public Grid(int x, int y) {
        places = new int[x][y]; 
        zeros = new ArrayList<Integer>();
    }
    
    public void placeBombs(int nmbr) {
        bombs = 0;
        ArrayList<Integer> bombsNotPut = new ArrayList<Integer>();
        for(int x = 0; x < places.length; x++) {
            for(int y = 0; y < places[0].length;y++) {
                bombsNotPut.add(x*100+y);
            }
        }
        for(int m = 1; m <= nmbr; m++) {
            int random = (int) (Math.random() * bombsNotPut.size());
            places[bombsNotPut.get(random)/100][bombsNotPut.get(random)%100] = MINE; 
            bombsNotPut.remove(random);
            bombs++;
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
                    if(y < places.length-1 && places[x][y+1] == MINE) { // down
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
                    if(y < places[0].length-1 && x < places.length-1 && places[x+1][y+1] == MINE) { // down right
                        neighborCount++;
                    }
                    places[x][y] = neighborCount;
                }
            }
        }
    }
    public int getBombs() {
        return bombs;
    } 
    public int[][] getPlaces() {
        return places;
    }
    public int getNeighbors(int x,int y) {
        return places[x][y];
    }
    
    public void resetGrid() {
        for (int x = 0; x < places.length; x++) {
            for (int y = 0; y < places[0].length; y++) {
                places[x][y] = 0;
            }   
        }
        placeBombs(bombs);
        checkNeighbors();
    }
    
    public ArrayList<Integer> adjacentWhites (int x,int y) {
        zeros.clear();
        zeros.add((x)*100+(y));
        recursion(x,y);
        //adjacentNumbers();
        return zeros;
    }
    public void recursion(int x, int y) {
        if(x < 0 || y < 0 || x > places.length-1 || y >places[0].length || places[x][y] == 10) {
            return;
        }
        if(!zeros.contains((x)*100+(y))) {
            zeros.add(x*100+y);
        }
        if(x > 0 && y > 0 && places[x-1][y-1] == 0) { //up left
            if(!zeros.contains((x-1)*100+(y-1))) {
                recursion(x-1,y-1);
            }
        }
        if(y > 0 && places[x][y-1] == 0) { // up
            if(!zeros.contains((x)*100+(y-1))) {
                recursion(x,y-1);
            }
        }
        if(y > 0 && x < places.length-1 && places[x+1][y-1] == 0) { // up right
            if(!zeros.contains((x-1)*100+(y-1))) {
                recursion(x-1,y-1);
            }
        }
        if(y < places.length-1 && places[x][y+1] == 0) { // down
            if(!zeros.contains((x)*100+(y+1))) {
                recursion(x,y+1);
            }
        }
        if(x > 0 && places[x-1][y] == 0) { // left
            if(!zeros.contains((x-1)*100+(y))) {
                recursion(x-1,y);
            }
        }
        if(x < places.length-1 && places[x+1][y] == 0) { // right
            if(!zeros.contains((x+1)*100+(y))) {
                recursion(x+1,y);
            }
        }
        if(y < places[0].length-1 && x >0 && places[x-1][y+1] == 0) { // down left
            if(!zeros.contains((x-1)*100+(y+1))) {
                recursion(x-1,y+1);
            }
        }
        if(y < places[0].length-1 && x < places.length-1 && places[x+1][y+1] == 0) { // down right
            if(!zeros.contains((x+1)*100+(y+1))) {
                recursion(x+1,y+1);
            }
        }
    }
    public void adjacentNumbers() {
        for (int i = 0; i < zeros.size(); i++) {
            int x = zeros.get(i)/100;
            int y = zeros.get(i)%100;
            if(places[x][y] == 0) {
                
                if(x > 0 && y > 0) { //up left
                    zeros.add((x-1)*100+(y-1));
                }
                if(y > 0 && places[x][y-1] == 0) { // up
                    zeros.add((x)*100+(y-1));
                }
                if(y > 0 && x < places.length-1) { // up right
                    zeros.add((x-1)*100+(y-1));
                }
                if(y < places.length-1) { // down
                    zeros.add((x)*100+(y+1));
                }
                if(x > 0) { // left
                    zeros.add((x-1)*100+(y));
                }
                if(x < places.length-1) { // right
                    zeros.add((x+1)*100+(y));
                }
                if(y < places[0].length-1 && x >0) { // down left
                    zeros.add((x-1)*100+(y+1));
                }
                if(y < places[0].length-1 && x < places.length-1) { // down right
                    zeros.contains((x+1)*100+(y+1));
                }
            }
        }
    }
}
