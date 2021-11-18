package entity;

import Controller.MouseAndKeyboardController;
import Controller.Action;
import EventManager.KeyEventManager;
import EventManager.MouseEventManager;
import Inventory.Inventory;
import collision.CircleCollision;
import collision.Collision;
import collision.SegmentCollision;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sprite.AnimatedSprite;
import sprite.ImageSprite;
import sprite.Sprite;
import utilities.Vector2D;
import windowManager.Ground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.*;
import static java.util.List.*;

public class Player extends Creature implements KeyEventManager, MouseEventManager {
    private MouseAndKeyboardController controller;
    private ArrayList<Interactable> interactableList;
    
    private float speed = 2;
    private Vector2D size;
    private Vector2D facing = new Vector2D();
    private Inventory inventory;

    public Player(MouseAndKeyboardController controller){
        this.controller = controller;
        this.interactableList = new ArrayList<Interactable>();
        this.inventory = new Inventory();

        position = new Vector2D();
        int size = 60;
        hitBox = new CircleCollision(position, size);

        Image image = new Image("file:src/resources/player/player.png");
        float sizeDivider = (float) (Math.min(image.getHeight(), image.getWidth()) / size);
        this.size = new Vector2D(image.getWidth() / sizeDivider, image.getWidth() / sizeDivider);
        sprite = new ImageSprite(image, position.subtract(this.size.divideBy(new Vector2D(2,2))), this.size);
        addSpriteTo(Ground.GROUND);
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

    public Inventory getInventory(){
        return inventory;
    }

    @Override
    public void update() {
        move();
    }

    private void move() {
        Vector2D translate = new Vector2D(controller.getXTiltLeftJoystick() * speed, controller.getYTiltLeftJoystick() * speed);
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