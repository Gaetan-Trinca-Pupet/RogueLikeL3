package map;

import sprite.Sprite;
import utilities.Vector2D;

public interface Tile {
    public Vector2D getPosition();
    public Sprite getSprite();
    public boolean hasCollisions();
}
