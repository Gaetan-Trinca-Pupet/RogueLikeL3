package Controller;

import java.util.HashMap;



public abstract class RogueLikeController {
    protected class Joystick{
        public float xTilt;
        public float yTilt;

        Joystick(){
            xTilt = 0;
            yTilt = 0;
        }
    }

    protected Joystick leftJoystick;

    protected HashMap<Touche, Boolean> pressedButtons;

    RogueLikeController(){
        leftJoystick = new Joystick();
        pressedButtons = new HashMap<>();
        for(Touche value : Touche.values())
            pressedButtons.put(value, false);
    }

    public double getXTiltLeftJoystick(){
        return leftJoystick.xTilt;
    }

    public float getYTiltLeftJoystick(){
        return leftJoystick.yTilt;
    }

    public boolean isPressed(Touche button){
        return pressedButtons.get(button);
    }

    protected abstract void computeButtons();
    protected abstract void computeJoystick();
}
