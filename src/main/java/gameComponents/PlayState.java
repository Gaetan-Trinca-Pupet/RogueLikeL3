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
import sprite.Sprite;
import test.EntityTest;
import test.Square;
import sprite.CompositeSprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class PlayState implements GameState{
    private Stage primaryStage;
    private GameContext gameContext;
    private MouseAndKeyboardController controller;

    private Map map;
    private EntityTest entityTest;

    private List<Sprite> background;
    private List<Sprite> ground;
    private List<Sprite> foreground;


    public PlayState(GameContext gameContext){
        this.gameContext = gameContext;
        controller = new MouseAndKeyboardController();
        controller.setCenterScreen(gameContext.gameWindow.getScreenCenter());

        background = new ArrayList<>();
        ground = new ArrayList<>();
        foreground = new ArrayList<>();

        startGame();
    }

    public void startGame(){
        //TODO
        //Exemple :
        Entity.setGameContext(gameContext);

        entityTest = new EntityTest(controller);

        entityTest.setPosition(new Vector2D(0,0));
        entityTest.setHitBox(new SquareCollision(entityTest.getPosition(), new Vector2D(100, 100)));

        map = new Map();
        background.add(map.getSprite());
        paintAll();

        entityTest.setSprite(map.getSprite());
    }

    private void paintAll() {
        gameContext.gameWindow.paintAll(background, ground, foreground);
    }

    @Override
    public void update() {
        entityTest.update();
        paintAll();
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
