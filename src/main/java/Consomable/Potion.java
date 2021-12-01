package Consomable;

import Inventory.Consomable;
import entity.Creature;
import sprite.ImageSprite;
import utilities.Vector2D;

public class Potion extends Consomable {

    public Potion(){
        size = new Vector2D(40,60);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/consomable/potion.png", sprite), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "+100HP";
    }

    @Override
    public void use(Creature creature){
        creature.addToLife(100);
    }
}
