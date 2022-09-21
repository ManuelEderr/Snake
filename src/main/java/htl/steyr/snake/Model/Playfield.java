package htl.steyr.snake.Model;

public class Playfield {
    public static int MAX_X = 20;
    public static int MAX_Y = 20;

    public static int EMPTY = 0;
    public static int SNAKE = 1;
    public static int FOOD = 2;
    /*
    Zustände der Zellen eines Playfields
     */
    public int[][] field;


    public Playfield(){
        field = new int[MAX_X][MAX_Y];

        for (int j = 0; j < MAX_Y; j++) {
            for (int i = 0; i < MAX_X; i++) {
                field[j][i] = EMPTY;
            }
        }
    }

}
