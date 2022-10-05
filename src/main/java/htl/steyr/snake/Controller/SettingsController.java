package htl.steyr.snake.Controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SettingsController {

    public Button playbtn;
    public Slider volumeSlider;
    public String path;
    public Media media;
    public MediaPlayer mediaPlayer;
    public double startvalue;
    public double value;
    public ChoiceBox chooseDifficulty;
    public String difficult;

    public void initialize() {
        path = "pictures_music\\legendarymusic.mp3";
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        startvalue = volumeSlider.getValue();

        Difficulty();
    }

    public void clickplaybtn(ActionEvent actionEvent) throws IOException {
        MediaControll();

        changeScenePlayfield();

        if(value == 0){
            mediaPlayer.stop();
        }
      difficult = (String) chooseDifficulty.getValue();
    }

    public void changeScenePlayfield() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) playbtn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = SnakeApplication.class.getResource("Playfield.fxml");
        assert u != null;
        Scene scene = new Scene(fxmlLoader.load(u.openStream()));
        PlayfieldController pc = fxmlLoader.getController();
        pc.afterSwitch(scene);

        stage.setTitle("Snake");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }


    public void MediaControll() {
        mediaPlayer.play();
        value = volumeSlider.getValue();

        if(startvalue != value){
            volumeSlider.setValue(value);

        }

     /*
        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });
       */
    }

    public void Difficulty(){
        chooseDifficulty.setItems(FXCollections.observableArrayList("slow","normal","fast"));

    }
}
