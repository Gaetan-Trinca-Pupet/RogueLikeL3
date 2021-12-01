package Monster;

import sprite.ImageSprite;
import utilities.Vector2D;

public class LandMine extends Fightable {
    public LandMine(){
        force = 400;
        life = 100;
        defense = 1;
        speed = 0;
        size = new Vector2D(50,50);
        sprite = new ImageSprite(imageManager.getRessource("file:src/resources/monster/landmine.png", this), new Vector2D(), size);
    }
}
