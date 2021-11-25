package map;

import collision.Collision;
import collision.CollisionType;
import collision.SquareCollision;
import entity.Player;
import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import utilities.Vector2D;

public class Wall extends Tile{
    public Wall (Vector2D position, Vector2D size, Vector2D parentPosition) {
        super(position, size, ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 1, 11, position.multiply(size), size), parentPosition);
    }

    @Override
    public CollisionType collide() {
        return CollisionType.BLOCK;
    }
}
