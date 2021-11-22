package gameComponents;

import Controller.Action;
import entity.Creature;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sprite.Sprite;
import test.Square;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

public class FightState extends GameState{
    private Creature player;
    private Creature monster;

    public FightState(GameContext gameContext, GameState lastState, Creature player, Creature monster){
        super(gameContext, lastState);

        this.player = player;
        this.monster = monster;


        Vector2D size = new Vector2D(1200,800);
        Vector2D pos = new Vector2D(size).divideBy(new Vector2D(-2, -2));
        Sprite border = new Square(pos.subtract(new Vector2D(10,10)), size.add(new Vector2D(20,20)), new Color(1,1,1,1));
        Sprite centerSquare = new Square(pos, size, new Color(0,0,0,1));

        spriteList = new SpriteHandler();
        spriteList.addHandlerToGround(Ground.BACKGROUND, lastState.getSpriteList());

        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        spriteList.addSpriteTo(Ground.GROUND, new Square(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));
        spriteList.addSpriteTo(Ground.GROUND, border);
        spriteList.addSpriteTo(Ground.GROUND, centerSquare);
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(event.getCode() == controller.keyCodeForAction(Action.ESCAPE))
                backToLastContext();

            if(event.getCode() == controller.keyCodeForAction(Action.INVENTORY))
                gameContext.setState(new InventoryState(player, gameContext, this));
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        gameContext.gameWindow.paintAll(spriteList);
    }
}
