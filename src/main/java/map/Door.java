package map;

import collision.CollisionType;
import sprite.ImageSpriteFromTilemapFactory;
import utilities.Vector2D;

public class Door extends Tile{
    CollisionType collisionType;
    public Door(Vector2D position, Vector2D size, CollisionType collisionType) {
        super(position, size, ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 9, 2, position.multiply(size), size));
        this.collisionType = collisionType;
    }

    @Override
    public CollisionType collide() {
        return collisionType;
    }
}
