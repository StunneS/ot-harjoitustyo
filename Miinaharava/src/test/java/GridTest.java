/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.miinaharava.Grid;
import java.util.ArrayList;
import java.util.Arrays;
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
public class GridTest {

    Grid test;

    public GridTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        test = new Grid(20, 20);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rightAmmountOfBombs() {
        test.placeBombs(30, 1, 1);
        assertEquals(30, test.getBombs());
    }

    @Test
    public void notAllEmptySpotsInGrid() {
        test.placeBombs(30, 1, 1);
        test.checkNeighbors();
        int[][] help = test.getPlaces();
        boolean onlyZeros = true;
        for (int x = 0; x < help.length; x++) {
            for (int y = 0; y < help[0].length; y++) {
                if (help[x][y] < 10 && help[x][y] > 0) {
                    onlyZeros = false;
                }
            }
        }
        assertEquals(false, onlyZeros);
    }

    @Test
    public void resetWorks() {
        test.placeBombs(30, 1, 1);
        test.checkNeighbors();
        int[][] first = this.test.getPlaces();
        int[][] second = new int[20][20];
        for (int x = 0; x < first.length; x++) {
            for (int y = 0; y < first[0].length; y++) {
                second[x][y] = first[x][y];
            }
        }
        this.test.resetGrid();
        assertEquals(false, Arrays.deepEquals(first, second));
    }

    @Test
    public void neighborCountIsRight() {
        Grid test1 = new Grid(4, 8);
        test1.placeBombs(28, 0, 0);
        test1.checkNeighbors();
        int[][] help = test1.getPlaces();
        boolean fourWithRightNeighbors = false;
        int nonbombs = 0;
        for (int x = 0; x < help.length; x++) {
            for (int y = 0; y < help[0].length; y++) {
                if (help[x][y] < 10) {
                    nonbombs++;
                }
            }
        }
        if (nonbombs == 4) {
            fourWithRightNeighbors = true;
        }
        assertEquals(true, fourWithRightNeighbors);
    }

    @Test
    public void adjacentZerosAreCorrect() {
        test.checkNeighbors();
        assertEquals(400, test.adjacentZeros(0, 0).size());
    }

    @Test
    public void recursionWorks() {
        Grid test1 = new Grid(4, 8);
        test1.placeBombs(23, 2, 2);
        test1.checkNeighbors();
        test1.recursion(2, 2);
        assertEquals(9, test1.getZeros().size());
    }

    @Test
    public void zerosNumberIsCorrect() {
        test.checkNeighbors();
        assertEquals(0, test.getZeros().size());
    }

    @Test
    public void testNeighborCount() {
        test.placeBombs(30, 0, 0);
        assertEquals(0, test.getNeighbors(0, 0));
    }
}
