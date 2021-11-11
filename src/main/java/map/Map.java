package map;

import sprite.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<List<Room>> rooms;
    private Vector2D currentRoomPosition;

    public Map() {
        rooms = new ArrayList<>();
        rooms.add(new ArrayList<>());
        rooms.get(0).add(new Room());
        currentRoomPosition = new Vector2D(0, 0);
    }

    public Sprite getSprite() {
        return rooms.get((int) currentRoomPosition.x).get((int) currentRoomPosition.y).getSprite();
    }
}
