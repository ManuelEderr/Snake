package htl.steyr.snake.Model;

import java.util.Random;

public class Playfield {
    public static int MAX_X = 20;
    public static int MAX_Y = 20;

    public static int EMPTY = 0;
    public static int SNAKE = 2;
    public static int FOOD = 1;
    /*
    Zustände der Zellen eines Playfields
     */
    public int[][] field;

    public Playfield() {
        field = new int[MAX_X][MAX_Y];

        for (int j = 0; j < MAX_Y; j++) {
            for (int i = 0; i < MAX_X; i++) {
                field[j][i] = EMPTY;
            }
        }
    }

    public void setSNAKE(int x, int y) {
        field[x][y] = SNAKE;
    }

    public void deleteSnake() {
        for (int j = 0; j < MAX_Y; j++) {
            for (int i = 0; i < MAX_X; i++) {
                if (field[j][i] == SNAKE) {
                    field[j][i] = EMPTY;
                }
            }
        }
    }

    public void drawRandomApple() {
        Random random = new Random();
        int randomX = random.nextInt(field.length);
        int randomY = random.nextInt(field[randomX].length);
        while (field[randomX][randomY] != EMPTY) {
            random = new Random();
            randomX = random.nextInt(field.length);
            randomY = random.nextInt(field[randomX].length);
        }
        field[randomX][randomY] = FOOD;
        System.out.println(randomX + " - " + randomY);
    }

    public boolean containsApple() {
        boolean r = false;

        for (int j = 0; j < MAX_Y; j++) {
            for (int i = 0; i < MAX_X; i++) {
                if (field[j][i] == FOOD) {
                    r = true;
                    break;
                }
            }
        }

        return r;
    }
}
