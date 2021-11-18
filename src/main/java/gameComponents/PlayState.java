package gameComponents;

import Controller.MouseAndKeyboardController;
import Controller.Touche;
import collision.SquareCollision;
import entity.Entity;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import map.Map;
import test.EntityTest;
import test.Square;
import sprite.CompositeSprite;
import utilities.Vector2D;

public class PlayState implements GameState{
    private Stage primaryStage;
    private GameContext gameContext;
    private MouseAndKeyboardController controller;

    private EntityTest entityTest;


    public PlayState(GameContext gameContext){
        this.gameContext = gameContext;
        controller = new MouseAndKeyboardController();
        controller.setCenterScreen(gameContext.gameWindow.getScreenCenter());
        startGame();
    }

    public void startGame(){
        //TODO
        //Exemple :
        Entity.setGameContext(gameContext);

        entityTest = new EntityTest(controller);

        entityTest.setPosition(new Vector2D(0,0));
        entityTest.setHitBox(new SquareCollision(entityTest.getPosition(), new Vector2D(100, 100)));

        Map map = new Map();
        gameContext.gameWindow.getBackground().add(map.getSprite());
        gameContext.gameWindow.paintAll();


        map.getSprite().setPosition(entityTest.getPosition());
        entityTest.setSprite(map.getSprite());
    }

    @Override
    public void update() {
        entityTest.update();
        gameContext.gameWindow.paintAll();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        controller.handleMouseEvent(event);
//        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
//            System.out.println("x : " + (event.getX() - gameContext.gameWindow.getScreenCenter().x) + " | y : " + (event.getY() - gameContext.gameWindow.getScreenCenter().y));
        //update();
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        controller.handleKeyEvent(event);
//        if (event.getEventType() == KeyEvent.KEY_PRESSED)
//            System.out.println(event.getCode());
        //update();
    }
}
