package gameComponents;

import Controller.Action;
import entity.Player;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import test.Square;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

public class InventoryState extends GameState{
    private Player player;

    private Map map;

    private SpriteHandler backGround;

    public InventoryState(Player player, GameContext gameContext, GameState lastState){
        super(gameContext, lastState);
        this.player = player;

        keyEventList.add(controller);
        mouseEventList.add(controller);

        backGround = new SpriteHandler();
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        backGround.addSpriteTo(Ground.BACKGROUND, new Square(screenSize.divideBy(new Vector2D(2,2)),screenSize, new Color(0,0,0,0.2)));

        spriteList = player.getInventory().getSpriteHandler();
    }

    public void quitInventory(){
        backToLastContext();
    }

    public void paintInventory(){
        gameContext.gameWindow.paintAll(lastState.spriteList, backGround, spriteList);
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

    }

    @Override
    public void update() {
        paintInventory();
    }
}
