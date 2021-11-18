package entity;

import Inventory.Stockable;
import collision.SquareCollision;
import javafx.scene.paint.Color;
import sprite.ImageSprite;
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

    public void interact(Player player){
        self.addSelfToInventory(player.getInventory());
        player.removeInteraction(this);
        removeSpriteTo(Ground.GROUND);
    }

    @Override
    public void update() {

    }
}
