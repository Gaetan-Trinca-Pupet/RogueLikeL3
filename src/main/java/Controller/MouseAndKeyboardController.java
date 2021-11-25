package Controller;

import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import utilities.Vector2D;

import java.security.Key;
import java.util.HashMap;

public class MouseAndKeyboardController extends RogueLikeController implements MouseEventManager, KeyEventManager {
    private enum Direction{
        UP, LEFT, DOWN, RIGHT
    }

    private HashMap<KeyCode, Direction> directionKey;
    private HashMap<Direction, Boolean> directionKeyPressed;

    private HashMap<Action, KeyCode> keyMapping;
    private HashMap<KeyCode, Action> keyAction;



    private class Mouse{
        public enum ButtonMouse{
            LEFT, RIGHT
        }

        public Vector2D position;
        private HashMap<MouseButton, ButtonMouse> buttonMap;
        public HashMap<ButtonMouse, Boolean> buttonPressed;

        public Mouse(MouseButton right, MouseButton left){
            position = new Vector2D();
            buttonMap = new HashMap<>();
            buttonPressed = new HashMap<>();

            buttonMap.put(right, ButtonMouse.RIGHT);
            buttonMap.put(left, ButtonMouse.LEFT);

            for(ButtonMouse buttonMouse : ButtonMouse.values())
                buttonPressed.put(buttonMouse, false);
        }

        public Mouse(){
            this(MouseButton.PRIMARY, MouseButton.SECONDARY);
        }
    }

    public Mouse mouse;

    private Vector2D centerScreen;

    public MouseAndKeyboardController(Vector2D centerScreen, KeyCode up, KeyCode left, KeyCode down, KeyCode right, KeyCode interact, KeyCode inventory, KeyCode escape, KeyCode map, MouseButton leftMouse, MouseButton rightMouse){
        super();
        this.centerScreen = centerScreen;

        directionKey = new HashMap<>();
        directionKey.put(up, Direction.UP);
        directionKey.put(left, Direction.LEFT);
        directionKey.put(down, Direction.DOWN);
        directionKey.put(right, Direction.RIGHT);

        directionKeyPressed = new HashMap<>();
        for(Direction direction : Direction.values())
            directionKeyPressed.put(direction, false);


        keyMapping = new HashMap<>();
        keyMapping.put(Action.INTERACT, interact);
        keyMapping.put(Action.INVENTORY, inventory);
        keyMapping.put(Action.ESCAPE, escape);
        keyMapping.put(Action.MAP, map);

        keyAction = new HashMap<>();
        for(Action action : keyMapping.keySet())
            keyAction.put(keyMapping.get(action), action);

        mouse = new Mouse(leftMouse, rightMouse);
    }

    public MouseAndKeyboardController(){
        this(new Vector2D(0,0), KeyCode.Z, KeyCode.Q, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.I, KeyCode.ESCAPE, KeyCode.M, MouseButton.PRIMARY, MouseButton.SECONDARY);
    }

    public void setCenterScreen(Vector2D centerScreen){
        this.centerScreen = centerScreen;
    }

    public KeyCode keyCodeForAction(Action action){
        return keyMapping.get(action);
    }

    public Vector2D getMousePosition(){
        return mouse.position;
    }

    @Override
    protected void computeButtons() {
        pressedButtons.replace(Action.ATTACK, mouse.buttonPressed.get(Mouse.ButtonMouse.LEFT));
    }

    @Override
    protected void computeJoystick() {
        leftJoystick.xTilt = 0;
        leftJoystick.yTilt = 0;

        if(directionKeyPressed.get(Direction.LEFT)) leftJoystick.xTilt -= 1;
        if(directionKeyPressed.get(Direction.RIGHT)) leftJoystick.xTilt += 1;
        if(directionKeyPressed.get(Direction.UP)) leftJoystick.yTilt -= 1;
        if(directionKeyPressed.get(Direction.DOWN)) leftJoystick.yTilt += 1;

        if(leftJoystick.xTilt != 0 && leftJoystick.yTilt != 0) {
            leftJoystick.xTilt *= Math.sqrt(2) / 2;
            leftJoystick.yTilt *= Math.sqrt(2) / 2;
        }
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (directionKey.containsKey(event.getCode()))
                directionKeyPressed.replace(directionKey.get(event.getCode()), true);

            if (keyAction.containsKey(event.getCode()))
                pressedButtons.replace(keyAction.get(event.getCode()), true);
        }

        if(event.getEventType() == KeyEvent.KEY_RELEASED) {
            if (directionKey.containsKey(event.getCode()))
                directionKeyPressed.replace(directionKey.get(event.getCode()), false);

            if (keyAction.containsKey(event.getCode()))
                pressedButtons.replace(keyAction.get(event.getCode()), false);
        }


        computeButtons();
        computeJoystick();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
            if(mouse.buttonMap.containsKey(event.getButton()))
                mouse.buttonPressed.replace(mouse.buttonMap.get(event.getButton()), true);

        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
            if(mouse.buttonMap.containsKey(event.getButton()))
                mouse.buttonPressed.replace(mouse.buttonMap.get(event.getButton()), false);

        mouse.position.x = event.getX() - centerScreen.x;
        mouse.position.y = event.getY() - centerScreen.y;

        computeButtons();
    }
}
