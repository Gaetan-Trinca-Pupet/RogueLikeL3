package map;

import collision.CollisionType;
import javafx.scene.image.Image;
import sprite.ImageSprite;
import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import utilities.Vector2D;

public class Trap extends Tile {
    private Sprite sprite;
    private Vector2D position;
    Vector2D size;
    private int damage;
    private boolean activated = false;

    public Trap(Vector2D position, Vector2D size, Vector2D parentPosition, int dmg) {
        super(position, size, new ImageSprite(new Image("file:src/resources/traps/spikes1.png"), position.multiply(size), size), parentPosition);
        damage = dmg;
        this.position = position;
        this.size = size;
    }

    public int getDamage() {
        return damage;
    }

    public boolean wasActivated() {
        return activated;
    }

    public void Activate() {
        activated = true;
//        setSprite(ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 6, 0, position.multiply(size), size));
    }

    @Override
    public CollisionType collide() {
        return CollisionType.DAMAGING;
    }
}
