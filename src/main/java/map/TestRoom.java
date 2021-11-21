package map;

import collision.CollisionType;
import javafx.scene.paint.Color;
import utilities.Vector2D;

import java.util.List;

public class TestRoom extends Room{
    boolean exitTop;
    boolean exitRight;
    boolean exitBottom;
    boolean exitLeft;
    public TestRoom (boolean exitTop, boolean exitRight, boolean exitBottom, boolean exitLeft) {
        this.exitTop = exitTop;
        this.exitRight = exitRight;
        this.exitBottom = exitBottom;
        this.exitLeft = exitLeft;
        generate();
    }

    @Override
    protected void generateRoom() {
        setWalls();
        setFloor();
    }

    private void setWalls() {
        for (int x = 0; x < ROOM_SIZE.x; ++x) {
            tiles.get(x).set(0, new Wall(
                    new Vector2D(x, 0), TILE_SIZE)
            );
            tiles.get(x).set((int) ROOM_SIZE.y -1, new Wall(
                    new Vector2D(x, (int) ROOM_SIZE.y -1), TILE_SIZE)
            );
        }
        for (int y = 0; y < ROOM_SIZE.y; ++y) {
            tiles.get(0).set(y, new Wall(
                    new Vector2D(0, y), TILE_SIZE)
            );
            tiles.get((int) ROOM_SIZE.x-1).set(y, new Wall(
                    new Vector2D((int) ROOM_SIZE.x -1, y), TILE_SIZE)
            );
        }
        if (exitTop) {
            setDoor(new Vector2D(0, ROOM_SIZE.x/2-1), CollisionType.UP);
            setDoor(new Vector2D(0, ROOM_SIZE.x/2), CollisionType.UP);
        }
        if (exitBottom) {
            setDoor(new Vector2D(ROOM_SIZE.y-1, ROOM_SIZE.x/2-1), CollisionType.DOWN);
            setDoor(new Vector2D(ROOM_SIZE.y-1, ROOM_SIZE.x/2), CollisionType.DOWN);}
        if (exitLeft) {
            setDoor(new Vector2D(ROOM_SIZE.y/2-1, 0), CollisionType.LEFT);
            setDoor(new Vector2D(ROOM_SIZE.y/2, 0), CollisionType.LEFT);
        }
        if (exitRight) {
            setDoor(new Vector2D(ROOM_SIZE.y/2-1, ROOM_SIZE.x-1), CollisionType.LEFT);
            setDoor(new Vector2D(ROOM_SIZE.y/2, ROOM_SIZE.x-1), CollisionType.LEFT);
        }
    }

    private void setDoor(Vector2D position, CollisionType collisionType) {
        tiles.get((int) position.x).set((int) position.y, new Door(position, TILE_SIZE, collisionType));
    }


    private void setFloor() {
        for (int y = 1; y < ROOM_SIZE.y-1; ++y) {
            for (int x = 1; x < ROOM_SIZE.x-1; ++x) {
                tiles.get(x).set(y, new Floor(
                        new Vector2D(x, y), TILE_SIZE
                ));
            }
        }
    }

    @Override
    public Color getMinimapColor() {
        return Color.BLUEVIOLET;
    }
}
