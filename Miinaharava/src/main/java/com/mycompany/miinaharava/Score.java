/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import java.util.Comparator;

/**
 *Class is for handling the top scores of the game. If no name is entered,
 * the name is Anonym as default.
 */
public class Score {

    private String name;
    private int seconds;
    private int id;

    public Score(String name, int seconds) {
        if (name.equals("")) {
            this.name = "Anonym";
        } else {
            this.name = name;
        }
        this.seconds = seconds;
    }

    public Score(String name, int seconds, int id) {
        if (name.equals("")) {
            this.name = "Anonym";
        } else {
            this.name = name;
        }
        this.seconds = seconds;
        this.id = id;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
