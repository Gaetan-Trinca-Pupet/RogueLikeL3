package entity;

import Controller.RogueLikeController;
import Controller.Touche;
import entity.Creature;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Player extends Creature{
    private RogueLikeController controller;
    private ArrayList<Interactable> interactableList;
    private float speed = 5;

    public Player(RogueLikeController controller){
        this.interactableList = new ArrayList<Interactable>();
        this.controller = controller;
    }

    public void addInteraction(Interactable... interactables){
        interactableList.addAll(List.of(interactables));
    }

    public void removeInteraction(Interactable... interactables){
        interactableList.removeAll(List.of(interactables));
    }

    @Override
    public void update() {
        move();
    }

    private void move(){
        translate(new Vector2D(controller.getXTiltLeftJoystick() * speed, controller.getYTiltLeftJoystick() * speed));
    }

    public void checkInteraction(){
        for(Interactable interactable : interactableList)
            interactable.interact(this);
    }
}