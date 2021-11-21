package map;

import collision.Collision;
import collision.CollisionType;
import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import utilities.Vector2D;

public class Floor extends Tile {
    private Sprite sprite;
    private Vector2D position;

    public Floor(Vector2D position, Vector2D size) {
        super(position, size, ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 9, 2, position.multiply(size), size));
    }

    @Override
    public CollisionType collide() {
        return CollisionType.NONE;
    }
}
