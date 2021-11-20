package gameComponents;

import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import utilities.Updatable;
import windowManager.GameWindow;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements Updatable, MouseEventManager, KeyEventManager {
    protected GameContext gameContext;
    protected GameState lastState;

    protected MouseAndKeyboardController controller;

    protected List<Updatable> updatableList;
    protected List<KeyEventManager> keyEventList;
    protected List<MouseEventManager> mouseEventList;

    protected SpriteHandler spriteList;

    public GameState(GameContext gameContext, GameState gameState){
        this.gameContext = gameContext;
        this.lastState = gameState;

        updatableList = new ArrayList<>();
        keyEventList = new ArrayList<>();
        mouseEventList = new ArrayList<>();

        spriteList = new SpriteHandler();

        setController(new MouseAndKeyboardController());
    }

    public GameState(GameContext gameContext){
        this(gameContext, null);
    }

public interface GameState extends Updatable, MouseEventManager, KeyEventManager {
}
