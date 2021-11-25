package map;

import collision.Collision;
import collision.CollisionType;
import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import utilities.Vector2D;

public class Floor extends Tile {
    private Sprite sprite;
    private Vector2D position;

    public Floor(Vector2D position, Vector2D size, Vector2D parentPosition) {
        super(position, size, ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 9, 2, position.multiply(size), size), parentPosition);
    }

    @Override
    public CollisionType collide() {
        return CollisionType.NONE;
    }
}
