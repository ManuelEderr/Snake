package htl.steyr.snake.View;

import htl.steyr.snake.Model.Playfield;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PlayfieldView {

    Playfield field;
    GridPane gridPane;


    public PlayfieldView(Playfield field, GridPane gridPane) {
        this.field = field;
        this.gridPane = gridPane;
    }

    void drawPlayfield() {
        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field.length; j++) {
                switch (field.field[i][j]) {
                    case 0:
                        break;
                    case 1:
                        ImageView imageView1 = new ImageView("apple.png");
                        imageView1.setFitHeight(33.8);
                        imageView1.setFitWidth(33.8);
                        gridPane.add(imageView1, i, j);
                        break;
                    case 2:
                        // snake
                }
            }
        }

    }

}
