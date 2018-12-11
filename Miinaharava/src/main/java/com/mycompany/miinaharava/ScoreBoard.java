/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Sade-Tuuli
 */
public class ScoreBoard {

    private ArrayList<Score> top;

    public ScoreBoard() {
        top = new ArrayList();
    }

    public ArrayList<Score> getTop() {
        return top;
    }

    public int getLowestScore() {
        if (top.size() == 0) {
            return 9999;
        }
        int returnable = 0;
        for (int i = 0; i < top.size(); i++) {
            if (returnable < top.get(i).getSeconds()) {
                returnable = top.get(i).getSeconds();
            }
        }
        return returnable;
    }

    public void addScore(Score n) {
        if (roomOnTheList() == false) {
            Score worst = top.get(0);
            for (int i = 1; i < top.size(); i++) {
                if (worst.getSeconds() < top.get(i).getSeconds()) {
                    worst = top.get(i);
                }
            }
            top.remove(worst);
        }
        top.add(n);
    }

    public boolean roomOnTheList() {
        if (top.size() < 10) {
            return true;
        }
        return false;
    }
}
