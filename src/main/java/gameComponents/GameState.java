package gameComponents;

import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public interface GameState {
    void update();
    void mouseEvent(MouseEvent event);
    void keyboardEvent(KeyEvent event);
}
