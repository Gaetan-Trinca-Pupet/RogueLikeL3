package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Toad extends Fightable {
    public Toad(){
        force = 15;
        life = 100;
        defense = 15;
        speed = 40;
        size = new Vector2D(150,150);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/toad.png", this), new Vector2D(), size);
    }
}
