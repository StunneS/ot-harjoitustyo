/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import com.mycompany.miinaharava.Score;
import database.ScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Sade-Tuuli
 */
public class ScoreBoard extends VBox {

    private List<Score> top;
    private String level;

    public ScoreBoard(ScoreDao dao) throws SQLException {
        top = new ArrayList();
        this.level = dao.getLevel();
        this.top = dao.findBest();
        getChildren().add(new Label("High Scores for " + level + " difficulty:       "));
        for (int i = 0; i < top.size(); i++) {
            getChildren().add(new Label(i + 1 + ". " + top.get(i).getName() + ", " + top.get(i).getSeconds() + " seconds"));
        }
    }

    public String getLevel() {
        return level;
    }
}
