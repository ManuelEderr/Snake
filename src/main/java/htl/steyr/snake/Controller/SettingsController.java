package htl.steyr.snake.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author nschickm
 * In dieser Klasse werden die Spieleinstellungen ausgewaehlt und uebergeben.
 */
public class SettingsController {

    public Button playbtn;
    public Slider volumeSlider;
    public ChoiceBox chooseBarriers;
    private String path;
    private Media media;
    private MediaPlayer mediaPlayer;
    private double value;
    private double valueMediaplayer;
    public ChoiceBox chooseDifficulty;
    public String difficult;
    public String barriers;
    private int countMedia = 0;

    /**
     * Vll schon geschafft, morgen noch ansehen
     * Überprüfen ob sich die volume von VolumeSlider und die volume von mediaplayer verändert haben
     * Wenn ja wird der mediaplayer auf eine neue volume gesetzt.
     */

    /**
     * Die beiden Choiceboxen werden zu Beginn mit Werten versehen.
     */
    public void initialize() {

        chooseDifficulty.setItems(FXCollections.observableArrayList("slow", "normal", "fast"));
        chooseBarriers.setItems(FXCollections.observableArrayList("0", "5", "10", "20"));

    }

    /**
     * Sobald der Button geklickt wird, werden alle Methoden der Klasse augerufen und
     * deren return-Werte zu uebergeben.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void clickplaybtn(ActionEvent actionEvent) throws IOException {
        Difficulty();
        SetBarriers();
        changeScenePlayfield();
    }

    /**
     * Schließt das aktuelle fxml und leitet zum Spielfeld ("Playfield.fxml") weiter.
     * Die Einstellungen werden mit uebergeben.
     *
     * @throws IOException
     */
    public void changeScenePlayfield() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) playbtn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = SnakeApplication.class.getResource("Playfield.fxml");
        assert u != null;
        Scene scene = new Scene(fxmlLoader.load(u.openStream()));
        PlayfieldController pc = fxmlLoader.getController();
        pc.afterSwitch(scene, Difficulty(), SetBarriers());

        stage.setTitle("Snake");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }


    /**
     * Zuerst wird die mp3-Datei fuer den mediaplayer zurecht geformt.
     * Dann wird die angegebene Lautstaerke eingeholt.
     * Der mediaplayer wird gestartet mit der gewuenschten Lautstaerke.
     * Falls der mediaplayer die Lautstaerke null hat, wird der er gestoppt.
     */
    public void MediaControll() {
        if (countMedia == 0) {
            path = "pictures_music\\legendarymusic.mp3";
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        }
        value = volumeSlider.getValue();
        valueMediaplayer = mediaPlayer.getVolume();

        value = value / 100;
        if (value != valueMediaplayer) {
            mediaPlayer.setVolume(value);
            mediaPlayer.play();
        }


        if (value == 0.0) {
            mediaPlayer.stop();
        }
        countMedia = 1;
    }

    /**
     * Hier wird abgefragt welche Schnelligkeit der Schlange ausgewaehlt wurde.
     *
     * @return wie schnell sich die Schlange bewegt
     */
    public String Difficulty() {
        difficult = (String) chooseDifficulty.getValue();
        return difficult;
    }

    /**
     * Hier wird abgefragt welche Anzahl an Barrieren ausgewaehlt wurde.
     *
     * @return Anzahl der Barrieren
     */
    public String SetBarriers() {
        barriers = (String) chooseBarriers.getValue();
        return barriers;
    }
}
