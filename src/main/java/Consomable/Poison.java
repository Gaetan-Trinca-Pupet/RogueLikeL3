package Consomable;

import Inventory.Consomable;
import entity.Creature;
import sprite.ImageSprite;
import utilities.Vector2D;

public class Poison extends Consomable {

    public Poison(){
        size = new Vector2D(40,60);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/consomable/poison.png", sprite), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "-???";
    }

    @Override
    public void use(Creature creature){
        creature.addToLife(-20);
    }
}
