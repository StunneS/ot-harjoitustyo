/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.miinaharava;

import database.Database;
import database.ScoreDao;
import guifx.ScoreBoard;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class handles the changes in the score board and interaction calls with the
 * database
 */
public class ScoreKeeper {

    private ScoreDao easy;
    private ScoreDao medium;
    private ScoreDao hard;
    private ScoreBoard board;
    private ScoreDao inUse;

    public ScoreKeeper(Database db) {
        this.easy = new ScoreDao(db, "Easy");
        this.medium = new ScoreDao(db, "Medium");
        this.hard = new ScoreDao(db, "Hard");
        try {
            this.board = new ScoreBoard(medium);
            this.inUse = medium;
        } catch (SQLException ex) {
            Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ScoreBoard getScoreBoard() {
        return this.board;
    }

    /**
     * Method updates the values of the scoreboard by creating a new scoreboard
     */
    public void updateScoreBoard() {

        try {
            if (board.getLevel().equals("Easy")) {
                this.board = new ScoreBoard(easy);
            } else if (board.getLevel().equals("Medium")) {
                this.board = new ScoreBoard(medium);
            } else {
                this.board = new ScoreBoard(hard);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method switches the scoreboard to the level thats name matches the name
     * of the parameter.
     *
     * @param level The level as string which scoreboard needs to be shown.
     */
    public void switchScoreBoard(String level) {
        try {
            if (level.equals("Easy")) {
                this.board = new ScoreBoard(easy);
                this.inUse = easy;

            } else if (level.equals("Medium")) {

                this.board = new ScoreBoard(medium);
                this.inUse = medium;
            } else {

                this.board = new ScoreBoard(hard);
                this.inUse = hard;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewScore(Score score) {
        try {
            this.inUse.addScore(score);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method determines if the number in the parameter is good enough to get to
     * the score board.
     *
     * @param candidate The score which is compared to the scores in the
     * database
     */
    public boolean getsToList(int candidate) {
        boolean getsToList = false;

        int comp = 999;
        try {
            comp = inUse.numberOfScores();
            if (comp < 10) {
                return true;
            }
            getsToList = inUse.checkIfGoodEnoughScore(candidate);

        } catch (SQLException ex) {
            Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getsToList;
    }
}
