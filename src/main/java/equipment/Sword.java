package equipment;

import Inventory.Equipment;
import javafx.scene.image.Image;
import sprite.ImageSprite;
import utilities.Vector2D;

public class Sword extends Equipment {
    public Sword(){
        super(0,75,0);
        size = new Vector2D(30,30);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/character/test/test2.png", this), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "Add 75 atk";
    }
}