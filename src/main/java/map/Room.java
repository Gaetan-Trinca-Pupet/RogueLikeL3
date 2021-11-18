package map;

import sprite.Sprite;
import sprite.CompositeSprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Room {
    protected List<List<Tile>> tiles;
    private CompositeSprite sprite;

    protected final Vector2D ROOM_SIZE = new Vector2D(16, 16);
    protected final Vector2D TILE_SIZE = new Vector2D(32, 32);
    private final Vector2D SPRITE_POSITION = new Vector2D(-284, -224);

    public Room() {
        sprite = new CompositeSprite(SPRITE_POSITION);
        tiles = new ArrayList<>();
        for (int y = 0; y < ROOM_SIZE.y; ++y) {
            tiles.add(new ArrayList<>());
            for (int x = 0; x < ROOM_SIZE.x; ++x) {
                tiles.get(y).add(null);
            }
        }
        generateRoom();
        generateSprite();
    }

    protected Vector2D getTilePxPosition(Vector2D position) {
        return position.multiply(TILE_SIZE);
    }

    protected abstract void generateRoom();

    private void generateSprite() {
        for (int y = 0; y < ROOM_SIZE.y; ++y) {
            for (int x = 0; x < ROOM_SIZE.x; ++x) {
                sprite.add(tiles.get(y).get(x).getSprite());
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
