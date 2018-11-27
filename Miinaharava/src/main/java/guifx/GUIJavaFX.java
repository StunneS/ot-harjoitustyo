/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import com.mycompany.miinaharava.Grid;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Sade-Tuuli
 */
public class GUIJavaFX extends Application {

    private Grid grid;
    private Button[][] buttons;

    @Override
    public void init() throws Exception {
        grid = new Grid(20, 20);
        grid.placeBombs(30);
        grid.checkNeighbors();
        System.out.println("Hello!");
    }

    @Override
    public void start(Stage window) {

        window.setTitle("MineSweeper");

        BorderPane frame = new BorderPane();

        Button reset = new Button("Reset");
        reset.setOnAction((event) -> {
            grid.resetGrid();
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    buttons[x][y].setText("   ");
                }
            }
            System.out.println("Painettu reset!");
        });

        buttons = new Button[20][20];
        GridPane buttonField = new GridPane();
        //Buttons

        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                buttons[x][y] = new Button();
                buttons[x][y].setText("   ");
                final int xx = x;
                final int yy = y;
                buttons[x][y].setOnAction((event) -> {
                    setButtonAction(xx, yy);
                });
                buttonField.add(buttons[x][y], x, y);
            }
        }
        FlowPane top = new FlowPane();
        Label victory = new Label("");
        top.getChildren().add(reset);
        top.getChildren().add(victory);

        frame.setTop(top);
        frame.setCenter(buttonField);

        Scene view = new Scene(frame);
        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(GUIJavaFX.class);
    }

    public void setButtonAction(int x, int y) {
        if (grid.getNeighbors(x, y) == 10) {
            showAllNumbers();
        } else if (grid.getNeighbors(x, y) == 0) {
            openAdjacentZeros(x, y);
            //checkIfWon();
        } else {
            buttons[x][y].setText("" + grid.getNeighbors(x, y) + "");
            //checkIfWon();
        }
    }

    public void showAllNumbers() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                if (grid.getNeighbors(x, y) == 10) {
                    buttons[x][y].setText("X");
                } else {
                    buttons[x][y].setText("" + grid.getNeighbors(x, y));
                }
            }
        }
    }

    public void openAdjacentZeros(int x, int y) {
        ArrayList<Integer> zeros = grid.adjacentZeros(x, y);
        for (int i = 0; i < zeros.size(); i++) {
            buttons[zeros.get(i) / 100][zeros.get(i) % 100].setText("" + grid.getNeighbors(zeros.get(i) / 100, zeros.get(i) % 100));
        }
    }

    public void checkIfWon() {
        boolean won = true;
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                if (buttons[x][y].getText().equals("   ") && grid.getNeighbors(x, y) != 10) {
                    won = false;
                }
            }
        }
        if (won == true) {
            showAllNumbers();
        }
    }
}
