package Consomable;

import Inventory.Consomable;
import entity.Creature;
import sprite.ImageSprite;
import utilities.Vector2D;

public class Sandwich extends Consomable {

    public Sandwich(){
        size = new Vector2D(40,60);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/consomable/sandwich.png", sprite), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "+30HP";
    }

    @Override
    public void use(Creature creature){
        creature.addToLife(30);
    }
}
