/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import java.text.DecimalFormat;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import static javafx.util.Duration.seconds;

/**
 *Class creates and handles the game timer
 */
public class TimeCounter extends Pane {

    private Label gameTime = new Label("00");
    private int secondsPassed = 0;
    private Timeline timer;

    public TimeCounter() {
        getChildren().add(gameTime);
        timer = new Timeline();
        timer.setCycleCount(Animation.INDEFINITE);
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            secondsPassed++;
            changeCurrentTime();
        }));

    }

    /**
     * Method starts the timer
     */
    public void start() {
        timer.play();
    }

    /**
     * Method changes the time shown
     */
    private void changeCurrentTime() {
        DecimalFormat df = new DecimalFormat("00");
        gameTime.setText(df.format(secondsPassed));
    }

    /**
     * Method resets the timers value to 0
     */
    public void reset() {
        secondsPassed = 0;
        gameTime.setText("00");
    }

    public String getGameTime() {
        return gameTime.getText();
    }

    public int getSeconds() {
        return secondsPassed;
    }

    /**
     * Method stops the timer
     */
    public void stop() {
        timer.stop();
    }
}
