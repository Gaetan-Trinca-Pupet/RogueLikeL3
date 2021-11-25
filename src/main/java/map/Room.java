package map;

import collision.Collidable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sprite.Sprite;
import sprite.CompositeSprite;
import test.Square;
import utilities.Grid;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Room{
    protected Grid<Tile> tiles;
    private CompositeSprite sprite;
    private List<Collidable> collidables;
    private Vector2D position;

    protected final Vector2D ROOM_SIZE = new Vector2D(16, 16);
    protected final Vector2D TILE_SIZE = new Vector2D(32, 32);
    protected final Vector2D SPRITE_POSITION = TILE_SIZE.multiply(ROOM_SIZE).divideBy(new Vector2D(-2, -2));

    protected void generate() {
        sprite = new CompositeSprite(SPRITE_POSITION);
        tiles = new Grid<>((int) ROOM_SIZE.x, (int) ROOM_SIZE.y);
        collidables = new ArrayList<>();
        generateRoom();
        generateSprite();
        for (Tile tile : tiles) collidables.add(tile);
    }

    protected Vector2D getTilePxPosition(Vector2D position) {
        return position.multiply(TILE_SIZE).add(this.position);
    }

    protected abstract void generateRoom();

    public abstract Sprite getMinimapSprite(Vector2D position, Vector2D size);

    private void generateSprite() {
        sprite.clear();
        for (int y = 0; y < tiles.getSizeWidth(); ++y) {
            for (int x = 0; x < tiles.getSizeHeight(); ++x) {
                sprite.add(tiles.get(y).get(x).getSprite());
            }
        }
    }

    public abstract void addExit(Vector2D direction);

    public Sprite getSprite() {
        return sprite;
    }

    public List<Collidable> getCollidables() {
        return collidables;
    }
}
