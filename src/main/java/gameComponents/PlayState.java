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
    private Map map;


    public PlayState(GameContext gameContext){
        this.gameContext = gameContext;
        startGame();
    }

    public void startGame(){
        //TODO
        //Exemple :
        map = new Map();
        gameContext.gameWindow.getBackground().add(new Square(new Vector2D(0,0), new Vector2D(100,100), new Color(0.5, 0.5, 1, 1)));
        CompositeSprite sprite = new CompositeSprite(new Vector2D(50, 50));
        sprite.add(new Square(new Vector2D(0,0), new Vector2D(50,50), new Color(0, 1, 0, 1)));
        sprite.add(new Square(new Vector2D(50,50), new Vector2D(50,50), new Color(0, 1, 0, 1)));
        sprite.add(new Square(new Vector2D(100,100), new Vector2D(50,50), new Color(1, 1, 0, 1)));
        gameContext.gameWindow.getForeground().add(sprite);

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
