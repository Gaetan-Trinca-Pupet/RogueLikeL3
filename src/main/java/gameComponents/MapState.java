package gameComponents;

import Controller.Action;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import map.Map;
import sprite.CompositeSprite;
import sprite.Sprite;
import test.Square;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;


public class MapState extends GameState {
    private CompositeSprite sprite;
    private Map map;

    private final Vector2D ROOM_DISPLAY_SIZE = new Vector2D(64, 64);

    public MapState(Map map, GameContext gameContext, GameState lastState) {
        super(gameContext, lastState);
        this.map = map;
        keyEventList.add(controller);
        mouseEventList.add(controller);
        computeMinimap();
    }

    public void computeMinimap(){


        spriteList = new SpriteHandler();
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        spriteList.addHandlerToGround(Ground.BACKGROUND, lastState.getSpriteList());
        spriteList.addSpriteTo(Ground.BACKGROUND, new Square(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));


        Vector2D mapSize = new Vector2D(ROOM_DISPLAY_SIZE.x * map.getMaxSize(), ROOM_DISPLAY_SIZE.y * map.getMaxSize());
        sprite = new CompositeSprite(mapSize.divideBy(new Vector2D(-2,-2)));

        Vector2D size = mapSize.add(new Vector2D(100,100));
        Vector2D pos = new Vector2D(size).divideBy(new Vector2D(-2, -2));
        Sprite border = new Square(pos.subtract(new Vector2D(10,10)), size.add(new Vector2D(20,20)), new Color(1,1,1,1));
        Sprite centerSquare = new Square(pos, size, new Color(0,0,0,1));

        spriteList.addSpriteTo(Ground.GROUND, border);
        spriteList.addSpriteTo(Ground.GROUND, centerSquare);

        for (int y = 0; y < map.getMapSize().y; ++y) {
            for (int x = 0; x < map.getMapSize().x; ++x) {
                if (map.getRooms().get(x).get(y) != null) {
                    sprite.add(map.getRooms().get(x).get(y).getMinimapSprite(ROOM_DISPLAY_SIZE.multiply(new Vector2D(x, y)), ROOM_DISPLAY_SIZE));
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
        computeMinimap();
        paint();
    }

    private void paint() {
        gameContext.gameWindow.paintAll(spriteList);
    }
}