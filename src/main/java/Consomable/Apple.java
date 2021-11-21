package Consomable;

import Inventory.Consomable;
import entity.Creature;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sprite.ImageSprite;
import sprite.Sprite;
import test.Square;
import utilities.Vector2D;

public class Apple extends Consomable {

    public Apple(){
        size = new Vector2D(30,30);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/consomable/apple.png", sprite), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "+20HP";
    }

    @Override
    public void use(Creature creature){
        creature.addToLife(20);
    }
}
