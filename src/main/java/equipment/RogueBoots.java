package equipment;

import Inventory.Equipment;
import javafx.scene.image.Image;
import sprite.ImageSprite;
import utilities.Vector2D;

public class RogueBoots extends Equipment {
    public RogueBoots(){
        super(-50,500,-10);
        size = new Vector2D(50,50);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/equipment/rogueBoots.png", this), new Vector2D(), size);
    }

    @Override
    public String info() {
        return "-50 HP\n+500 ATK\n-10 DEF";
    }
}
