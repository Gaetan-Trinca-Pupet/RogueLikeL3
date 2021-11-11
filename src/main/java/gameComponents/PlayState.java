package gameComponents;

import javafx.event.Event;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import map.Map;
import test.Square;
import utilities.CompositeSprite;
import utilities.Vector2D;

public class PlayState implements GameState{
    private Stage primaryStage;
    private GameContext gameContext;


    public PlayState(GameContext gameContext){
        this.gameContext = gameContext;
        startGame();
    }

    public void startGame(){
        //TODO
        //Exemple :
        Map map = new Map();
        gameContext.gameWindow.getBackground().add(map.getSprite());
        gameContext.gameWindow.paintAll();
    }

    @Override
    public void update() {
        gameContext.gameWindow.paintAll();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
            System.out.println((event.getX() - gameContext.gameWindow.getScreenCenter().x) + " " + (event.getY() - gameContext.gameWindow.getScreenCenter().y));
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED)
            System.out.println(event.getCode());
    }
}
