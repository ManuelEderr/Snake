package htl.steyr.snake.Controller;

import htl.steyr.snake.Model.Playfield;
import htl.steyr.snake.Model.Snake;
import htl.steyr.snake.View.PlayfieldView;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

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

    public void initialize() {
        time = System.currentTimeMillis();
        pfView = new PlayfieldView(snakePlayfield, boardView);
    }

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
                timeLabel.setText("Time: " + ((System.currentTimeMillis() - time) / 1000) + " sec");

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
                            System.out.println("GAME OVER");
                        }

                        pfView.drawPlayfield();

                        lasttick = now;
                    }
                }
            }
        }.start();

    }

    /*
    void jsonFunction() throws IOException {
        String path = "highscore.json";
        JSONObject jo = new JSONObject();
        jo.put("name", "");
        jo.put("score", snake.getCountScore());

        JSONArray ja = new JSONArray();
        ja.put(jo);

        System.out.println(ja.toString());
        //Files.write(path, ja.toString().getBytes(), StandardOpenOption.CREATE);

    }

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

    private static boolean isInDB(JSONObject obj, JSONArray arr) throws JSONException {
        for (int i = 0; i < arr.length(); i++) {
            JSONObject item = arr.getJSONObject(i);
            if (obj.toString().equals(item.toString())) {
                return true;
            }
        }
        return false;
    }

    private static JSONArray saveArray(JSONArray arr, String jsonFileName) throws JSONException, IOException {
        try (FileWriter Data = new FileWriter(jsonFileName)) {
            Data.write(arr.toString(4)); // setting spaces for indent
        }
        return arr;
    }

}
