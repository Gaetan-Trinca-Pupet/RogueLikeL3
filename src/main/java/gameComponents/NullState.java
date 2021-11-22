package gameComponents;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import test.TimeEvent;

public class NullState extends GameState{
    public NullState(GameContext gameContext){
        super(gameContext);
    }

    @Override
    public void keyboardEvent(KeyEvent event) {

    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        gameContext.gameWindow.closeWindow();
    }
}
