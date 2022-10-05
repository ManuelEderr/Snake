package htl.steyr.snake.View;

import htl.steyr.snake.Model.Playfield;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class PlayfieldView {

    Playfield field;
    GridPane gridPane;


    public PlayfieldView(Playfield field, GridPane gridPane) {
        this.field = field;
        this.gridPane = gridPane;
    }

    public void drawPlayfield() {
        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field.length; j++) {
                switch (field.field[i][j]) {
                    case 0:
                        ImageView imageView0 = new ImageView("Heisenberg.png");
                        imageView0.setFitHeight(22.8);
                        imageView0.setFitWidth(22.8);
                        gridPane.add(imageView0, i, j);
                        break;
                    case 1:
                        ImageView imageView1 = new ImageView("apple.png");
                        imageView1.setFitHeight(22.8);
                        imageView1.setFitWidth(22.8);
                        gridPane.add(imageView1, i, j);
                        break;
                    case 2:
                        ImageView imageView2 = new ImageView("img.png");
                        imageView2.setFitHeight(22.8);
                        imageView2.setFitWidth(22.8);
                        gridPane.add(imageView2, i, j);
                        break;

                }
            }
        }

    }

}
