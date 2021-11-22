package gameComponents;

import Controller.Action;
import EventManager.MouseEventManager;
import entity.Creature;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import test.Square;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

public class InventoryState extends GameState{
    private Creature creature;

    private Map map;

    public InventoryState(Creature creature, GameContext gameContext, GameState lastState){
        super(gameContext, lastState);
        this.creature = creature;



        spriteList = new SpriteHandler();


        mouseEventList.add(creature.getInventory());
    }

    public void quitInventory(){
        backToLastContext();
    }

    public void paintInventory(){
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        spriteList = new SpriteHandler();

        spriteList.addHandlerToGround(Ground.BACKGROUND, lastState.getSpriteList());
        spriteList.addSpriteTo(Ground.BACKGROUND, new Square(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));
        spriteList.addHandlerToGround(Ground.FOREGROUND, creature.getInventory().getSpriteHandler());
        gameContext.gameWindow.paintAll(spriteList);
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(event.getCode() == controller.keyCodeForAction(Action.ESCAPE))
                quitInventory();
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
            creature.getInventory().eventController(controller);
        for(MouseEventManager mouseEvent : mouseEventList)
            mouseEvent.mouseEvent(event);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        paintInventory();
    }
}
