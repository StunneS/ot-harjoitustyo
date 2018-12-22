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
 * Class handles the creation and connecting to a database
 */
public class Database {

    private String database;

    public Database(String db) {
        this.database = db;
    }

    /**
     * Method creates connection to the database
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + database);
    }

    /**
     * Method is creates database with the given name, if it doesn't already
     * exist
     */
    public void initDatabase() {
        File file = new File(database);
        if (file.exists()) {
            return;
        } else {
            List<String> statements = startingStatements();
            try (Connection conn = getConnection()) {
                Statement stmt = conn.createStatement();

                for (String statement : statements) {
                    stmt.executeUpdate(statement);
                }

            } catch (Throwable t) {
            }
        }
    }

    /**
     * Method adds to the list the required commands for creating the database
     * tables
     *
     * @return list of the strings to create to the tables in the database
     */
    private List<String> startingStatements() {
        ArrayList<String> starters = new ArrayList<>();

        starters.add("CREATE TABLE Easy (id integer PRIMARY KEY, name varchar(200), score integer);");
        starters.add("CREATE TABLE Medium (id integer PRIMARY KEY, name varchar(200), score integer);");
        starters.add("CREATE TABLE Hard (id integer PRIMARY KEY, name varchar(200), score integer);");

        return starters;
    }
}
