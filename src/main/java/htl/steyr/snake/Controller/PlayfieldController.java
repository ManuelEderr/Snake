package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class PlayfieldController {
    public static int UP = 0;
    public static int DOWN = 1;
    public static int RIGHT = 2;
    public static int LEFT = 3;
    public GridPane boardView;
    public Label timeLabel;
    Playfield playfield = new Playfield();
    Snake snake = new Snake(playfield);
    PlayfieldView pfView = new PlayfieldView(playfield, boardView);

    public void afterSwitch() {
        //snakePlayfield.drawRandomApple();
        playfield = snake.move(UP);
        playfield = snake.move(RIGHT);
        playfield = snake.move(UP);

        //pfView.drawPlayfield();
    }

}
