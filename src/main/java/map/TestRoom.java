package map;

import javafx.scene.paint.Color;
import utilities.Vector2D;

import java.util.List;

public class TestRoom extends Room{
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
