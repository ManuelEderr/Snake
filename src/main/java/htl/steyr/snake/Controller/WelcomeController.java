package htl.steyr.snake.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  @author nschickm
 * Hier wird der Startbild ("hello-view.fxml") bearbeitet. Es beinhaltet drei Buttons mit verschiedenen Aktionen.
 */
public class WelcomeController {
    public Button helpbtn;
    public Button playbtn;
    public Button settingsbtn;
    public TextField nameTextField;

    @FXML
    private AnchorPane apane;
    private AnchorPane AnchorPane;

    /**
     * Hier wird das Spiel kurz erklärt, wie man die Schlange bewegt und was man alles einstellen kann.
     * @param actionEvent
     */
    public void clickhelpbtn(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Help Dialog ");
        alert.setHeaderText(null);
        alert.setContentText("Snake game:\n" +
                "When the game button is pressed, the game is played with the default settings. When the settings button is pressed, the speed, music volume and the number of barriers can be changed.\n" +
                "The snake moves with W-A-S-D keys in the respective direction. \n" +
                "If you drive the snake against the wall or into itself, the game is over.");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * Wird diese Methode aufgerufen, wird man zur Erstellung weitergeleitet.
     * @param actionEvent
     * @throws IOException
     */
    public void clicksettingsbtn(ActionEvent actionEvent) throws IOException {
        changeSceneSettings();
    }

    /**
     * Wird diese Methode aufgerufen, wird man direkt weitergeleitet zum Spielfeld.
     * Es wird mit Standardeinstellungen gespielt:
     * -> ohne Ton
     * -> mit der Geschwindigkeit normal
     * -> mit null Barrieren
     * @param actionEvent
     * @throws IOException
     */
    public void clickplaybtn(ActionEvent actionEvent) throws IOException {
        changeScenePlayfield();
    }

    /**
     * Schließt das aktuelle fxml und leitet zum Spielfeld ("Playfield.fxml") weiter.
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
        pc.afterSwitch(scene, "normal","0", nameTextField.getText());

        stage.setTitle("Snake");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Schließt das aktuelle fxml und leitet zur Einstellung ("Settings.fxml") weiter.
     * @throws IOException
     */
    public void changeSceneSettings() throws IOException {
        Stage stage = new Stage();

        Stage stageclose = (Stage) settingsbtn.getScene().getWindow();
        stageclose.close();
 try {
        AnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")));
        Stage stage1 = new Stage();
        Scene scene = new Scene(AnchorPane);
        scene.setFill(Color.TRANSPARENT);
        stage1.initStyle(StageStyle.TRANSPARENT);
        stage1.setTitle("Snake");
        stage1.setScene(scene);
        stage1.setMinHeight(400);
        stage1.setMinWidth(255);
        stage1.show();
        apane.getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
