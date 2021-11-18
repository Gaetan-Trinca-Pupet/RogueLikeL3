package map;

import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import utilities.Vector2D;

public class Wall implements Tile{
    private Sprite sprite;
    private Vector2D position;

    public Wall (Vector2D position, Vector2D size) {
        this.position = position;
        sprite = ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 1, 11, position.multiply(size), size);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public boolean hasCollisions() {
        return true;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }
}
