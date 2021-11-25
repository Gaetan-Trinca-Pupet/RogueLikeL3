package map;

import sprite.Sprite;
import utilities.Grid;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Grid<Room> rooms;
    private Vector2D currentRoomPosition;
    private Sprite sprite;

    public Map() {
        generateMap(50);
//        rooms = new ArrayList<>();
//        rooms.add(new ArrayList<>());
//        rooms.get(0).add(new TestRoom(false, true, true, false));
//        rooms.get(0).add(new TestRoom(false, false, false, true));
//        rooms.add(new ArrayList<>());
//        rooms.get(1).add(new TestRoom(true, true, false, false));
//        rooms.get(1).add(new TestRoom(false, false, false, true));
        currentRoomPosition = new Vector2D(0, 0);
        actualizeSprite();
    }

    private void actualizeSprite() {
        sprite = rooms.get((int) currentRoomPosition.x).get((int) currentRoomPosition.y).getSprite();
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
        rooms = new Grid<Room>(nbRoom/5, nbRoom/5);
        ArrayList<Vector2D> roomToProcess = new ArrayList<Vector2D>();
        ArrayList<Vector2D> roomCreated = new ArrayList<Vector2D>();

        roomToProcess.add(new Vector2D(rooms.getSizeWidth(), rooms.getSizeHeight()));
        while(roomCreated.size() < nbRoom){
            while(roomToProcess.size() != 0){
                int i = (int) roomToProcess.get(0).x;
                int j = (int) roomToProcess.get(0).y;
                roomToProcess.remove(0);
                roomToProcess.addAll(processRoom(i, j, roomCreated, nbRoom));
            }
            roomToProcess = new ArrayList<>(roomCreated);
        }

    }

    private ArrayList<Vector2D> processRoom(int i, int j, ArrayList<Vector2D> roomCreated, int nbRoom){
        ArrayList<Vector2D> result = new ArrayList<>();
        //TODO decommenter et faire les rooms
//
//        if(roomCreated.size() >= nbRoom) return new ArrayList<>();
//        if(setRoomToGrid(i, j, /*new Room()*/)){
//            result.add(new Vector2D(i, j));
//            roomCreated.add(new Vector2D(i, j));
//        }
//
//
//        for(int k = -1 ; k <= 1 ; ++k){
//            if(k == 0)continue;
//            for(int l = -1 ; l <= 1 ; ++l){
//                if(l == 0) continue;
//
//                if(roomCreated.size() >= nbRoom) return new ArrayList<>();
//                if(setRoomToGrid(i - k, j - l, /*new Room()*/)){
//                    result.add(new Vector2D(i - k, j - l));
//                    roomCreated.add(new Vector2D(i - k, j - l));
//                }
//            }
//        }
        return result;
    }

    private boolean setRoomToGrid(int i, int j, Room room){
        if(rooms.get(i, j) != null) return false;
        rooms.set(i, j, room);
        return true;
    }
}
