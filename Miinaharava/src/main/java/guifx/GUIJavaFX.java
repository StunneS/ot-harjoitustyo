/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guifx;

import com.mycompany.miinaharava.Grid;
import com.mycompany.miinaharava.Score;
import com.mycompany.miinaharava.ScoreKeeper;
import database.Database;
import database.ScoreDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Class handles the starting of the program and creating and handling of the
 * grafic interface.
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
    private Database database;
    private ScoreBoard scoreBoard;
    private ScoreKeeper scoreKeeper;

    @Override
    public void init() {
        this.database = new Database("score.db");
        this.database.initDatabase();
        this.scoreKeeper = new ScoreKeeper(database);
        this.scoreBoard = scoreKeeper.getScoreBoard();
        currentX = 16;
        currentY = 16;
        grid = new Grid(currentX, currentY);
        bombNmbr = 40;
        first = true;
        images = new ImageHandler();
        bombCount = bombNmbr;

    }

    public void init(int x) {
        if (x == 1) {
            currentX = 8;
            currentY = 8;
            grid = new Grid(currentX, currentY);
            bombNmbr = 10;

            scoreKeeper.switchScoreBoard("Easy");
            scoreBoard = scoreKeeper.getScoreBoard();
        } else if (x == 2) {
            currentX = 16;
            currentY = 16;
            grid = new Grid(currentX, currentY);
            bombNmbr = 40;

            scoreKeeper.switchScoreBoard("Medium");
            scoreBoard = scoreKeeper.getScoreBoard();

        } else if (x == 3) {
            currentX = 24;
            currentY = 24;
            grid = new Grid(currentX, currentY);
            bombNmbr = 99;

            scoreKeeper.switchScoreBoard("Hard");
            scoreBoard = scoreKeeper.getScoreBoard();

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

        BorderPane frame = new BorderPane();

        Button reset = new Button("Reset"); // RESET button
        reset.setOnAction((event) -> {
            timer.stop();
            timer.reset();
            grid.resetGrid();
            victory.setText("");
            for (int x = 0; x < buttons.length; x++) {
                for (int y = 0; y < buttons[0].length; y++) {
                    buttons[x][y].setGraphic(new ImageView(images.getImage(9)));
                    buttons[x][y].setId("");
                }
            }
            first = true;
            bombCount = bombNmbr;
            unmarkedBombs.setText("Bombs: " + bombCount);
            this.scoreKeeper.updateScoreBoard();
            this.scoreBoard = scoreKeeper.getScoreBoard();
            start(window);
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
        right.getChildren().add(scoreBoard);

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

    /**
     * Method sets the buttons image to number corresponding to the number of
     * its neighbors that have not yet been opened
     */
    public void showAllNumbers() {
        timer.stop();
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                if (grid.getNeighbors(x, y) == 10) {
                    if (buttons[x][y].getId().equals("boom")) {
                        buttons[x][y].setGraphic(new ImageView(images.getImage(12)));
                    } else {
                        buttons[x][y].setGraphic(new ImageView(images.getImage(11)));
                    }
                } else {
                    if (buttons[x][y].getId().equals("flagged")) {
                        buttons[x][y].setGraphic(new ImageView(images.getImage(13)));
                    } else {
                        buttons[x][y].setGraphic(new ImageView(images.getImage(grid.getNeighbors(x, y))));
                    }
                }
            }
        }
    }

    public void openAdjacentZeros(int x, int y) {
        ArrayList<Integer> zeros = grid.adjacentZeros(x, y);
        for (int i = 0; i < zeros.size(); i++) {
            if (!buttons[zeros.get(i) / 100][zeros.get(i) % 100].getId().equals("flagged")) {
                buttons[zeros.get(i) / 100][zeros.get(i) % 100].setGraphic(new ImageView(images.getImage(grid.getNeighbors(zeros.get(i) / 100, zeros.get(i) % 100))));
                buttons[zeros.get(i) / 100][zeros.get(i) % 100].setId("open");
            }
        }
    }

    /**
     * Method checkes if the conditions for a victory are true in current game
     */
    public void checkIfWon() {
        boolean won = true;
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                if ((buttons[x][y].getId().equals("") && grid.getNeighbors(x, y) != 10) || buttons[x][y].getId().equals("boom") || (buttons[x][y].getId().equals("flagged") && grid.getNeighbors(x, y) != 10)) {
                    won = false;
                }
            }
        }
        if (won == true) {
            showAllNumbers();
            if (!victory.getText().equals("    Victory!")) {
                checkIfGoodEnough();
            }
            victory.setText("    Victory!");
        }
    }

    /**
     * Method creates the buttons on the game grid with their own actions
     */
    public void makeButtonfield(GridPane buttonField) {
        for (int x = 0; x < buttons.length; x++) {
            for (int y = 0; y < buttons[0].length; y++) {
                buttons[x][y] = new Button();
                buttons[x][y].setGraphic(new ImageView(images.getImage(9)));
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
                    }
                });
                buttonField.add(buttons[x][y], x, y);
            }
        }
    }

    /**
     * Method determines the actions for the buttons on the game grid when
     * clicked with left mouse button.
     */
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
            buttons[x][y].setGraphic(new ImageView(images.getImage(grid.getNeighbors(x, y))));
            buttons[x][y].setId("open");

            checkIfWon();

        }

    }

    /**
     * Method determines the actions for the buttons on the game grid when
     * clicked with right mouse button.
     */
    public void setButtonActionRight(int x, int y) {
        if (buttons[x][y].getId().equals("flagged")) {
            buttons[x][y].setGraphic(new ImageView(images.getImage(9)));
            buttons[x][y].setId("");
            bombCount++;
            unmarkedBombs.setText("Bombs: " + bombCount);
        } else if (buttons[x][y].getId().equals("open")) {
            return;
        } else {
            buttons[x][y].setGraphic(new ImageView(images.getImage(10)));
            buttons[x][y].setId("flagged");
            bombCount--;
            unmarkedBombs.setText("Bombs: " + bombCount);
        }
    }

    /**
     * Method creates a popup victory screen which player can enter their name
     * and the score will be saved to the database.
     */
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
            scoreKeeper.addNewScore(newest);
            dialog.close();
        });
        dialogVbox.getChildren().add(close);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Method checkes if the players time is good enough to end up in the score
     * board.
     */
    public void checkIfGoodEnough() {
        if (scoreKeeper.getsToList(timer.getSeconds())) {
            highEnoughScore();
        }
    }
}
