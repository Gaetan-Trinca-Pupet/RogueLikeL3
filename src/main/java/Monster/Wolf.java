package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class Wolf extends Fightable {
    public Wolf(){
        force = 10;
        life = 50;
        defense = 5;
        speed = 150;
        size = new Vector2D(70,60);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/wolf.png", this), new Vector2D(), size);
    }
}
