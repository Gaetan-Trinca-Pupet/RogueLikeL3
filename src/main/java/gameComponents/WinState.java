package gameComponents;

import Controller.Action;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sprite.LabelSprite;
import sprite.SquareSprite;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;

public class WinState extends GameState{
    public WinState(GameContext gameContext, GameState lastState){
        super(gameContext, lastState);
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();

        spriteList.addHandlerToGround(Ground.BACKGROUND, this.lastState.getSpriteList());
        spriteList.addSpriteTo(Ground.BACKGROUND, new SquareSprite(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));
        spriteList.addSpriteTo(Ground.BACKGROUND, new SquareSprite(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,1,0,0.2)));

        LabelSprite labelSprite = new LabelSprite("VOUS AVEZ VAINCU LE BOSS ET GAGNÉ !\nF pour reelancer une partie\nESCAPE pour quitter", Color.BLACK, new Vector2D(-400,-200));
        labelSprite.setSize(70);
        labelSprite.setStrokeColor(Color.WHITE);
        spriteList.addSpriteTo(Ground.FOREGROUND, labelSprite);
        gameContext.gameWindow.paintAll(spriteList);
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(event.getCode() == controller.keyCodeForAction(Action.INTERACT))
                gameContext.setState(new PlayState(gameContext, new NullState(gameContext)));
            if(event.getCode() == controller.keyCodeForAction(Action.ESCAPE))
                gameContext.setState(new NullState(gameContext));
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {

    }
}
