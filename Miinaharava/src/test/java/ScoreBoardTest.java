/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.miinaharava.Score;
import com.mycompany.miinaharava.ScoreBoard;
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
public class ScoreBoardTest {

    ScoreBoard test;

    public ScoreBoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        test = new ScoreBoard();
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
    public void getTheList() {
        assertEquals(0, test.getTop().size());
    }

    @Test
    public void getTheLowestScore() {
        test.addScore(new Score("Test", 10));
        assertEquals(10, test.getLowestScore());
    }

    @Test
    public void addingScoreToFullList() {
        for (int i = 0; i < 10; i++) {
            test.addScore(new Score("Test", 10));
        }
        test.addScore(new Score("Test", 10));
        assertEquals(10, test.getTop().size());
    }

    @Test
    public void spaceFalseIfFullList() {
        for (int i = 0; i < 10; i++) {
            test.addScore(new Score("Test", 10));
        }
        test.addScore(new Score("Test", 10));
        assertEquals(false, test.roomOnTheList());
    }
}
