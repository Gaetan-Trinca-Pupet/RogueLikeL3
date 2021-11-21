package map;

import sprite.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<List<Room>> rooms;
    private Vector2D currentRoomPosition;
    private Sprite sprite;

    public Map() {
        rooms = new ArrayList<>();
        rooms.add(new ArrayList<>());
        rooms.get(0).add(new TestRoom(false, true, true, false));
        rooms.get(0).add(new TestRoom(false, false, false, true));
        rooms.add(new ArrayList<>());
        rooms.get(1).add(new TestRoom(true, true, false, false));
        rooms.get(1).add(new TestRoom(false, false, false, true));
        currentRoomPosition = new Vector2D(0, 0);
        actualizeSprite();
    }

    private void actualizeSprite() {
        sprite = rooms.get((int) currentRoomPosition.x).get((int) currentRoomPosition.y).getSprite();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public List<List<Room>> getRooms() {
        return rooms;
    }

    public Vector2D getMapSize() {
        return new Vector2D(rooms.size(), rooms.get(0).size());
    }

    public void moveRoom(Vector2D direction){
        currentRoomPosition = currentRoomPosition.add(direction);
        actualizeSprite();
    }
}
