package entity;

import Controller.MouseAndKeyboardController;
import Controller.Action;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import collision.CircleCollision;
import collision.Collision;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sprite.ImageSprite;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;

import java.util.ArrayList;

import static java.util.List.*;

public class Player extends Creature implements KeyEventManager, MouseEventManager {
    private MouseAndKeyboardController controller;
    
    private final float speed = 200;

    public Player(MouseAndKeyboardController controller){
        super(100, 50, 5);
        this.controller = controller;

        size = new Vector2D(60,60);
        hitBox = new CircleCollision(position, (long) size.x);

        Image image = imageManager.getRessource("file:src/resources/character/test/test.png", this);
        float sizeDivider = (float) (Math.min(image.getHeight(), image.getWidth()) / size.x);
        this.size = new Vector2D(image.getWidth() / sizeDivider, image.getWidth() / sizeDivider);
        sprite = new ImageSprite(image, position.subtract(this.size.divideBy(new Vector2D(2,2))), this.size);
        addSpriteTo(Ground.FOREGROUND);
    }


    @Override
    public void setPosition(Vector2D position){
        this.position = position;
        sprite.setPosition(this.position.subtract(size.divideBy(new Vector2D(2,2))));
        hitBox.setPosition(this.position);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        move(event);
    }

    private void move(TimeEvent event) {
        Vector2D translate = new Vector2D(controller.getXTiltLeftJoystick() * speed * event.getDeltaTime(), controller.getYTiltLeftJoystick() * speed * event.getDeltaTime());
        translate(translate);
        translateSprite(translate);
        if (controller.getXTiltLeftJoystick() != 0 || controller.getYTiltLeftJoystick() != 0) {
            facing.x = controller.getXTiltLeftJoystick();
            facing.y = controller.getYTiltLeftJoystick();
        }
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(controller.keyCodeForAction(Action.INTERACT) == event.getCode()){
                checkInteraction();
            }
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    @Override
    public void interact(Creature creature) {
    }
}