package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.scene.control.Label;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javafx.scene.layout.GridPane;

import java.util.Objects;


public class PlayfieldController implements KeyListener {

    public GridPane boardView = new GridPane();

    public static int UP = 0;
    public static int DOWN = 1;
    public static int RIGHT = 2;
    public static int LEFT = 3;
    public Label timeLabel;
    Playfield snakePlayfield = new Playfield();
    PlayfieldView pfView;
    private int direction;

    public void initialize() {
        pfView = new PlayfieldView(snakePlayfield, boardView);
    }

    Snake snake = new Snake();

    public void afterSwitch() {
        System.out.println("afterSwitch");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
                if (!snakePlayfield.containsApple()) {
                    snakePlayfield.drawRandomApple();
                }

                snakePlayfield = snake.move(snakePlayfield, direction);

                pfView.drawPlayfield();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (Objects.equals(e.getKeyChar(), KeyEvent.VK_UP)) {
            direction = UP;
        } else if (Objects.equals(e.getKeyChar(), KeyEvent.VK_DOWN)) {
            direction = DOWN;
        } else if (Objects.equals(e.getKeyChar(), KeyEvent.VK_RIGHT)) {
            direction = RIGHT;
        } else if (Objects.equals(e.getKeyChar(), KeyEvent.VK_LEFT)) {
            direction = LEFT;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
