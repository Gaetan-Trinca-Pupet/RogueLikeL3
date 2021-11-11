package map;

import sprite.Sprite;
import utilities.CompositeSprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<List<Tile>> tiles;
    private CompositeSprite sprite;

    private final Vector2D ROOM_SIZE = new Vector2D(16, 16);
    private final Vector2D TILE_SIZE = new Vector2D(32, 32);
    private final Vector2D SPRITE_POSITION = new Vector2D(-284, -224);

    public Room() {
        sprite = new CompositeSprite(SPRITE_POSITION);
        tiles = new ArrayList<>();
        tiles.add(new ArrayList<>());
        for (int y = 0; y < ROOM_SIZE.y; ++y) {
            tiles.add(new ArrayList<>());
            for (int x = 0; x < ROOM_SIZE.x; ++x) {
                tiles.get(y).add(new Tile(new Vector2D(x, y), TILE_SIZE));
                sprite.add(tiles.get(y).get(x).getSprite());
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
