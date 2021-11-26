package entity;

import Controller.MouseAndKeyboardController;
import Controller.Action;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import collision.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import map.Map;
import sprite.ImageSprite;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.*;

public class Player extends Creature implements KeyEventManager, MouseEventManager {
    private MouseAndKeyboardController controller;
    
    private final float speed = 200;
    private Map map;

    public Player(MouseAndKeyboardController controller, Map map){
        super(100, 50, 5);
        this.controller = controller;
        this.map = map;

        size = new Vector2D(30,30);
        hitBox = new SquareCollision(position, size);

        Image image = imageManager.getRessource("file:src/resources/character/player.png", this);
        float sizeDivider = (float) (Math.min(image.getHeight(), image.getWidth()) / size.x);
        this.size = new Vector2D(image.getWidth() / sizeDivider, image.getWidth() / sizeDivider);
        sprite = new ImageSprite(image, position, this.size);
        addSpriteTo(Ground.FOREGROUND);
    }


    @Override
    public void setPosition(Vector2D position){
        this.position = position;
        sprite.setPosition(this.position);
        hitBox.setPosition(this.position);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        move(event);
    }

    private void move(TimeEvent event) {
        //Collision collision = new CircleCollision(position.add(new Vector2D(facing.x, facing.y).multiply(new Vector2D(30,30))),20);

        Vector2D translation = new Vector2D(controller.getXTiltLeftJoystick() * speed * event.getDeltaTime(), controller.getYTiltLeftJoystick() * speed * event.getDeltaTime());
        Vector2D lastPos = new Vector2D(position);
        translate(translation);

        List<Collidable> collidables = map.getCollidables();
        for(Collidable collidable : collidables) {
            if( ! hitBox.intersect(collidable.getHitbox())) continue;
            CollisionType collisionType = collidable.collide();

            if(collisionType == CollisionType.BLOCK) setPosition(lastPos);
            if(collisionType == CollisionType.EXIT_BOTTOM) setPosition(map.moveRoom(Vector2D.BOTTOM));
            if(collisionType == CollisionType.EXIT_LEFT) setPosition(map.moveRoom(Vector2D.LEFT));
            if(collisionType == CollisionType.EXIT_RIGHT) setPosition(map.moveRoom(Vector2D.RIGHT));
            if(collisionType == CollisionType.EXIT_TOP) setPosition(map.moveRoom(Vector2D.TOP));
        }

    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(controller.keyCodeForAction(Action.INTERACT) == event.getCode()){
                checkInteraction();
            }
            if (controller.getXTiltLeftJoystick() != 0 || controller.getYTiltLeftJoystick() != 0) {
                facing.x = controller.getXTiltLeftJoystick();
                facing.y = controller.getYTiltLeftJoystick();
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