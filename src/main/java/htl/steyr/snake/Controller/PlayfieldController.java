package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.View.PlayfieldView;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class PlayfieldController {

    public GridPane boardView;
    public Label timeLabel;
    Playfield snakePlayfield = new Playfield();

    PlayfieldView pfView = new PlayfieldView(snakePlayfield, boardView);




}
