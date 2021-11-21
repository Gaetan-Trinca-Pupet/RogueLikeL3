package gameComponents;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import test.TimeEvent;
import windowManager.GameWindow;

public class GameContext{
    private GameState currentState;
    GameWindow gameWindow;

    public GameContext(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        currentState = new NullState();
        setState(new PlayState(this, currentState));
    }

    public void setState(GameState newState) {
        currentState = newState;

        gameWindow.setEventHandlerTo(MouseEvent.ANY, currentState::mouseEventHandler);
        gameWindow.setEventHandlerTo(KeyEvent.ANY, currentState::keyboardEventHandler);
        gameWindow.setEventHandlerTo(TimeEvent.TIME_PASSES, this::update);
    }

    public void update(TimeEvent event){
        currentState.update();
    }

    public GameState getState(){
        return currentState;
    }

    public GameWindow getGameWindow(){
        return gameWindow;
    }
}
