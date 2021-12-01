package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Bear extends Fightable {
    public Bear(){
        force = 100;
        life = 150;
        defense = 5;
        speed = 50;
        size = new Vector2D(100,150);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/bear.png", this), new Vector2D(), size);
    }
}
