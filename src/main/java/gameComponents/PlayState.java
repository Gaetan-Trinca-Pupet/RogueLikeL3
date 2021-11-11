package gameComponents;

import collision.SquareCollision;
import entity.Entity;
import javafx.event.Event;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import test.EntityTest;
import test.Square;
import utilities.CompositeSprite;
import utilities.Vector2D;

public class PlayState implements GameState{
    private Stage primaryStage;
    private GameContext gameContext;

    private EntityTest entityTest;


    public PlayState(GameContext gameContext){
        this.gameContext = gameContext;
        startGame();
    }

    public void startGame(){
        //TODO
        //Exemple :
        Entity.setGameContext(gameContext);

        gameContext.gameWindow.getBackground().add(new Square(new Vector2D(0,0), new Vector2D(100,100), new Color(0.5, 0.5, 1, 1)));
        CompositeSprite sprite = new CompositeSprite(new Vector2D(50, 50));
        sprite.add(new Square(new Vector2D(0,0), new Vector2D(50,50), new Color(0, 1, 0, 1)));
        sprite.add(new Square(new Vector2D(50,50), new Vector2D(50,50), new Color(0, 1, 0, 1)));
        sprite.add(new Square(new Vector2D(100,100), new Vector2D(50,50), new Color(1, 1, 0, 1)));
        gameContext.gameWindow.getForeground().add(sprite);

        entityTest = new EntityTest();

        entityTest.setPosition(new Vector2D());
        sprite.setPosition(entityTest.getPosition());
        entityTest.setSprite(sprite);
        entityTest.setHitBox(new SquareCollision(entityTest.getPosition(), new Vector2D(50, 50)));


        gameContext.gameWindow.paintAll();
    }

    @Override
    public void update() {
        gameContext.gameWindow.paintAll();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        entityTest.mouseEvent(event);
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
            System.out.println((event.getX() - gameContext.gameWindow.getScreenCenter().x) + " " + (event.getY() - gameContext.gameWindow.getScreenCenter().y));

        gameContext.gameWindow.paintAll();
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        entityTest.keyboardEvent(event);
        if (event.getEventType() == KeyEvent.KEY_PRESSED)
            System.out.println(event.getCode());

        gameContext.gameWindow.paintAll();
    }
}
