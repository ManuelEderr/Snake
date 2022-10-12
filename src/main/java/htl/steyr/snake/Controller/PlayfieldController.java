package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PlayfieldController {


    public GridPane boardView = new GridPane();
    public static int UP = 0;
    public static int DOWN = 1;
    public static int RIGHT = 2;
    public static int LEFT = 3;
    public static int EASY = 300000000;
    public static int MEDIUM = 100000000;
    public static int HARD = 0;
    int speed;
    public Label timeLabel;
    Playfield snakePlayfield = new Playfield();
    PlayfieldView pfView;
    private int direction = RIGHT;
    private long lasttick;
    private int barrierCount = 0;
    private boolean pause;
    private boolean end;

    public void initialize() {
        pfView = new PlayfieldView(snakePlayfield, boardView);
    }

    Snake snake = new Snake();


    public void afterSwitch(Scene scene, String difficulty, String barriers) {
        switch (difficulty) {
            case "slow":
                this.speed = EASY;
                break;
            case "normal":
                this.speed = MEDIUM;
                break;
            case "fast":
                this.speed = HARD;
                break;
        }

        barrierCount = Integer.parseInt(barriers);

        Platform.runLater(() -> {
            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case W:
                        direction = UP;
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
                    case SPACE:
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        if (pause) {
                            pause = false;
                        } else {
                            alert.setTitle("Pause");
                            alert.setContentText("Press SPACE to continue");
                            alert.show();
                            pause = true;
                        }
                        break;
                    case ESCAPE:
                        end = true;
                        break;
                }
            });
        });

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println(end);
                if (end) {
                    System.out.println("Game End");
                    this.stop();
                }

                if (!pause) {
                    if (lasttick == 0) {
                        lasttick = now;
                    }

                    System.out.println(now);
                    if (now - lasttick > speed) {
                        if (!snakePlayfield.containsApple()) {
                            snakePlayfield.drawRandomApple();
                        }

                        if (!snakePlayfield.containsBarrier()) {
                            snakePlayfield.drawRandomBarrier(barrierCount);
                        }

                        snakePlayfield = snake.move(snakePlayfield, direction);

                        if (snakePlayfield == null) {
                            this.stop();
                            System.out.println("GAME OVER");
                        }

                        pfView.drawPlayfield();

                        lasttick = now;
                    }
                }
            }
        }.start();

    }
}
