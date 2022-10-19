package htl.steyr.snake.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;

/**
 * In dieser Klasse wird das Programm gestartet.
 */
public class SnakeApplication extends Application {

    /**
     *  @author nschickm
     * Als ersters wird die "Splash-view.fxml" ausgerufen
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Splash-view.fxml")));
        Scene scene = new Scene(pane);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {

        launch();

    }
}
