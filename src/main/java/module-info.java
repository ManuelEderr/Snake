module htl.steyr.snake {
    requires javafx.controls;
    requires javafx.fxml;


    opens htl.steyr.snake to javafx.fxml;
    exports htl.steyr.snake;
}