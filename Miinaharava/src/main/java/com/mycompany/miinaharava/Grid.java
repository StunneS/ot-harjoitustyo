/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import java.util.ArrayList;

/**
 * Class handles the creation of the game board and everything that requires
 * handling of the numbers in the game
 */
public class Grid {

    private int[][] places;
    private int mine = 10;
    private int bombs = 0;
    private int win = 0;
    private ArrayList<Integer> zeros;

    public Grid(int x, int y) {
        places = new int[x][y];
        zeros = new ArrayList<Integer>();
    }

    /**
     * Method places number of bombs randomly to the grid, marking them with the
     * number 10, but does not place them around the grid spot marked by the
     * parametre coordinates
     *
     * @param nmbr number of bombs that are placed
     * @param a x coordinate of the grid slot where no bomb is placed
     * @param b y coordinate of of the grid slot where no bomb is placed
     *
     */
    public void placeBombs(int nmbr, int a, int b) {
        bombs = 0;
        ArrayList<Integer> bombsNotPut = new ArrayList<Integer>();
        for (int x = 0; x < places.length; x++) {
            for (int y = 0; y < places[0].length; y++) {
                bombsNotPut.add(x * 100 + y);
            }
        }
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                bombsNotPut.remove(Integer.valueOf((a + x) * 100 + (b + y)));
            }
        }
        for (int m = 1; m <= nmbr; m++) {
            int random = (int) (Math.random() * bombsNotPut.size());
            places[bombsNotPut.get(random) / 100][bombsNotPut.get(random) % 100] = mine;
            bombsNotPut.remove(random);
            bombs++;
        }
    }

    /**
     * Method fills into the grid the number of neighboring bombs to each slot
     *
     */
    public void checkNeighbors() {
        for (int x = 0; x < places.length; x++) {
            for (int y = 0; y < places[0].length; y++) {
                if (places[x][y] != mine) {
                    int neighborCount = 0;
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            if (x + a >= 0 && x + a <= places.length - 1 && y + b >= 0 && y + b <= places[0].length - 1 && (a != 0 || b != 0)) {
                                if (places[x + a][y + b] == mine) {
                                    neighborCount++;
                                }
                            }
                        }
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

    public int getNeighbors(int x, int y) {
        return places[x][y];
    }

    /**
     * Method resets all of the values in the grid to zero
     */
    public void resetGrid() {
        for (int x = 0; x < places.length; x++) {
            for (int y = 0; y < places[0].length; y++) {
                places[x][y] = 0;
            }
        }
    }

    /**
     * Method adds to a lists all the adjacent coordinates to the (x,y)
     * coordinate that are zero or are next to zero using recursion
     *
     * @see com.mycompany.miinaharava.Grid#recursion(int, int)
     *
     * @param x x coordinate of the spot that opens
     * @param y y coordinate of the spot that opens
     *
     * @return all of the spots that need to open
     */
    public ArrayList<Integer> adjacentZeros(int x, int y) {
        zeros.clear();
        zeros.add((x) * 100 + (y));
        recursion(x, y);
        return zeros;
    }

    /**
     * Method is called by method adjacentZeros(int, int) and it adds
     * coordinates to the places -list if its number value is zero or it's
     * located next to zero, Method calls itself until all required values are
     * on the list.
     *
     * @param x x coordinate of the spot thats neighbors are added to the list
     * @param y y coordinate of the spot thats neighbors are added to the list
     *
     */
    public void recursion(int x, int y) {
        if (places[x][y] != 0) {
            return;
        } else {
            for (int a = -1; a < 2; a++) {
                for (int b = -1; b < 2; b++) {
                    if (x + a >= 0 && x + a <= places.length - 1 && y + b >= 0 && y + b <= places[0].length - 1) {
                        if (!zeros.contains((x + a) * 100 + (y + b))) {
                            zeros.add((x + a) * 100 + (y + b));
                            recursion(x + a, y + b);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Integer> getZeros() {
        return zeros;
    }

}
