package map;

import javafx.scene.paint.Color;
import sprite.ImageSpriteFromTilemapFactory;
import sprite.Sprite;
import test.Square;
import utilities.Vector2D;

public class Tile {
    private Sprite sprite;

    public Tile(Vector2D position, Vector2D size) {
        sprite = ImageSpriteFromTilemapFactory.construct("map/roguelikeDungeon_transparent.png", 9, 2, position.multiply(size), size);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
