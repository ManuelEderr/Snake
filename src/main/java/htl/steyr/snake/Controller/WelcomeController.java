package htl.steyr.snake.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class WelcomeController {
    public Button helpbtn;
    public Button playbtn;
    public Button settingsbtn;

    public void clickhelpbtn(ActionEvent actionEvent) {

        // das Spiel und die Steuerung kurz erklären auf Englisch
    }

    public void clicksettingsbtn(ActionEvent actionEvent) throws IOException {
        // Weiterleiten auf Settings.fxml dann wieder zurück auf hello-view.fxml
        // dann playbtn
        // gleich wie clickplaybtn Funktion
        changeSceneSettings();
    }

    public void clickplaybtn(ActionEvent actionEvent) throws IOException {
        changeScenePlayfield();
    }

    public void changeScenePlayfield() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) playbtn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
//        URL u = HelloApplication.class.getResource("Playfield.fxml");
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


    public void changeSceneSettings() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) settingsbtn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = SnakeApplication.class.getResource("Settings.fxml");

        assert u != null;
        Scene scene = new Scene(fxmlLoader.load(u.openStream()));
        SettingsController sc = fxmlLoader.getController();

        stage.setTitle("Snake");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

