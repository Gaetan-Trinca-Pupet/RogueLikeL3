package Controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;

public class MouseAndKeyboardController extends RogueLikeController{

    private enum Direction{
        UP, LEFT, DOWN, RIGHT
    }

    private HashMap<KeyCode, Direction> directionKey;
    private HashMap<Direction, Boolean> directionKeyPressed;



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

    public MouseAndKeyboardController(Vector2D centerScreen, KeyCode up, KeyCode left, KeyCode down, KeyCode right, MouseButton rightMouse, MouseButton leftMouse){
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

        mouse = new Mouse(leftMouse, rightMouse);
    }

    public MouseAndKeyboardController(){
        this(new Vector2D(0,0), KeyCode.Z, KeyCode.Q, KeyCode.S, KeyCode.D, MouseButton.PRIMARY, MouseButton.SECONDARY);
    }

    public void setCenterScreen(Vector2D centerScreen){
        this.centerScreen = centerScreen;
    }

    public void handleKeyEvent(KeyEvent event){
        if(event.getEventType() == KeyEvent.KEY_PRESSED)
            if(directionKey.containsKey(event.getCode()))
                directionKeyPressed.replace(directionKey.get(event.getCode()), true);

        if(event.getEventType() == KeyEvent.KEY_RELEASED)
            if (directionKey.containsKey(event.getCode()))
                directionKeyPressed.replace(directionKey.get(event.getCode()), false);


        computeButtons();
        computeJoystick();
    }

    public void handleMouseEvent(MouseEvent event){
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
            if(mouse.buttonMap.containsKey(event.getButton()))
                mouse.buttonPressed.replace(mouse.buttonMap.get(event.getButton()), true);

        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
            if(mouse.buttonMap.containsKey(event.getButton()))
                mouse.buttonPressed.replace(mouse.buttonMap.get(event.getButton()), false);

        if(event.getEventType() == MouseEvent.MOUSE_MOVED)
        {
            mouse.position.x = event.getX();
            mouse.position.y = event.getY();
        }

        computeButtons();
        computeJoystick();
    }

    @Override
    protected void computeButtons() {
        pressedButtons.replace(Touche.ATTACK, mouse.buttonPressed.get(Mouse.ButtonMouse.LEFT));
        pressedButtons.replace(Touche.INTERACT, mouse.buttonPressed.get(Mouse.ButtonMouse.RIGHT));
    }

    @Override
    protected void computeJoystick() {
        leftJoystick.xTilt = 0;
        leftJoystick.yTilt = 0;

        if(directionKeyPressed.get(Direction.LEFT)) leftJoystick.xTilt -= 1;
        if(directionKeyPressed.get(Direction.RIGHT)) leftJoystick.xTilt += 1;
        if(directionKeyPressed.get(Direction.UP)) leftJoystick.yTilt -= 1;
        if(directionKeyPressed.get(Direction.DOWN)) leftJoystick.yTilt += 1;
    }
}
