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

public class GameContext extends Thread{
    private boolean isGameRunning;
    private GameState currentState;
    GameWindow gameWindow;

    private Thread mainLoopThread;

    public GameContext(GameWindow gameWindow) {
        isGameRunning = true;
        this.gameWindow = gameWindow;
        setState(new PlayState(this));

        mainLoopThread = new Thread(this::loopUpdateAtFrequency);
        mainLoopThread.start();
    }

    public void setState(GameState newState) {
        currentState = newState;
        gameWindow.addEventHandler(MouseEvent.ANY, currentState::mouseEvent);
        gameWindow.addEventHandler(KeyEvent.ANY, currentState::keyboardEvent);
        gameWindow.addEventHandler(WindowEvent.ANY, this::closeGame);
        //gameWindow.addEventHandler(Event.ANY, currentState::update);
    }

    public GameWindow getGameWindow(){
        return gameWindow;
    }

    private void loopUpdateAtFrequency(long hz){
        float deltaTime = 1;
        for(long chrono = System.currentTimeMillis() ; isGameRunning ; chrono = System.currentTimeMillis()){
            currentState.update();
            while(System.currentTimeMillis() - chrono < 1000/hz);
            deltaTime = (System.currentTimeMillis() - chrono) / (1000/hz);
        }
    }

    private void loopUpdateAtFrequency(){
        loopUpdateAtFrequency(60);
    }

    public void closeGame(WindowEvent event){
        System.out.println(event.getEventType());
        isGameRunning = false;
    }
}
