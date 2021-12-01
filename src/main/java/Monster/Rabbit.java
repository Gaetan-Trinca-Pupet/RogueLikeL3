package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Rabbit extends Fightable {
    public Rabbit(){
        force = 100;
        life = 50;
        defense = 2;
        speed = 100;
        size = new Vector2D(40,40);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/rabbit.png", this), new Vector2D(), size);
    }
}
