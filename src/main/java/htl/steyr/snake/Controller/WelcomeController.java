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

    }

    public void clicksettingsbtn(ActionEvent actionEvent){

    }

    public void clickplaybtn(ActionEvent actionEvent) throws IOException {
        changeScene();
    }
    public void changeScene() throws IOException{
        Stage stage = new Stage();

        Stage stageclose = (Stage) playbtn.getScene().getWindow();
        stageclose.close();

        final FXMLLoader fxmlLoader = new FXMLLoader();
        URL u = PlayfieldController.class.getResource("Playfield.fxml");

        assert u != null;
        Scene scene = new Scene(fxmlLoader.load(u.openStream()));

        stage.setTitle("Snake ");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    }

