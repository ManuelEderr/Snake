module htl.steyr.snake {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.media;
    requires java.desktop;

    opens htl.steyr.snake.Controller to javafx.fxml;
    exports htl.steyr.snake.Controller to javafx.graphics;
}


