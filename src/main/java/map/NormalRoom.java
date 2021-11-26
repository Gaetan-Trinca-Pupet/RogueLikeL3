package map;

import collision.CollisionType;
import javafx.scene.paint.Color;
import sprite.CompositeSprite;
import sprite.Sprite;
import test.Square;
import utilities.Vector2D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NormalRoom extends Room{
    Map <Vector2D, Boolean> exits;
    public NormalRoom() {
        exits = new HashMap<>();
        generate();
    }

    @Override
    protected void generateRoom() {
        exits = new HashMap<>();
        exits.put(Vector2D.RIGHT, false);
        exits.put(Vector2D.BOTTOM, false);
        exits.put(Vector2D.LEFT, false);
        exits.put(Vector2D.TOP, false);
        setWalls();
        setFloors();
    }

    private void setWalls() {
        for (int x = 0; x < ROOM_SIZE.x; ++x) {
            setWall(x, 0);
            setWall(x, (int) ROOM_SIZE.y -1);
        }
        for (int y = 0; y < ROOM_SIZE.y; ++y) {
            setWall(0, y);
            setWall((int) ROOM_SIZE.x-1, y);
        }
    }

    private void setFloors() {
        for (int y = 1; y < ROOM_SIZE.y-1; ++y) {
            for (int x = 1; x < ROOM_SIZE.x-1; ++x) {
                setFloor(x, y);
            }
        }
    }

    public void addExit(Vector2D direction) {
        exits.put(direction, true);
        if (direction == Vector2D.TOP) {
            setDoor(0, (int) (ROOM_SIZE.x / 2) - 1, CollisionType.EXIT_TOP);
            setDoor(0, (int) (ROOM_SIZE.x / 2), CollisionType.EXIT_TOP);
        } else if (direction == Vector2D.RIGHT) {
            setDoor((int) (ROOM_SIZE.y / 2) - 1, (int) ROOM_SIZE.x - 1, CollisionType.EXIT_RIGHT);
            setDoor((int) (ROOM_SIZE.y / 2), (int) ROOM_SIZE.x - 1, CollisionType.EXIT_RIGHT);
        } else if (direction == Vector2D.BOTTOM) {
            setDoor((int) ROOM_SIZE.y - 1, (int) (ROOM_SIZE.x / 2) - 1, CollisionType.EXIT_BOTTOM);
            setDoor((int) ROOM_SIZE.y - 1, (int) (ROOM_SIZE.x / 2), CollisionType.EXIT_BOTTOM);
        } else if (direction == Vector2D.LEFT) {
            setDoor((int) (ROOM_SIZE.y / 2) - 1, 0, CollisionType.EXIT_LEFT);
            setDoor((int) (ROOM_SIZE.y / 2), 0, CollisionType.EXIT_LEFT);
        }
        actualizeSprite();
    }

    private void setFloor (int x, int y) {
        tiles.set(x, y, new Floor(new Vector2D(x, y), TILE_SIZE, SPRITE_POSITION));
    }

    private void setWall (int x, int y) {
        Tile newTile = new Wall(new Vector2D(x, y), TILE_SIZE, SPRITE_POSITION);
        tiles.set(x, y, newTile);
        getCollidables().add(newTile);
    }

    private void setDoor(int x, int y, CollisionType collisionType) {
        Tile newTile = new Door(new Vector2D(x, y), TILE_SIZE, collisionType, SPRITE_POSITION);
        tiles.set(x, y, newTile);
        getCollidables().add(newTile);
    }

    @Override
    public Sprite getMinimapSprite(Vector2D position, Vector2D size) {
        Vector2D vectorThree = new Vector2D(3, 3);
        CompositeSprite sprite = new CompositeSprite(position);
        sprite.add(new Square(new Vector2D(), size, Color.POWDERBLUE));

        sprite.add(new Square(size.divideBy(vectorThree), size.divideBy(vectorThree), Color.BLACK));
        for (Map.Entry<Vector2D, Boolean> exit : exits.entrySet()) {
            if (exit.getValue()) {
                sprite.add(new Square(size.multiply(exit.getKey().add(new Vector2D(1, 1))).divideBy(vectorThree), size.divideBy(vectorThree), Color.BLACK));
            }
        }
        return sprite;
    }
}