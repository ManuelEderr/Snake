module htl.steyr.snake {
    requires javafx.controls;
    requires javafx.fxml;

    opens htl.steyr.snake.Controller to javafx.fxml;
    exports htl.steyr.snake;
}


