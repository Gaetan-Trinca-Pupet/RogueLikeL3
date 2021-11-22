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
    private ArrayList<Interactable> interactableList;
    
    private final float speed = 75;
    private Vector2D facing = new Vector2D();

    public Player(MouseAndKeyboardController controller){
        super(100, 50, 5);
        this.controller = controller;
        this.interactableList = new ArrayList<Interactable>();

        size = new Vector2D(60,60);
        hitBox = new CircleCollision(position, (long) size.x);

        Image image = new Image("file:src/resources/character/test/test.png");
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

    public void addInteraction(Interactable... interactables){
        interactableList.addAll(of(interactables));
    }

    public void removeInteraction(Interactable... interactables){
        interactableList.removeAll(of(interactables));
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

    public void checkInteraction(){
        Collision collision = new CircleCollision(position.add(new Vector2D(facing.x, facing.y).multiply(new Vector2D(30,30))),20);
        ArrayList<Interactable> interactables = new ArrayList<>(interactableList);
        for(Interactable interactable : interactables)
            if(collision.intersect(interactable.hitBox)) {
                interactable.interact(this);
                break;
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
    public void interact(Player player) {
    }
}