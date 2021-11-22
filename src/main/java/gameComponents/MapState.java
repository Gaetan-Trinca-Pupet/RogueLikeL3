package gameComponents;

import Controller.Action;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import sprite.CompositeSprite;
import test.Square;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;


public class MapState extends GameState {
    private CompositeSprite sprite;
    private SpriteHandler backGround;

    private final Vector2D ROOM_DISPLAY_SIZE = new Vector2D(16, 16);

    public MapState(Map map, GameContext gameContext, GameState lastState) {
        super(gameContext, lastState);

        keyEventList.add(controller);
        mouseEventList.add(controller);

        backGround = new SpriteHandler();
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        backGround.addSpriteTo(Ground.BACKGROUND, new Square(screenSize.divideBy(new Vector2D(2,2)),screenSize, new Color(0,0,0,0.2)));

        sprite = new CompositeSprite();
        for (int y = 0; y < map.getMapSize().y; ++y) {
            for (int x = 0; x < map.getMapSize().x; ++x) {
                if (map.getRooms().get(x).get(y) != null) {
                    sprite.add(new Square(new Vector2D(x, y).multiply(ROOM_DISPLAY_SIZE), ROOM_DISPLAY_SIZE, map.getRooms().get(x).get(y).getMinimapColor()));
                }
            }
        }

        spriteList.addSpriteTo(Ground.FOREGROUND, sprite);
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(event.getCode() == controller.keyCodeForAction(Action.ESCAPE))
                backToLastContext();
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        paint();
    }

    private void paint() {
        gameContext.gameWindow.paintAll(spriteList);
    }
}