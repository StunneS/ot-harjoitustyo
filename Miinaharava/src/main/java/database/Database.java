/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sade-Tuuli
 */
public class Database {

    private String database;

    public Database(String db) {
        this.database = db;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + database);
    } //src\\main\\resources\\" + 

    public void initDatabase() {
        File file = new File(database);
        if (file.exists()) {
            System.out.println("Error: Database already exists");
        } else {
            List<String> statements = startingStatements();
            try (Connection conn = getConnection()) {
                Statement stmt = conn.createStatement();

                for (String statement : statements) {
                    System.out.println("Running command: " + statement);
                    stmt.executeUpdate(statement);
                }

            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        }
    }

    public void removeDatabase() {
        File file = new File(database);
        if (!file.exists()) {
            System.out.println("Error: Database doesn't exist");
        } else {
            file.delete();
        }
    } 
    private List<String> startingStatements() {
        ArrayList<String> starters = new ArrayList<>();

        starters.add("CREATE TABLE Easy (id integer PRIMARY KEY, name varchar(200), score integer);");
        starters.add("CREATE TABLE Medium (id integer PRIMARY KEY, name varchar(200), score integer);");
        starters.add("CREATE TABLE Hard (id integer PRIMARY KEY, name varchar(200), score integer);");

        return starters;
    }
}
