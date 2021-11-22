package gameComponents;

import Controller.MouseAndKeyboardController;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import test.TimeEvent;
import utilities.UpdateOnTimeEvent;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements UpdateOnTimeEvent, MouseEventManager, KeyEventManager {
    protected GameContext gameContext;
    protected GameState lastState;

    protected MouseAndKeyboardController controller;

    protected List<UpdateOnTimeEvent> updatableList;
    protected List<KeyEventManager> keyEventList;
    protected List<MouseEventManager> mouseEventList;

    protected SpriteHandler spriteList;

    public GameState(GameContext gameContext, GameState gameState, MouseAndKeyboardController controller){
        this.controller = controller;
        this.gameContext = gameContext;
        this.lastState = gameState;

        updatableList = new ArrayList<>();
        keyEventList = new ArrayList<>();
        mouseEventList = new ArrayList<>();

        spriteList = new SpriteHandler();

        setController(new MouseAndKeyboardController());
    }

    public GameState(GameContext gameContext, GameState gameState){
        this(gameContext, gameState, new MouseAndKeyboardController());
    }

    public GameState(GameContext gameContext){
        this(gameContext, null);
    }

    public GameState(){
        this(null);
    }

    public void setController(MouseAndKeyboardController controller){
        keyEventList.remove(this.controller);
        mouseEventList.remove(this.controller);

        this.controller = controller;
        if(gameContext != null) this.controller.setCenterScreen(gameContext.gameWindow.getScreenCenter());

        keyEventList.add(this.controller);
        mouseEventList.add(this.controller);
    }

    public void backToLastContext(){
        gameContext.setState(lastState);
    }

    public SpriteHandler getSpriteList(){
        SpriteHandler list = new SpriteHandler();
        if(lastState != null)
            list.addHandlerToGround(Ground.GROUND, lastState.getSpriteList());
        list.addHandlerToGround(Ground.GROUND, spriteList);
        return list;
    }

    public void keyboardEventHandler(KeyEvent event){
        controller.keyboardEvent(event);
        this.keyboardEvent(event);
    }

    public void mouseEventHandler(MouseEvent event){
        controller.mouseEvent(event);
        this.mouseEvent(event);
    }

    public void timeEventHandler(TimeEvent event){
        this.updateOnTimeEvent(event);
    }
}
