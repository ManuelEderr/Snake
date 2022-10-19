package htl.steyr.snake.View;

import htl.steyr.snake.Model.Playfield;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class PlayfieldView {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    Playfield field;
    GridPane gridPane;

    /**
     * @param field Playfield, in welchem SNAKE(2), FOOD(1), BARRIER(3) und EMPTY(0) gespeichert werden
     * @param gridPane GridPane, in welchem das Playfield gezeichnet wird
     */
    public PlayfieldView(Playfield field, GridPane gridPane) {
        this.field = field;
        this.gridPane = gridPane;
    }

    /**
     * Zeichnet das Playfield:
     *  case:
     *      -1 Apfel
     *      -2 Schlange
     *      -3 Hindernis
     * Das Playfield wird kariert, mit den Farben #aad751 und #a2d149 eingefärbt.
     */
    public void drawPlayfield() {
        gridPane.getChildren().clear();
        gridPane.setStyle("-fx-grid-lines-visible: false;");

        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field.length; j++) {
                Label label = new Label();
                if ((i + j) % 2 == 0) {
                    label.setStyle("-fx-background-color: #aad751; -fx-pref-width: 40; -fx-pref-height: 40");
                } else {
                    label.setStyle("-fx-background-color: #a2d149; -fx-pref-width: 40; -fx-pref-height: 40");
                }

                gridPane.add(label, i, j);

                switch (field.field[i][j]) {
                    case 1:
                        imageView1 = new ImageView("apple.png");
                        imageView1.setFitHeight(40);
                        imageView1.setFitWidth(40);
                        gridPane.add(imageView1, i, j);
                        break;
                    case 2:
                        imageView2 = new ImageView("img1.png");
                        imageView2.setFitHeight(40);
                        imageView2.setFitWidth(40);
                        gridPane.add(imageView2, i, j);
                        break;
                    case 3:
                        Random random = new Random();
                        int zufallsZahl = random.nextInt(2);

                        if (zufallsZahl == 0) {
                            imageView3 = new ImageView("barrier1.png");
                        } else {
                            imageView3 = new ImageView("barrier2.png");
                        }

                        imageView3.setFitHeight(30);
                        imageView3.setFitWidth(30);
                        gridPane.add(imageView3, i, j);
                        break;
                }
            }
        }
    }

}
