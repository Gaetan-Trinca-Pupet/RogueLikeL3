package Monster;

import entity.Creature;
import ressourceManager.ImageManager;
import sprite.Sprite;
import utilities.Vector2D;

public class Fightable {
    static protected ImageManager imageManager = new ImageManager();

    protected Sprite sprite;
    protected Vector2D size;

    protected int life;
    protected int force;
    protected int defense;

    public Sprite getSprite(){
        return sprite;
    }

    public Vector2D getSize(){
        return size;
    }

    public int getLife() {
        return life;
    }

    public int getForce() {
        return force;
    }

    public int getDefense() {
        return defense;
    }
}
