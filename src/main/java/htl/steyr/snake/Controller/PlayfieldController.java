package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
    private int direction = RIGHT;
    private boolean collision = false;
    private long lasttick = 0;

    public void initialize() {
        pfView = new PlayfieldView(snakePlayfield, boardView);
    }

    Snake snake = new Snake();

    public void afterSwitch(Scene scene) {
        Platform.runLater(() -> {
            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case W:
                        direction = UP;
                        System.out.println("UP");
                        break;
                    case S:
                        direction = DOWN;
                        break;
                    case A:
                        direction = LEFT;
                        break;
                    case D:
                        direction = RIGHT;
                        break;
                }
            });
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lasttick == 0) {
                    lasttick = now;
                }

                //if (!snakePlayfield.containsApple()) {
                    snakePlayfield.drawRandomApple();
                //}

                if (snake.isCollision()) {
                    this.stop();
                } else {
                    snakePlayfield = snake.move(snakePlayfield, direction);
                }

                pfView.drawPlayfield();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();


    }
}
