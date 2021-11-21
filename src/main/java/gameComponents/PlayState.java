package gameComponents;

import Consomable.Apple;
import Controller.Action;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import entity.*;
import Monster.Wolf;
import equipment.RogueBoots;
import equipment.Sword;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import map.Map;
import utilities.Updatable;
import utilities.Vector2D;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState{
    private Map map;

    private InventoryState inventoryState;
    private MapState mapState;

    public PlayState(GameContext gameContext, GameState gameState){
        super(gameContext, gameState);

        updatableList = new ArrayList<>();
        keyEventList = new ArrayList<>();
        mouseEventList = new ArrayList<>();

        spriteList = new SpriteHandler();

        startGame();
    }

    public PlayState(GameContext gameContext){
        this(gameContext, null);
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

        Pickable epee = new Pickable(new Sword());
        epee.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
        player.addInteraction(epee);

        Pickable botte = new Pickable(new RogueBoots());
        botte.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
        player.addInteraction(botte);

        Creature loup = new Monster(new Wolf());
        loup.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
        player.addInteraction(loup);


        updatableList.add(player);
        mouseEventList.add(player);
        keyEventList.add(player);

        //map = new Map();
        //spriteList.addSpriteTo(Ground.BACKGROUND, map.getSprite());

        paintAll();

        inventoryState = new InventoryState(player, gameContext, this);
        //mapState = new MapState(map, gameContext, this);
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

        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(event.getCode() == controller.keyCodeForAction(Action.INVENTORY))
                gameContext.setState(inventoryState);
            if(event.getCode() == controller.keyCodeForAction(Action.MAP))
                gameContext.setState(mapState);
        }
        //update();
    }
}
