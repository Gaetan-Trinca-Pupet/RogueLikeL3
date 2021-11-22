package gameComponents;

import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import test.TimeEvent;
import utilities.UpdateOnTimeEvent;
import windowManager.GameWindow;

public class GameContext implements KeyEventManager, MouseEventManager, UpdateOnTimeEvent {
    private GameState currentState;
    GameWindow gameWindow;

    public GameContext(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        currentState = new NullState();
        setState(new PlayState(this, currentState));

        gameWindow.setEventHandlerTo(MouseEvent.ANY, this::mouseEvent);
        gameWindow.setEventHandlerTo(KeyEvent.ANY, this::keyboardEvent);
        gameWindow.setEventHandlerTo(TimeEvent.TIME_PASSES, this::updateOnTimeEvent);
    }

    public void setState(GameState newState) {
        currentState = newState;
    }

    public GameState getState(){
        return currentState;
    }

    public GameWindow getGameWindow(){
        return gameWindow;
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        currentState.keyboardEventHandler(event);
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        currentState.mouseEventHandler(event);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        currentState.timeEventHandler(event);
    }
}
