package test;

import Controller.RogueLikeController;
import Controller.Touche;
import entity.Creature;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;

public class EntityTest extends Creature {
    private RogueLikeController controller;

    public EntityTest(RogueLikeController controller){
        this.controller = controller;
    }

    public void update(){
        this.getPosition().x += controller.getXTiltLeftJoystick() * 10;
        this.getPosition().y += controller.getYTiltLeftJoystick() * 10;
    }
}
