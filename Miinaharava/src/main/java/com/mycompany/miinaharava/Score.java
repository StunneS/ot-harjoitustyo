/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import java.util.Comparator;

/**
 *
 * @author Sade-Tuuli
 */
public class Score {
    private String name;
    private int seconds;
    
    public Score(String name, int seconds) {
        if(name.equals("")) {
        this.name = "Anonym";
        } else {
            this.name = name;
        }
        this.seconds = seconds;
    }
    public int getSeconds() {
        return seconds;
    }
    public String getName() {
        return name;
    }
}
