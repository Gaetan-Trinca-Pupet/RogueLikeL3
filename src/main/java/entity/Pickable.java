package entity;

import Inventory.Stockable;
import collision.SquareCollision;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;

public class Pickable extends Interactable {

    private Stockable self;

    public Pickable(Stockable self){
        this.self = self;
        position = new Vector2D();
        sprite = self.getSprite();
        sprite.setPosition(position);
        hitBox = new SquareCollision(position, self.getSize());

        addSpriteTo(Ground.GROUND);
    }

    @Override
    public void setPosition(Vector2D position){
        this.position = position;
        sprite.setPosition(this.position);
        hitBox.setPosition(this.position);
    }

    public void interact(Creature creature){
        self.addSelfToInventory(creature.getInventory());
        creature.removeInteraction(this);
        removeSpriteTo(Ground.GROUND);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {

    }
}
