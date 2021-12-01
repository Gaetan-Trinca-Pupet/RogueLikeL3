package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Mice extends Fightable {
    public Mice(){
        force = 5;
        life = 50;
        defense = 1;
        speed = 250;
        size = new Vector2D(50,50);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/mice.png", this), new Vector2D(), size);
    }
}
