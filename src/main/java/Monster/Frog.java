package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Frog extends Fightable {
    public Frog(){
        force = 5;
        life = 30;
        defense = 15;
        speed = 50;
        size = new Vector2D(40,40);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/frog.png", this), new Vector2D(), size);
    }
}
