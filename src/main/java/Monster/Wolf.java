package Monster;

import entity.Monster;
import sprite.ImageSprite;
import test.Square;
import utilities.Vector2D;
import windowManager.Ground;

public class Wolf extends Fightable {
    public Wolf(){
        force = 10;
        life = 50;
        defense = 1;
        size = new Vector2D(60,50);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/wolf.png", this), new Vector2D(), size);
    }
}
