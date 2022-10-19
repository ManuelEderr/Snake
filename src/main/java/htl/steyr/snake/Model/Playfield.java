package htl.steyr.snake.Model;

import java.util.Random;

public class Playfield {
    public static int MAX_X = 14;
    public static int MAX_Y = 14;

    public static int EMPTY = 0;
    public static int SNAKE = 2;
    public static int FOOD = 1;

    public static int BARRIER = 3;
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

    /**
     * Setzt ein bestimmtes Feld als SNAKE (2).
     * @param x X-Koordinate im Playfield
     * @param y Y-Koordinate im Playfield
     */
    public void setSNAKE(int x, int y) {
        field[x][y] = SNAKE;
    }

    /**
     * Löscht die SNAKE (2) aus dem Spielfeld.
     */
    public void deleteSnake() {
        for (int j = 0; j < MAX_Y; j++) {
            for (int i = 0; i < MAX_X; i++) {
                if (field[j][i] == SNAKE) {
                    field[j][i] = EMPTY;
                }
            }
        }
    }

    /**
     * Zeichnet ein FOOD (1) auf einem zufälligen Feld in Spielfeld.
     */
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
    }

    /**
     * Überprüft das in der Klasse gespeicherte Spielfeld ob ein FOOD (1) enthalten ist.
     * @return true falls ein FOOD (1) enthalten ist
     */
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

    /**
     * Zeichnet eine beliebige Anzahl an BARRIER (3)
     * @param amount Anzahl an BARRIER (3)
     */
    public void drawRandomBarrier(int amount) {
        for (int i = 0; i < amount; i++) {
            Random random = new Random();
            int randomX = random.nextInt(field.length);
            int randomY = random.nextInt(field[randomX].length);
            while (field[randomX][randomY] != EMPTY) {
                random = new Random();
                randomX = random.nextInt(field.length);
                randomY = random.nextInt(field[randomX].length);
            }
            field[randomX][randomY] = BARRIER;
        }
    }

    /**
     * überprüft ob das Spielfeld BARRIER (3) enthält
     * @return true falls BARRIER (3) enthalten sind, ansonsten false
     */
    public boolean containsBarrier() {
        boolean r = false;

        for (int j = 0; j < MAX_Y; j++) {
            for (int i = 0; i < MAX_X; i++) {
                if (field[j][i] == BARRIER) {
                    r = true;
                    break;
                }
            }
        }

        return r;
    }

}
