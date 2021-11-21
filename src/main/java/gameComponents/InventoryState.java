package gameComponents;

import Controller.Action;
import entity.Player;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import sprite.Sprite;
import test.Square;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

public class InventoryState extends GameState{
    private Player player;

    private SpriteHandler backScreen;

    private Map map;

    public InventoryState(Player player, GameContext gameContext, GameState lastState){
        super(gameContext, lastState);
        this.player = player;

        keyEventList.add(controller);
        mouseEventList.add(controller);

        spriteList = this.player.getInventory().getSpriteHandler();

        backScreen = new SpriteHandler();
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        backScreen.addSpriteTo(Ground.BACKGROUND, new Square(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));

        Vector2D size = new Vector2D(this.player.getInventory().sizeInventory);
        Vector2D pos = new Vector2D(size).divideBy(new Vector2D(-2, -2));
        Sprite border = new Square(pos.subtract(new Vector2D(10,10)), size.add(new Vector2D(20,20)), new Color(1,1,1,1));
        Sprite centerSquare = new Square(pos, size, new Color(0,0,0,1));

        backScreen.addSpriteTo(Ground.GROUND, border);
        backScreen.addSpriteTo(Ground.FOREGROUND, centerSquare);

    }

    public void quitInventory(){
        backToLastContext();
    }

    public void paintInventory(){
        gameContext.gameWindow.paintAll(lastState.getSpriteList(), backScreen, spriteList);
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
