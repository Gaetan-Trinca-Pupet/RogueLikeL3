package map;

import collision.Collidable;
import collision.Collision;
import collision.CollisionType;
import collision.SquareCollision;
import entity.Interactable;
import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import utilities.Vector2D;

public abstract class Tile implements Collidable{
    private Sprite sprite;
    private Vector2D position;
    private Collision collision;

    public Tile (Vector2D position, Vector2D size, Sprite sprite, Vector2D parentPosition) {
        this.position = position;
        this.collision = new SquareCollision(position.multiply(size).add(parentPosition), size);
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
         this.sprite = sprite;
    }

    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Collision getHitbox() {
        return collision;
    }
}
