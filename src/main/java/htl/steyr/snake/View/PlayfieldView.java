package htl.steyr.snake.View;

import htl.steyr.snake.Model.Playfield;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class PlayfieldView {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    Playfield field;
    GridPane gridPane;

    public PlayfieldView(Playfield field, GridPane gridPane) {
        this.field = field;
        this.gridPane = gridPane;
    }

    public void drawPlayfield() {
        gridPane.getChildren().clear();
        gridPane.setStyle("-fx-border-style: solid; -fx-border-color: blue;");

        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field.length; j++) {
                Label label = new Label();
                label.setStyle("-fx-background-color: green; -fx-pref-width: 25.48; -fx-pref-height: 25.48");
                gridPane.add(label, i, j);

                switch (field.field[i][j]) {
                    case 0:
                        break;
                    case 1:
                        imageView1 = new ImageView("apple.png");
                        imageView1.setFitHeight(22.8);
                        imageView1.setFitWidth(22.8);
                        gridPane.add(imageView1, i, j);
                        break;
                    case 2:
                        imageView2 = new ImageView("img.png");
                        imageView2.setFitHeight(22.8);
                        imageView2.setFitWidth(22.8);
                        gridPane.add(imageView2, i, j);
                        break;
                    case 3:
                        imageView3 = new ImageView("barrier.png");
                        imageView3.setFitHeight(22.8);
                        imageView3.setFitWidth(22.8);
                        gridPane.add(imageView3, i, j);
                        break;
                }
            }
        }
    }

}
