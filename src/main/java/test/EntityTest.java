package test;

import Controller.RogueLikeController;
import entity.Creature;
import entity.Player;

public class EntityTest extends Creature {
    private RogueLikeController controller;

    public EntityTest(RogueLikeController controller){
        this.controller = controller;
    }

    public void update(){
        this.getPosition().x += controller.getXTiltLeftJoystick() * 10;
        this.getPosition().y += controller.getYTiltLeftJoystick() * 10;
    }

    @Override
    public void interact(Player player) {
    }
}
