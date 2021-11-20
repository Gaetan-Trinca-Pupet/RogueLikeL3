package gameComponents;

import Consomable.Apple;
import Controller.MouseAndKeyboardController;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import Inventory.Stockable;
import entity.Entity;
import entity.Pickable;
import entity.Player;
import equipment.Sword;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import map.Map;
import sprite.ImageSprite;
import sprite.Sprite;
import utilities.Updatable;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayState implements GameState{
    private Stage primaryStage;
    private GameContext gameContext;
    private MouseAndKeyboardController controller;

    private Map map;
    private List<Updatable> updatableList;
    private List<KeyEventManager> keyEventList;
    private List<MouseEventManager> mouseEventList;

    private SpriteHandler spriteList;

    private Thread mainLoopThread;

    public PlayState(GameContext gameContext){
        updatableList = new ArrayList<>();
        keyEventList = new ArrayList<>();
        mouseEventList = new ArrayList<>();

        this.gameContext = gameContext;

        controller = new MouseAndKeyboardController();
        controller.setCenterScreen(gameContext.gameWindow.getScreenCenter());
        keyEventList.add(controller);
        mouseEventList.add(controller);

        spriteList = new SpriteHandler();

        startGame();
    }

    public void startGame(){
        //TODO
        //Exemple :
        Entity.setGameContext(gameContext);
        Entity.setSpriteHandler(spriteList);

        Player player = new Player(controller);
        player.setPosition(new Vector2D(0,0));
        Pickable[] pommes = new Pickable[200];
        Random random = new Random();
        for(Pickable pomme : pommes)
        {
            pomme = new Pickable(new Apple());
            pomme.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
            player.addInteraction(pomme);
        }

        updatableList.add(player);
        mouseEventList.add(player);
        keyEventList.add(player);

        //map = new Map();
        //background.add(map.getSprite());
        paintAll();
    }

    private void paintAll() {
        gameContext.gameWindow.paintAll(spriteList);
    }

    @Override
    public void update() {
        for(Updatable object : updatableList) {
            object.update();
        }
        paintAll();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        for(MouseEventManager object : mouseEventList)
            object.mouseEvent(event);
        //update();
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        for(KeyEventManager object : keyEventList)
            object.keyboardEvent(event);
        //update();
    }
}
