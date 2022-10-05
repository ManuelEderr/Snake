package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class PlayfieldController {


    public GridPane boardView = new GridPane();

    public static int UP = 0;
    public static int DOWN = 1;
    public static int RIGHT = 2;
    public static int LEFT = 3;
    public Label timeLabel;
    Playfield snakePlayfield = new Playfield();
    PlayfieldView pfView;

    public void initialize() {
        pfView = new PlayfieldView(snakePlayfield, boardView);
    }

    Snake snake = new Snake();

    public void afterSwitch() {
        snakePlayfield.drawRandomApple();
        snakePlayfield = snake.move(snakePlayfield, UP);
        snakePlayfield = snake.move(snakePlayfield, RIGHT);
        snakePlayfield = snake.move(snakePlayfield, UP);

        snakePlayfield.drawRandomApple();
        pfView.drawPlayfield();
    }

}
