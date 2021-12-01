package gameComponents;

import Controller.Action;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import entity.*;
import Monster.Wolf;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import sprite.LabelSprite;
import test.TimeEvent;
import utilities.UpdateOnTimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends GameState{
    private Map map;

    private Player player;
    private LabelSprite HPplayer;

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

        map = new Map(controller);
        spriteList.addHandlerToGround(Ground.GROUND, map.getSpriteHandler());

        //Player player = new Player(controller, map);
        //player.setPosition(new Vector2D(0,0));
        this.player = map.getPlayer();

//        Pickable[] pommes = new Pickable[200];
        Random random = new Random();
//        for(Pickable pomme : pommes)
//        {
//            pomme = new Pickable(new Apple());
//            pomme.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
//            player.addInteraction(pomme);
//        }

//        Pickable epee = new Pickable(new Sword());
//        epee.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
//        player.addInteraction(epee);
//
//        Pickable botte = new Pickable(new RogueBoots());
//        botte.setPosition(new Vector2D(random.nextInt(1000)-500, random.nextInt(1000)-500));
//        player.addInteraction(botte);
//
        Creature loup = new Monster(new Wolf());
        loup.setPosition(new Vector2D(random.nextInt(500), random.nextInt(500)));
        //player.addInteraction(loup);

//        Creature ours = new Monster(new Bear());
//        ours.setPosition(new Vector2D(random.nextInt(1000)-800, random.nextInt(1000)-800));
//        player.addInteraction(ours);
//        spriteList.addSpriteTo(Ground.GROUND, ours.getSprite());

        HPplayer = new LabelSprite("HP : " + player.getCurrentLife() + " / " + player.getMaxLife(), Color.BLACK, new Vector2D(-960,-500), 50);
        spriteList.addSpriteTo(Ground.HUD, HPplayer);

        //updatableList.add(map);
        keyEventList.add(map.getPlayer());

        paintAll();

        inventoryState = new InventoryState(map.getPlayer(), gameContext, this);
        mapState = new MapState(map, gameContext, this);
    }

    private void paintAll() {
        spriteList.clean(Ground.GROUND);
        spriteList.addHandlerToGround(Ground.GROUND, map.getCurrentRoom().getSpriteHandler());
        gameContext.gameWindow.paintAll(spriteList);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        for(UpdateOnTimeEvent object : updatableList) {
            object.updateOnTimeEvent(event);
        }
        map.getCurrentRoom().updateOnTimeEvent(event);
        HPplayer.setText("HP : " + player.getCurrentLife() + " / " + player.getMaxLife());
        paintAll();
        if(player.getCurrentLife() <= 0) gameContext.setState(new GameOverState(gameContext, this));
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
