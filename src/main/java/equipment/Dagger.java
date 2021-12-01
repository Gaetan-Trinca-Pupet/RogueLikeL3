package equipment;

import Inventory.Equipment;
import sprite.ImageSprite;
import utilities.Vector2D;

public class Dagger extends Equipment {
    public Dagger(){
        super(0,75,0);
        size = new Vector2D(70,70);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/equipment/dagger.png", this), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "+75 ATK";
    }
}
