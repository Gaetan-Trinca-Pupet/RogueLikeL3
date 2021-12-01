package equipment;

import Inventory.Equipment;
import sprite.ImageSprite;
import utilities.Vector2D;

public class Armor extends Equipment {
    public Armor(){
        super(50,-10,10);
        size = new Vector2D(70,70);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/equipment/armor.png", this), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "+50 HP\n-10 ATK\n+10 DEF";
    }
}
