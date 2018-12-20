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
 *
 * @author Sade-Tuuli
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

    public void updateScoreBoard() {
        if (board.getLevel().equals("Easy")) {
            try {
                this.board = new ScoreBoard(easy);
            } catch (SQLException ex) {
                Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (board.getLevel().equals("Medium")) {
            try {
                this.board = new ScoreBoard(medium);
            } catch (SQLException ex) {
                Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.board = new ScoreBoard(hard);
            } catch (SQLException ex) {
                Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void switchScoreBoard(String level) {
        if (level.equals("Easy")) {
            try {
                this.board = new ScoreBoard(easy);
                this.inUse = easy;
            } catch (SQLException ex) {
                Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (level.equals("Medium")) {
            try {
                this.board = new ScoreBoard(medium);
                this.inUse = medium;
            } catch (SQLException ex) {
                Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.board = new ScoreBoard(hard);
                this.inUse = hard;
            } catch (SQLException ex) {
                Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addNewScore(Score score) {
        try {
            this.inUse.addScore(score);
            /*if (inUse.numberOfScores() >= 10) {
            inUse.deleteWorst();
            } */
        } catch (SQLException ex) {
            Logger.getLogger(ScoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public ScoreDao getEasy() {
        return easy;
    }

    public ScoreDao getMedium() {
        return medium;
    }

    public ScoreDao getHard() {
        return hard;
    }

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
