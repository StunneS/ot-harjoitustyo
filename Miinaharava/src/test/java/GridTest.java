/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.miinaharava.Grid;
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
        test = new Grid(20,20);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void rightAmmountOfBombs() {
        test.placeBombs(30);
        assertEquals(30, test.getBombs());
    }
    
    @Test
    public void notAllEmptySpotsInGrid() {
        test.placeBombs(30);
        test.checkNeighbors();
        int[][] help = test.getPlaces();
        boolean onlyZeros = true;
        for(int x = 0; x < help.length;x++) {
            for(int y = 0; y < help[0].length;y++) {
                if(help[x][y] <10 && help[x][y] > 0){
                    onlyZeros = false;
                }
            }
        }
        assertEquals(false,onlyZeros);
    }
    @Test
    public void resetWorks() {
        test.placeBombs(30);
        test.checkNeighbors();
        int[][] first = this.test.getPlaces();
        int[][] second = new int[20][20];
        for(int x = 0; x < first.length;x++) {
            for(int y = 0; y < first[0].length;y++) {
                second[x][y] = first[x][y];   
            }
        }
        this.test.resetGrid();
        //boolean allAreSame = true;
        /*for(int x = 0; x < first.length;x++) {
            for(int y = 0; y < first[0].length;y++) {
                if(first[x][y] != second[x][y]){
                    allAreSame = false;
                }
            }
        }*/
        assertEquals(false,Arrays.deepEquals(first, second));
    }
}
