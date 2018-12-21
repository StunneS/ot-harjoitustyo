/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.miinaharava.Score;
import database.Database;
import database.ScoreDao;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sade-Tuuli
 */
public class ScoreDaoTest {
    ScoreDao test;
    public ScoreDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Database db = new Database("test.db");
        db.initDatabase();
        test = new ScoreDao(db, "Easy");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void addingScoreWorks() throws SQLException{
        test.addScore(new Score("TestFile", 10000)); // TEST ADDING
        boolean notEmptyList = false;
        List<Score> list = test.findAll();
        if(list.size() > 0) {
            notEmptyList = true;
        }
        for (int i = 0; i < list.size(); i++) {
            test.delete(list.get(i).getId());
        }
        assertEquals(true, notEmptyList);
    }
    @Test
    public void canFindTop10() throws SQLException{
        boolean notEmptyList = false; // TEST FINDING THE TOP 10
        test.addScore(new Score("TestFile1", 10000));
        test.addScore(new Score("TestFile2", 100));
        test.addScore(new Score("TestFile3", 20));
        List<Score> list = test.findBest();
        if(list.size() == 3) {
            notEmptyList = true;
        }
        for (int i = 0; i < list.size(); i++) {
            test.delete(list.get(i).getId());
        }
        assertEquals(true, notEmptyList);
    }
    @Test
    public void numberOfValuesInDbIsCorrect() throws SQLException {
        boolean check = false; // TEST THE NUMBER OF SCORES IS CORRECT
        test.addScore(new Score("TestFile1", 10000));
        test.addScore(new Score("TestFile2", 100));
        test.addScore(new Score("TestFile3", 20));
        int help = test.numberOfScores();
        if(help == 3) {
            check = true;
        }
        List<Score> list = test.findAll();
        for (int i = 0; i < list.size(); i++) {
            test.delete(list.get(i).getId());
        }
        assertEquals(true, check);
    }
    @Test
    public void testThatSmallerValueCanGoToList() throws SQLException {
        test.addScore(new Score("TestFile", 20));
        assertEquals(true, test.checkIfGoodEnoughScore(0)); // TEST THAT 0 COULD GO ON THE LIST       
        test.delete(1);
    }
    @Test
    public void getsCorrectId() throws SQLException {
        test.addScore(new Score("TestFile", 100));
        assertEquals(1,test.findOne(1).getId()); // TEST THAT IT GIVES RIGHT ID
        test.delete(1);
    }
    @Test
    public void removeWorstWorks() throws SQLException {
        boolean check = false; // TEST THAT REMOVAL (OF WORST) WORKS
        test.addScore(new Score("TestFile1", 10000));
        test.addScore(new Score("TestFile2", 100));
        test.addScore(new Score("TestFile3", 20));
        int help = test.numberOfScores();
        test.deleteWorst();
        int afterRemoval = test.numberOfScores();
        List<Score> noFileOne = test.findAll();
        if(help-1 == afterRemoval) {
            check = true;
        }
        for (int i = 0; i < noFileOne.size(); i++) {
            if(noFileOne.get(i).getName().equals("TestFile1")) {
                check = false;
            }
            test.delete(noFileOne.get(i).getId());
        }
        assertEquals(true, check);
    }
    @Test
    public void removeWorks() throws SQLException {
        boolean check = false; // TEST THAT REMOVAL WORKS
        test.addScore(new Score("TestFile", 20));
        int help = test.numberOfScores();
        test.delete(1);
        int afterRemoval = test.numberOfScores();
        if(help-1 == afterRemoval) {
            check = true;
        }
        assertEquals(true, check);
    }
    
}
