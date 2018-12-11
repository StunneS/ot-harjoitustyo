/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import com.mycompany.miinaharava.Grid;
import com.mycompany.miinaharava.Score;
import com.mycompany.miinaharava.ScoreBoard;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.text.Text;

/**
 *
 * @author Sade-Tuuli
 */
public class GUIJavaFX extends Application {

    private Grid grid;
    private Button[][] buttons;
    private ImageHandler images;
    private Label victory;
    private Integer bombCount;
    private Label unmarkedBombs;
    private TimeCounter timer;
    private Integer currentX;
    private Integer currentY;
    private Integer bombNmbr;
    private boolean first;
    private ScoreBoard scoreEasy;
    private ScoreBoard scoreMedium;
    private ScoreBoard scoreHard;

    @Override
    public void init() throws Exception {
        currentX = 16;
        currentY = 16;
        grid = new Grid(currentX, currentY);
        bombNmbr = 40;
        first = true;
        images = new ImageHandler();
        bombCount = bombNmbr;
        scoreEasy = new ScoreBoard();
        scoreMedium = new ScoreBoard();
        scoreHard = new ScoreBoard();

    }

    public void init(int x) {
        if (x == 1) {
            currentX = 8;
            currentY = 8;
            grid = new Grid(currentX, currentY);
            bombNmbr = 10;
        } else if (x == 2) {
            currentX = 16;
            currentY = 16;
            grid = new Grid(currentX, currentY);
            bombNmbr = 40;
        } else if (x == 3) {
            currentX = 24;
            currentY = 24;
            grid = new Grid(currentX, currentY);
            bombNmbr = 99;
        }
        first = true;
        bombCount = bombNmbr;
        unmarkedBombs.setText("Bombs: " + bombCount);
        timer.stop();
        timer.reset();
    }

    @Override
    public void start(Stage window) {

        window.setTitle("MineSweeper");
        window.setMinHeight(715);
        //window.setMinWidth(800);

        BorderPane frame = new BorderPane();

        Button reset = new Button("Reset"); // RESET button
        reset.setOnAction((event) -> {
            timer.stop();
            timer.reset();
            grid.resetGrid();
            victory.setText("");
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    buttons[x][y].setGraphic(new ImageView(images.setImage(9)));
                    buttons[x][y].setId("");
                }
            }
            first = true;
            bombCount = bombNmbr;
            unmarkedBombs.setText("Bombs: " + bombCount);
        });

        buttons = new Button[currentX][currentY];
        GridPane buttonField = new GridPane();
        makeButtonfield(buttonField); //Buttons visual made

        BorderPane middle = new BorderPane();

        FlowPane top = new FlowPane();
        top.setHgap(40);
        VBox right = new VBox();
        timer = new TimeCounter();
        victory = new Label("");
        unmarkedBombs = new Label("Bombs: " + bombCount);

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

        top.getChildren().add(timer);
        top.getChildren().add(reset);
        top.getChildren().add(unmarkedBombs);

        middle.setTop(top);
        middle.setCenter(buttonField);

        right.getChildren().add(easy);
        right.getChildren().add(medium);
        right.getChildren().add(hard);
        right.getChildren().add(victory);

        //frame.setTop(top);
        frame.setRight(right);
        frame.setCenter(middle);
        frame.setLeft(new Label("            "));

        Scene view = new Scene(frame);
        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(GUIJavaFX.class);
    }

    public void showAllNumbers() {
        timer.stop();
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
                    tänne metodi että middlemouse avaa ympäröivät ruudut jos tarpeeksi pommeja merkitty
                } */
                    }
                });
                buttonField.add(buttons[x][y], x, y);
            }
        }
    }

    public void setButtonActionLeft(int x, int y) {
        if (first == true) {
            grid.placeBombs(bombNmbr, x, y);
            grid.checkNeighbors();
            first = false;
            timer.start();
        }
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
        highEnoughScore();
        if (buttons[x][y].getId().equals("flagged")) {
            buttons[x][y].setGraphic(new ImageView(images.setImage(9)));
            buttons[x][y].setId("");
            bombCount++;
            unmarkedBombs.setText("Bombs: " + bombCount);
        } else if (buttons[x][y].getId().equals("open")) {
            return;
        } else {
            buttons[x][y].setGraphic(new ImageView(images.setImage(10)));
            buttons[x][y].setId("flagged");
            bombCount--;
            unmarkedBombs.setText("Bombs: " + bombCount);
        }
    }

    public void highEnoughScore() {
        final Stage dialog = new Stage();
        dialog.setTitle("Victory!");
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Congratulations! You got onto the score board!"));
        dialogVbox.getChildren().add(new Text("Your name: "));
        TextField nameField = new TextField();
        nameField.setMaxWidth(100);
        dialogVbox.getChildren().add(nameField);
        dialogVbox.getChildren().add(new Text("Your time: " + timer.getGameTime() + " seconds"));
        Button close = new Button("Submit");
        close.setOnAction((event) -> {
            Score newest = new Score(nameField.getText(), timer.getSeconds());
            if(bombNmbr == 10) {
                scoreEasy.addScore(newest);
            }
            if(bombNmbr == 40) {
                scoreMedium.addScore(newest);
            }
            if(bombNmbr == 99) {
                scoreMedium.addScore(newest);
            }
            dialog.close();
        });
        dialogVbox.getChildren().add(close);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    public void checkIfGoodEnough(){
        int lowest = 0;
        if(bombNmbr == 10) {
                lowest = scoreEasy.getLowestScore();
            } else if(bombNmbr == 40) {
                lowest = scoreMedium.getLowestScore();
            } else if(bombNmbr == 99) {
                lowest = scoreHard.getLowestScore();
            }
        if(timer.getSeconds() <= lowest) {
            highEnoughScore();
        }
    }
}
