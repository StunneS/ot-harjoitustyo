/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author Sade-Tuuli
 */
public class Database {
    public Database() {
        
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\scores.db");
    }
}
