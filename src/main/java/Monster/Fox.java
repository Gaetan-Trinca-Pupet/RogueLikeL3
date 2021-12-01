package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Fox extends Fightable {
    public Fox(){
        force = 50;
        life = 100;
        defense = 5;
        speed = 75;
        size = new Vector2D(60,60);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/renard.png", this), new Vector2D(), size);
    }
}
