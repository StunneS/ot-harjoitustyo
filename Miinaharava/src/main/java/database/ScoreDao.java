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
 *
 * @author Sade-Tuuli
 */
public class ScoreDao implements Dao<Score, Integer> {

    private Database database;
    private String level;

    public ScoreDao(Database database, String level) {
        this.database = database;
        this.level = level;
    }

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

    @Override
    public void delete(Integer key) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM " + level + " WHERE id = ?");
        stmt.setObject(1, key);

        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }

    public void deleteWorst() throws SQLException {
        List<Score> list = findBest();
        delete(list.get(list.size() - 1).getId());
    }

    public void addScore(Score score) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO " + level + " (name, score) VALUES (?, ?)");
        stmt.setString(1, score.getName());
        stmt.setInt(2, score.getSeconds());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

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
