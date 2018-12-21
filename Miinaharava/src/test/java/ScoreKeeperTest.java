/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.miinaharava.ScoreKeeper;
import database.Database;
import guifx.ScoreBoard;
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
public class ScoreKeeperTest {
    ScoreKeeper test;
    
    public ScoreKeeperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Database db = new Database("Test.db");
        this.test = new ScoreKeeper(db);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /*
    @Test
    
    public void gotScoreboardNotEmpty() {
        boolean notNull = false;
        ScoreBoard help = test.getScoreBoard();
        if(help != null) {
            notNull = true;
        }
        assertEquals(true, notNull);
    }
    @Test
    public void changingScoreBoardWorks() {
        boolean notSameLevel = false;
        test.switchScoreBoard("Hard");
        if(!test.getScoreBoard().getLevel().equals("Easy")) {
            notSameLevel = true;
        }
        assertEquals(true, notSameLevel);
    }
    
    @Test
    public void putingToListWhenOnTheListLessThanTen() {
        boolean getsToList = test.getsToList(0);
        
        assertEquals(true, getsToList);
    }
*/
}
