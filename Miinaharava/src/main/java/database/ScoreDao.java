/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mycompany.miinaharava.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handles the interactions between one database table and the program
 */
public class ScoreDao implements Dao<Score, Integer> {

    private Database database;
    private String level;

    public ScoreDao(Database database, String level) {
        this.database = database;
        this.level = level;
    }

    /**
     * Method finds a score from the levels database table where the id is the
     * parameter. If there is no such value in the database, method returns
     * null.
     *
     * @param key the id for the searched Score
     */
    @Override
    public Score findOne(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + level + " WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet resultSet = stmt.executeQuery();
        boolean hasOne = resultSet.next();
        if (!hasOne) {
            return null;
        }

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Integer seconds = resultSet.getInt("score");

        Score score = new Score(name, seconds, id);

        resultSet.close();
        stmt.close();
        connection.close();

        return score;
    }

    /**
     * Method finds all values in the level spesific database table
     *
     * @return List of all the vaues in the database table
     */
    @Override
    public List<Score> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + level);

        ResultSet resultSet = stmt.executeQuery();
        List<Score> scores = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer seconds = resultSet.getInt("score");
            scores.add(new Score(name, seconds, id));
        }

        resultSet.close();
        stmt.close();
        connection.close();

        return scores;
    }

    /**
     * Method deletes a score from the table where the id is the parameter if
     * there is such value.
     *
     * @param key the id for the Score that is to be deleted
     */
    @Override
    public void delete(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM " + level + " WHERE id = ?");
        stmt.setObject(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }

    /**
     * Method deletes the score with the highest number from the table.
     *
     */
    public void deleteWorst() throws SQLException {
        List<Score> list = findBest();
        delete(list.get(list.size() - 1).getId());
    }

    /**
     * Method adds a Score to the database table
     *
     * @param score the score that is added to the table
     */
    public void addScore(Score score) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + level + " (name, score) VALUES (?, ?)");
        stmt.setString(1, score.getName());
        stmt.setInt(2, score.getSeconds());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    /**
     * Method finds and puts to a list the ten best scores from the database
     * table. If the table has less than ten values, method puts to the list as
     * many values as there are.
     *
     * @return List of the ten best scores in the database
     */
    public List<Score> findBest() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + level + " ORDER BY score");

        ResultSet resultSet = stmt.executeQuery();
        List<Score> scores = new ArrayList<>();
        int i = 0;
        while (resultSet.next() && i < 10) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer seconds = resultSet.getInt("score");
            scores.add(new Score(name, seconds, id));
            i++;
        }

        resultSet.close();
        stmt.close();
        connection.close();

        return scores;
    }

    public String getLevel() {
        return this.level;
    }

    /**
     * Method counts the number of values in the database.
     *
     * @return the number of values in the database
     */
    public int numberOfScores() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + level);

        ResultSet resultSet = stmt.executeQuery();
        int number = 0;
        while (resultSet.next()) {
            number++;
        }

        resultSet.close();
        stmt.close();
        connection.close();

        return number;
    }

    /**
     * Method compares the value given as parameter to the values in the table.
     * If the score is good enouh to be on the list method returns true, if not
     * it returns false.
     *
     * @param score the score that is to be compared with the scores in the
     * database
     *
     * @return true if the score fits to the ten best scores, otherwise false
     */
    public boolean checkIfGoodEnoughScore(int score) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " + level + " WHERE score >= ?");
        stmt.setInt(1, score);

        ResultSet resultSet = stmt.executeQuery();
        if (!resultSet.next()) {
            resultSet.close();
            stmt.close();
            connection.close();
            return false;
        }
        resultSet.close();
        stmt.close();
        connection.close();
        return true;
    }
}
