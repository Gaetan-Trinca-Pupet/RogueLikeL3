package gameComponents;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import test.Square;
import utilities.Vector2D;
import windowManager.GameWindow;

import java.util.EventListenerProxy;
import java.util.EventObject;

public class GameContext{
    private GameState currentState;
    GameWindow gameWindow;

    public GameContext(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setState(new PlayState(this));
    }

    public void setState(GameState newState) {
        currentState = newState;
        gameWindow.addEventHandler(MouseEvent.ANY, currentState::mouseEvent);
        gameWindow.addEventHandler(KeyEvent.ANY, currentState::keyboardEvent);
        //gameWindow.addEventHandler(Event.ANY, currentState::update);
    }

    public GameWindow getGameWindow(){
        return gameWindow;
    }
}
