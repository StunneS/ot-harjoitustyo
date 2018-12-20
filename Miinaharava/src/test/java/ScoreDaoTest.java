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
        test = new ScoreDao(new Database(), "Easy");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void allTheFunctionalitiesWork() throws SQLException{
        test.addScore(new Score("TestFile", 10000)); // TEST ADDING
        boolean notEmptyList = false;
        List<Score> list = test.findAll();
        if(list.size() > 0) {
            notEmptyList = true;
        }
        assertEquals(true, notEmptyList);
        
        notEmptyList = false; // TEST FINDING THE TOP 10
        list = test.findBest();
        if(list.size() > 0) {
            notEmptyList = true;
        }
        assertEquals(true, notEmptyList);
        
        boolean check = false; // TEST THE NUMBER OF SCORES IS CORRECT
        int help = test.numberOfScores();
        System.out.println(help);
        if(help > 0) {
            check = true;
        }
        assertEquals(true, check);
        
        assertEquals(true, test.checkIfGoodEnoughScore(0)); // TEST THAT 0 COULD GO ON THE LIST
        
        assertEquals(1,test.findOne(1).getId()); // TEST THAT IT GIVES RIGHT ID
        
        check = false; // TEST THAT REMOVAL (OF WORST) WORKS
        help = test.numberOfScores();
        test.deleteWorst();
        int afterRemoval = test.numberOfScores();
        if(help > afterRemoval) {
            check = true;
        }
        assertEquals(true, check);
    }
    
    @Test
    public void testThatShownLevelIsCorrect() {
        assertEquals("Easy", test.getLevel());
    }
    
}
