module htl.steyr.snake {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.media;
    requires java.desktop;
    requires org.json;
    requires jsr250.api;

    opens htl.steyr.snake.Controller to javafx.fxml;
    exports htl.steyr.snake.Controller to javafx.graphics;
}


