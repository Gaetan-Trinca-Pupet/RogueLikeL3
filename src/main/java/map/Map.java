package map;

import sprite.Sprite;
import utilities.Grid;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private Grid<Room> rooms;
    private Vector2D currentRoomPosition;
    private Sprite sprite;
    private int maxSize;

    public Map() {
        generateMap(50);
        actualizeSprite();
    }

    private void actualizeSprite() {
        sprite = rooms.get((int) currentRoomPosition.x, (int) currentRoomPosition.y).getSprite();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Grid<Room> getRooms() {
        return rooms;
    }

    public Vector2D getMapSize() {
        return new Vector2D(rooms.getSizeWidth(), rooms.getSizeHeight());
    }

    public void moveRoom(Vector2D direction){
        currentRoomPosition = currentRoomPosition.add(direction);
        actualizeSprite();
    }

    private void generateMap(final int nbRoom){
        System.out.println("Generating map...");

        Random rand = new Random();
        maxSize = (int) (Math.sqrt(nbRoom) * 2);
        rooms = new Grid<Room>(maxSize, maxSize);

        ArrayList<Vector2D> roomCreated = new ArrayList<Vector2D>();
        roomCreated.add(new Vector2D(rooms.getSizeWidth()/2, rooms.getSizeHeight()/2));
        rooms.set((int) roomCreated.get(0).x, (int) roomCreated.get(0).y, new TestRoom());
        currentRoomPosition = roomCreated.get(0);

        while(roomCreated.size() < nbRoom) {
            Vector2D processedRoom = roomCreated.get(rand.nextInt(roomCreated.size()));
            Vector2D direction = Vector2D.DIRECTIONS.get(rand.nextInt(Vector2D.DIRECTIONS.size()));
            Vector2D newRoom = processedRoom.add(direction);

            if( ! isRoomPositionValid(newRoom)) continue;

            roomCreated.add(newRoom);
            rooms.set((int) newRoom.x ,(int) newRoom.y, new TestRoom());
            rooms.get((int) processedRoom.x, (int) processedRoom.y).addExit(direction);
            rooms.get((int) newRoom.x, (int) newRoom.y).addExit(Vector2D.OPPOSITE_DIRECTION.get(direction));

        }

    }

    private boolean isRoomPositionValid(Vector2D v) {
        return (v.x >= 0 && v.y >= 0 && v.x < rooms.getSizeWidth() && v.y < rooms.getSizeHeight() && !isThereRoomAt(v));
    }
    private boolean isThereRoomAt(Vector2D v){
        return (rooms.get((int) v.x, (int) v.y) != null);
    }

    public int getMaxSize(){
        return maxSize;
    }
}
