package htl.steyr.snake.Model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    ArrayList<Point2D> snake = new ArrayList<>();
    public Playfield playfield;

    public Snake(Playfield playfield) {
        this.playfield = playfield;
        snake.add(new Point2D(4, 8));
        snake.add(new Point2D(4, 7));
        snake.add(new Point2D(4, 6));
    }

    public Playfield move(int i) {
        Point2D temp1 = snake.get(0);
        Point2D temp2;
        switch (i) {
            case 0:
                //UP
                System.out.println("^");
                System.out.println("|");
                snake.set(0, new Point2D(snake.get(0).getX(), snake.get(0).getY() - 1));
                break;
            case 1:
                //DOWN
                snake.set(0, new Point2D(snake.get(0).getX(), snake.get(0).getY() + 1));
                break;
            case 2:
                //RIGHT
                System.out.println("->");
                snake.set(0, new Point2D(snake.get(0).getX() + 1, snake.get(0).getY()));
                break;
            case 3:
                //LEFT
                System.out.println("<-");
                snake.set(0, new Point2D(snake.get(0).getX() - 1, snake.get(0).getY()));
                break;
        }

        for (int j = 1; j < snake.size(); j++) {
            temp2 = snake.get(j);
            snake.set(j, temp1);
            temp1 = temp2;
        }

        playfield.setPlayfieldEMPTY();
        for (int k = 0; k < snake.size(); k++) {
            System.out.println(snake.get(k).toString());
            playfield.setSNAKE((int) snake.get(k).getX(), (int) snake.get(k).getY());
        }

        return playfield;
    }
}
