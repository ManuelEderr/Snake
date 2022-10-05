package htl.steyr.snake.Model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private static final int MAX_X = 20;
    private static final int MAX_Y = 20;
    ArrayList<Point2D> snake = new ArrayList<>();

    public Snake() {
        snake.add(new Point2D(6, 10));
        snake.add(new Point2D(5, 10));
        snake.add(new Point2D(4, 10));
    }

    public Playfield move(Playfield playfield, int direction) {
        Point2D temp1 = snake.get(0);
        Point2D temp2;
        boolean apple = false;

        switch (direction) {
            case 0:
                //UP
                snake.set(0, new Point2D(snake.get(0).getX(), snake.get(0).getY() - 1));
                break;
            case 1:
                //DOWN
                snake.set(0, new Point2D(snake.get(0).getX(), snake.get(0).getY() + 1));
                break;
            case 2:
                //RIGHT
                snake.set(0, new Point2D(snake.get(0).getX() + 1, snake.get(0).getY()));
                break;
            case 3:
                //LEFT
                snake.set(0, new Point2D(snake.get(0).getX() - 1, snake.get(0).getY()));
                break;
        }

        if (playfield.field[(int) snake.get(0).getX()][(int) snake.get(0).getY()] == 1) {
            apple = true;
        }

        for (int j = 1; j < snake.size(); j++) {
            temp2 = snake.get(j);
            snake.set(j, temp1);
            temp1 = temp2;
        }

        if (apple) {
            snake.add(temp1);
        }

        playfield.setPlayfieldEMPTY();
        for (int k = 0; k < snake.size(); k++) {
            playfield.setSNAKE((int) snake.get(k).getX(), (int) snake.get(k).getY());
        }

        return playfield;
    }

    public boolean isCollision() {
        boolean r = false;

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).getX() >= MAX_X) {
                r = true;
                break;
            }
            if (snake.get(i).getY() >= MAX_Y) {
                r = true;
                break;
            }

            if (snake.get(0) == snake.get(i)) {
                r = true;
                break;
            }
        }

        return r;
    }
}
