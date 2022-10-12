package htl.steyr.snake.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Help Dialog ");
        alert.setHeaderText(null);
        alert.setContentText("Snake game:\n" +
                "When the game button is pressed, the game is played with the default settings. When the settings button is pressed, the speed and music volume can be changed.\n" +
                "The snake moves with the four arrow keys in the respective direction. \n" +
                "If you drive the snake against the wall or into itself, the game is over.");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
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
        pc.afterSwitch(scene, "normal","5");

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

