package gameComponents;

import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import utilities.Updatable;

public interface GameState extends Updatable, MouseEventManager, KeyEventManager {
}
