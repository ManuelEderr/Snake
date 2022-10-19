package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.text.DecimalFormat;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Hier wird das Spiel ausgefuehrt und alle notwendigen
 * Bedingugen die man waehrend des Spiels benoetigt
 * werden staendig ueberprueft.
 */
public class PlayfieldController {
    public GridPane boardView = new GridPane();
    public static int UP = 0;
    public static int DOWN = 1;
    public static int RIGHT = 2;
    public static int LEFT = 3;
    public static int EASY = 300000000;
    public static int MEDIUM = 100000000;
    public static int HARD = 0;
    public Label labelScore;
    int speed;
    public Label timeLabel;
    Playfield snakePlayfield = new Playfield();
    PlayfieldView pfView;
    private int direction = RIGHT;
    private long lasttick;
    private int barrierCount = 0;
    private boolean pause;
    private boolean end;
    Snake snake = new Snake();
    private double time = 0.0;
    public Button restartBtn;

    /**
     * Die aktuelle Zeit in Millisekunden wird gespeichert und eine
     * Playfieldview wird erzeugt.
     */
    public void initialize() {
        time = System.currentTimeMillis();
        pfView = new PlayfieldView(snakePlayfield, boardView);
    }

    /**
     * Die Einstellungen werden in der Methode eingestellt und die
     * Tasten-Bedingugen festgelegt.
     * Im AnimationTimer werden Aepfel und Barrieren eingezechnet, die Spielzeit und der Score wird erhoeht.
     * Außerdem wird ueberprueft ob die Schlange noch lebt.
     * @param scene
     * @param difficulty die Geschwindigkeit der Schlange.
     * @param barriers die Anzahl der Barrieren die auf das Spielfeld platziert werden.
     */
    public void afterSwitch(Scene scene, String difficulty, String barriers) {
        labelScore.setStyle("-fx-font-size: 20px; -fx-font-family: 'Agency FB'");
        timeLabel.setStyle("-fx-font-size: 20px; -fx-font-family: 'Agency FB'");
        switch (difficulty) {
            case "slow":
                this.speed = EASY;
                break;
            case "normal":
                this.speed = MEDIUM;
                break;
            case "fast":
                this.speed = HARD;
                break;
        }

        barrierCount = Integer.parseInt(barriers);

        Platform.runLater(() -> {
            scene.setOnKeyPressed(keyEvent -> {
                switch (keyEvent.getCode()) {
                    case W:
                        direction = UP;
                        break;
                    case S:
                        direction = DOWN;
                        break;
                    case A:
                        direction = LEFT;
                        break;
                    case D:
                        direction = RIGHT;
                        break;
                    case SPACE:
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        if (pause) {
                            pause = false;
                        } else {
                            alert.setTitle("Pause");
                            alert.setContentText("Press SPACE to continue");
                            alert.show();
                            pause = true;
                        }
                        break;
                    case ESCAPE:
                        end = true;
                        break;
                }
            });
        });



        new AnimationTimer() {
            @Override
            public void handle(long now) {
                DecimalFormat df = new DecimalFormat("00");

                int minutes = (int) (((System.currentTimeMillis() - time) / (1000 * 60)) % 60);
                int seconds = (int) ((System.currentTimeMillis() - time) / 1000) % 60;
                int millis = (int) ((System.currentTimeMillis() - time) % 100);

                timeLabel.setText("Time: " + df.format(minutes) + ":" + df.format(seconds) + ":" + df.format(millis));

                if (!pause) {
                    if (lasttick == 0) {
                        lasttick = now;
                    }

                    if (now - lasttick > speed) {
                        if (!snakePlayfield.containsApple()) {
                            snakePlayfield.drawRandomApple();
                        }

                        if (!snakePlayfield.containsBarrier()) {
                            snakePlayfield.drawRandomBarrier(barrierCount);
                        }

                        snakePlayfield = snake.move(snakePlayfield, direction);
                        labelScore.setText("Score: " + snake.getCountScore());

                        if (snakePlayfield == null || end) {

                            try {
                                JSONArray arr = initArray("highscore.json");

                                JSONObject jo = new JSONObject();
                                jo.put("name", "");
                                jo.put("score", snake.getCountScore());

                                if (!isInDB(jo, arr)) {
                                    arr.put(jo);
                                    saveArray(arr, "highscore.json");
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            this.stop();
                            try {
                                changeScene();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        pfView.drawPlayfield();

                        lasttick = now;
                    }
                }
            }
        }.start();

    }

    /**
     *  @author nschickm
     *
     * Sobald die Schlange stirbt wird das Spielfeld ("Playfield.fxml") geschlossen
     * und das GameOver Fenster ("GameOVer.fxml") ploppt auf.
     * Auf dieser ein restart-Button abgebildet ist.
     * @throws IOException
     */
    public void changeScene() throws IOException {

        Stage stageclose2 = (Stage) labelScore.getScene().getWindow();
        stageclose2.close();

        Stage stage = new Stage();
        Parent pane = FXMLLoader.load(Objects.requireNonNull(SettingsController.class.getResource("GameOver.fxml")));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     *  @author nschickm
     * Wird der restart-Button geklicket wird das Spielfeld ("Playfield.fxml")
     * geschlossen. Zu gleich wird wieder zum Startbildschirm ("Hello-view.fxml") weitergeleitet.
     * @param mouseEvent
     * @throws IOException
     */
    public void restartGame(MouseEvent mouseEvent) throws IOException {

        Stage stageclose = (Stage) restartBtn.getScene().getWindow();
        stageclose.close();

        Stage stage = new Stage();
        Parent pane = FXMLLoader.load(Objects.requireNonNull(SettingsController.class.getResource("Hello-view.fxml")));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();


    }


    /**
     * Es wird eine neue Json-Datei mit den Namen "highscore.json" erstellt.
     * @param jsonFileName bestimmt den Namen der Datei
     * @return
     * @throws IOException
     * @throws JSONException
     */
    //https://stackoverflow.com/questions/61524425/how-to-add-new-jsonobjects-to-existing-jsonarray-in-json-file
    private static JSONArray initArray(String jsonFileName) throws IOException, JSONException {
        String json = "";
        File jsonFile = new File(jsonFileName);
        if (!jsonFile.exists()) {
            return new JSONArray();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }
        }
        JSONTokener tokener = new JSONTokener(json);
        JSONArray readArr = new JSONArray(tokener);
        System.out.println("Read arr: " + readArr.toString());
        return readArr;
    }

    /**
     * Ueberprueft, ob die Werte schon in der JSON Datei vorhanden sind.
     * @param obj Übergibt das Objekt mit den Key und Value.
     * @param arr Das JSONArray zum hineinschreiben.
     * @return true -> wenn der Wert schon in der Datei vorhanden ist.
     *        false -> wenn der Wert nicht in der Datei vorhanden ist.
     * @throws JSONException
     */
    private static boolean isInDB(JSONObject obj, JSONArray arr) throws JSONException {
        for (int i = 0; i < arr.length(); i++) {
            JSONObject item = arr.getJSONObject(i);
            if (obj.toString().equals(item.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Schreibt den Wert mittels FileWriter in die JSON Datei.
     * @param arr Das JSONArray zum hineinschreiben.
     * @param jsonFileName Der Filename der Daei
     * @return Das JSONArray das hineingeschrieben wurde wird zurueck gegeben.
     * @throws JSONException
     * @throws IOException
     */
    private static JSONArray saveArray(JSONArray arr, String jsonFileName) throws JSONException, IOException {
        try (FileWriter Data = new FileWriter(jsonFileName)) {
            Data.write(arr.toString(4)); // setting spaces for indent
        }
        return arr;
    }

}
