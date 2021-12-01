package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Goku extends Fightable {
    public Goku(){
        force = 1;
        life = 100;
        defense = 1;
        speed = 200;
        size = new Vector2D(80,150);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/goku.png", this), new Vector2D(), size);
    }
}
