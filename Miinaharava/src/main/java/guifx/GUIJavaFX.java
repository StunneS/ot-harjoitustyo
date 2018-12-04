/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import com.mycompany.miinaharava.Grid;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Sade-Tuuli
 */
public class GUIJavaFX extends Application {

    private Grid grid;
    private Button[][] buttons;
    private ImageHandler images;
    private Label victory;
    private Integer currentX;
    private Integer currentY;

    @Override
    public void init() throws Exception {
        currentX = 16;
        currentY = 16;
        grid = new Grid(currentX, currentY);
        grid.placeBombs(40);
        grid.checkNeighbors();
        images = new ImageHandler();
    }

    public void init(int x) {
        if (x == 1) {
            currentX = 8;
            currentY = 8;
            grid = new Grid(currentX, currentY);
            grid.placeBombs(10);
        } else if (x == 2) {
            currentX = 16;
            currentY = 16;
            grid = new Grid(currentX, currentY);
            grid.placeBombs(40);
        } else if (x == 3) {
            currentX = 24;
            currentY = 24;
            grid = new Grid(currentX, currentY);
            grid.placeBombs(99);
        }
        grid.checkNeighbors();
    }

    @Override
    public void start(Stage window) {

        window.setTitle("MineSweeper");
        window.setMinHeight(740);
        window.setMinWidth(800);

        BorderPane frame = new BorderPane();

        Button reset = new Button("Reset"); // RESET button
        reset.setOnAction((event) -> {
            grid.resetGrid();
            victory.setText("");
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    //buttons[x][y].setText("");
                    buttons[x][y].setGraphic(new ImageView(images.setImage(9)));
                    buttons[x][y].setId("");
                }
            }
        });

        buttons = new Button[currentX][currentY];
        GridPane buttonField = new GridPane();
        makeButtonfield(buttonField); //Buttons visual made

        FlowPane top = new FlowPane();
        VBox right = new VBox();
        victory = new Label("");

        Button easy = new Button("Easy"); //CREATE new "Easy" board
        easy.setMinSize(100, 30);
        easy.setOnAction((event) -> {
            init(1);
            start(window);
        });

        Button medium = new Button("Medium"); //CREATE new "Medium" board
        medium.setMinSize(100, 30);
        medium.setOnAction((event) -> {
            init(2);
            start(window);
        });

        Button hard = new Button("Hard"); //CREATE new "Hard" board
        hard.setMinSize(100, 30);
        hard.setOnAction((event) -> {
            init(3);
            start(window);
        });

        top.getChildren().add(reset);
        top.getChildren().add(victory);
        right.getChildren().add(easy);
        right.getChildren().add(medium);
        right.getChildren().add(hard);

        frame.setTop(top);
        frame.setRight(right);
        frame.setCenter(buttonField);
        frame.setLeft(new Label("            "));

        Scene view = new Scene(frame);
        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(GUIJavaFX.class);
    }

    public void showAllNumbers() {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                if (grid.getNeighbors(x, y) == 10) {
                    if (buttons[x][y].getId().equals("boom")) {
                        buttons[x][y].setGraphic(new ImageView(images.setImage(12)));
                    } else {
                        buttons[x][y].setGraphic(new ImageView(images.setImage(11)));
                    }
                } else {
                    if (buttons[x][y].getId().equals("flagged")) {
                        buttons[x][y].setGraphic(new ImageView(images.setImage(13)));
                    } else {
                        buttons[x][y].setGraphic(new ImageView(images.setImage(grid.getNeighbors(x, y))));
                    }
                }
            }
        }
    }

    public void openAdjacentZeros(int x, int y) {
        ArrayList<Integer> zeros = grid.adjacentZeros(x, y);
        for (int i = 0; i < zeros.size(); i++) {
            if (!buttons[zeros.get(i) / 100][zeros.get(i) % 100].getId().equals("flagged")) {
                buttons[zeros.get(i) / 100][zeros.get(i) % 100].setGraphic(new ImageView(images.setImage(grid.getNeighbors(zeros.get(i) / 100, zeros.get(i) % 100))));
                buttons[zeros.get(i) / 100][zeros.get(i) % 100].setId("open");
            }
        }
    }

    public void checkIfWon() {
        boolean won = true;
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                if ((buttons[x][y].getId().equals("") && grid.getNeighbors(x, y) != 10) || buttons[x][y].getId().equals("boom")) {
                    won = false;
                }
            }
        }
        if (won == true) {
            showAllNumbers();
            victory.setText("    Victory!");
        }
    }

    public void makeButtonfield(GridPane buttonField) {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                buttons[x][y] = new Button();
                buttons[x][y].setGraphic(new ImageView(images.setImage(9)));
                buttons[x][y].setId("");

                buttons[x][y].setMinSize(24, 24);
                buttons[x][y].setMaxSize(24, 24);

                final int xx = x;
                final int yy = y;

                buttons[x][y].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if (button == MouseButton.PRIMARY) {
                            setButtonActionLeft(xx, yy);
                        } else if (button == MouseButton.SECONDARY) {
                            setButtonActionRight(xx, yy);
                        }
                        /*else if(button==MouseButton.MIDDLE){
                    t�nne metodi ett� middlemouse avaa ymp�r�iv�t ruudut jos tarpeeksi pommeja merkitty
                } */
                    }
                });
                buttonField.add(buttons[x][y], x, y);
            }
        }
    }

    public void setButtonActionLeft(int x, int y) {
        if (buttons[x][y].getId().equals("flagged")) {
            return;
        } else if (grid.getNeighbors(x, y) == 10) {
            buttons[x][y].setId("boom");
            showAllNumbers();
        } else if (grid.getNeighbors(x, y) == 0) {
            openAdjacentZeros(x, y);
            checkIfWon();
        } else {
            buttons[x][y].setGraphic(new ImageView(images.setImage(grid.getNeighbors(x, y))));
            buttons[x][y].setId("open");
            checkIfWon();
        }
    }

    public void setButtonActionRight(int x, int y) {
        if (buttons[x][y].getId().equals("flagged")) {
            buttons[x][y].setGraphic(new ImageView(images.setImage(9)));
            buttons[x][y].setId("");
        } else if (buttons[x][y].getId().equals("open")) {
            return;
        } else {
            buttons[x][y].setGraphic(new ImageView(images.setImage(10)));
            buttons[x][y].setId("flagged");
        }
    }
}
