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

public class TestRoom extends Room{
    Map <Vector2D, Boolean> exits;
    Map <Vector2D, CollisionType> collisionTypes;
    public TestRoom () {
        exits = new HashMap<>();
        collisionTypes = new HashMap<>();
        collisionTypes.put(new Vector2D(0, -1), CollisionType.EXIT_TOP);
        collisionTypes.put(new Vector2D(1, 0), CollisionType.EXIT_RIGHT);
        collisionTypes.put(new Vector2D(0, 1), CollisionType.EXIT_BOTTOM);
        collisionTypes.put(new Vector2D(-1, 0), CollisionType.EXIT_LEFT);
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
        setFloor();
    }

    private void setWalls() {
        for (int x = 0; x < ROOM_SIZE.x; ++x) {
            tiles.get(x).set(0, new Wall(
                    new Vector2D(x, 0), TILE_SIZE, SPRITE_POSITION)
            );
            tiles.get(x).set((int) ROOM_SIZE.y -1, new Wall(
                    new Vector2D(x, (int) ROOM_SIZE.y -1), TILE_SIZE, SPRITE_POSITION)
            );
        }
        for (int y = 0; y < ROOM_SIZE.y; ++y) {
            tiles.get(0).set(y, new Wall(
                    new Vector2D(0, y), TILE_SIZE, SPRITE_POSITION)
            );
            tiles.get((int) ROOM_SIZE.x-1).set(y, new Wall(
                    new Vector2D((int) ROOM_SIZE.x -1, y), TILE_SIZE, SPRITE_POSITION)
            );
        }
    }

    private void setFloor() {
        for (int y = 1; y < ROOM_SIZE.y-1; ++y) {
            for (int x = 1; x < ROOM_SIZE.x-1; ++x) {
                tiles.get(x).set(y, new Floor(
                        new Vector2D(x, y), TILE_SIZE, SPRITE_POSITION
                ));
            }
        }
    }

    public void addExit(Vector2D direction) {
        if (direction == Vector2D.TOP) {
            setDoor(new Vector2D(0, ROOM_SIZE.x / 2 - 1), CollisionType.EXIT_TOP);
            setDoor(new Vector2D(0, ROOM_SIZE.x / 2), CollisionType.EXIT_TOP);
        } else if (direction == Vector2D.RIGHT) {
            setDoor(new Vector2D(ROOM_SIZE.y / 2 - 1, ROOM_SIZE.x - 1), CollisionType.EXIT_RIGHT);
            setDoor(new Vector2D(ROOM_SIZE.y / 2, ROOM_SIZE.x - 1), CollisionType.EXIT_RIGHT);
        } else if (direction == Vector2D.BOTTOM) {
            setDoor(new Vector2D(ROOM_SIZE.y - 1, ROOM_SIZE.x / 2 - 1), CollisionType.EXIT_BOTTOM);
            setDoor(new Vector2D(ROOM_SIZE.y - 1, ROOM_SIZE.x / 2), CollisionType.EXIT_BOTTOM);
        } else if (direction == Vector2D.LEFT) {
            setDoor(new Vector2D(ROOM_SIZE.y / 2 - 1, 0), CollisionType.EXIT_LEFT);
            setDoor(new Vector2D(ROOM_SIZE.y / 2, 0), CollisionType.EXIT_LEFT);
        }
        exits.put(direction, true);
        System.out.println(exits);

    }

    private void setDoor(Vector2D position, CollisionType collisionType) {
        tiles.set((int) position.x, (int) position.y, new Door(position, TILE_SIZE, collisionType, SPRITE_POSITION));
        tiles.get((int) position.x).set((int) position.y, new Door(position, TILE_SIZE, collisionType, SPRITE_POSITION));
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
