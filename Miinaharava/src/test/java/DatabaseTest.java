/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class DatabaseTest {
    Database test;
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.test = new Database("Test.db");
        test.initDatabase();
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
    public void testConnection() throws Exception{
        Connection conn = test.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT 1");

        ResultSet resultSet = stmt.executeQuery();
        int i = 0;
        if(resultSet.next()) {
            i = 1;
        }
        resultSet.close();
        stmt.close();
        conn.close();
        assertEquals(1, i);
    }
}
