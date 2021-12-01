package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Lion extends Fightable {
    public Lion(){
        force = 50;
        life = 200;
        defense = 15;
        speed = 10;
        size = new Vector2D(150,150);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/lion.png", this), new Vector2D(), size);
    }
}
