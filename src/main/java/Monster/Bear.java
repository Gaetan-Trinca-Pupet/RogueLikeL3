package Monster;

import entity.Monster;
import sprite.ImageSprite;
import test.Square;
import utilities.Vector2D;
import windowManager.Ground;

public class Bear extends Fightable {
    public Bear(){
        force = 30;
        life = 100;
        defense = 10;
        size = new Vector2D(100,150);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/bear.png", this), new Vector2D(), size);
    }
}
