package gameComponents;

import javafx.event.Event;

public interface GameState {
    void update();
    void handle(Event event);
}
