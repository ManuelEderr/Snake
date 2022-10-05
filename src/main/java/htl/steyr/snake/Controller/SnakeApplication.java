package htl.steyr.snake.Controller;

import htl.steyr.snake.View.PlayfieldView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SnakeApplication extends Application {

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

/*
        ThreadClass1 thread = new ThreadClass1("pictures_music\\legendarymusic.mp3");
        Thread thread2 = new Thread(thread);
        thread2.start();
*/
      //  File file = new File("pictures_music\\legendarymusic.mp3");
       // Media media = new Media(file.toURI().toString());


        /*
        String path = "pictures_music\\legendarymusic.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
*/

        launch();

    }
}
