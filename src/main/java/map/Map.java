package map;

import Consomable.Apple;
import Controller.MouseAndKeyboardController;
import Monster.Wolf;
import collision.Collidable;
import entity.*;
import sprite.CompositeSprite;
import sprite.Sprite;
import test.TimeEvent;
import utilities.Grid;
import utilities.UpdateOnTimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map implements UpdateOnTimeEvent {
    private Grid<Room> rooms;
    private Vector2D currentRoomPosition;
    private SpriteHandler spriteHandler;
    private CompositeSprite sprite;
    private List<Collidable> collidables;
    private int maxSize;

    private Player player;

    public Map(MouseAndKeyboardController controller) {
        spriteHandler = new SpriteHandler();
        Entity.setSpriteHandler(spriteHandler);
        player = new Player(controller, this);
        player.setPosition(new Vector2D());

        generateMap(10);
        sprite = new CompositeSprite();
        spriteHandler.addSpriteTo(Ground.BACKGROUND, sprite);
        //spriteHandler.addSpriteTo(Ground.FOREGROUND, player.getSprite());
        actualizeSprite();
        actualizeCollidables();
    }

    public Player getPlayer(){
        return player;
    }

    public SpriteHandler getSpriteHandler(){
        return spriteHandler;
    }

    private void actualizeSprite() {
        sprite.clear();
        sprite.add(rooms.get((int) currentRoomPosition.x, (int) currentRoomPosition.y).getSprite());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Grid<Room> getRooms() {
        return rooms;
    }

    public List<Collidable> getCollidables ()
    {
        return collidables;
    }

    private void actualizeCollidables() {
        collidables = rooms.get((int) currentRoomPosition.x).get((int) currentRoomPosition.y).getCollidables();
    }

    public Vector2D getMapSize() {
        return new Vector2D(rooms.getSizeWidth(), rooms.getSizeHeight());
    }

    public Vector2D moveRoom(Vector2D direction){
        moveEntityToRoom(player, currentRoomPosition, currentRoomPosition.add(direction));

        currentRoomPosition = currentRoomPosition.add(direction);
        Room current = rooms.get((int) currentRoomPosition.x, (int) currentRoomPosition.y);

        Vector2D mapSize = current.ROOM_SIZE.multiply(current.TILE_SIZE).multiply(new Vector2D(0.7,0.7));
        Vector2D nextDoorPosition = new Vector2D();
        nextDoorPosition.x -= mapSize.x / 2 * direction.x;
        nextDoorPosition.y -= mapSize.y / 2 * direction.y;


        actualizeSprite();
        actualizeCollidables();

        return nextDoorPosition;
    }

    public void moveEntityToRoom(Entity entity, Vector2D source, Vector2D destination){
        rooms.get((int) source.x, (int) source.y).removeEntity(entity);
        rooms.get((int) destination.x, (int) destination.y).addEntity(entity);
    }

    private void generateMap(final int nbRoom){
        System.out.println("Generating map...");

        Random rand = new Random();
        maxSize = (int) (Math.sqrt(nbRoom) * 2);
        rooms = new Grid<>(maxSize, maxSize);

        ArrayList<Vector2D> roomCreated = new ArrayList<>();
        roomCreated.add(new Vector2D((double)rooms.getSizeWidth()/2, (double)rooms.getSizeHeight()/2));
        rooms.set((int) roomCreated.get(0).x, (int) roomCreated.get(0).y, new NormalRoom());
        currentRoomPosition = roomCreated.get(0);
        rooms.get((int) currentRoomPosition.x,(int) currentRoomPosition.y).addEntity(player);

        while(roomCreated.size() < nbRoom) {
            Vector2D processedRoom = roomCreated.get(rand.nextInt(roomCreated.size()));
            Vector2D direction = Vector2D.DIRECTIONS.get(rand.nextInt(Vector2D.DIRECTIONS.size()));
            Vector2D newRoom = processedRoom.add(direction);

            if( ! isRoomPositionValid(newRoom)) continue;

            roomCreated.add(newRoom);
            rooms.set((int) newRoom.x ,(int) newRoom.y, new NormalRoom());
            System.out.println("Room at : " + processedRoom);
            rooms.get((int) processedRoom.x, (int) processedRoom.y).addExit(direction);
            System.out.println("Room at : " + newRoom);
            rooms.get((int) newRoom.x, (int) newRoom.y).addExit(Vector2D.OPPOSITE_DIRECTION.get(direction));

            generateEntityInsideRoom(rooms.get((int) newRoom.x, (int) newRoom.y));

        }

    }

    private void generateEntityInsideRoom(Room room){
        Vector2D roomSize = new Vector2D();
        roomSize.x = room.ROOM_SIZE.x * room.TILE_SIZE.x;
        roomSize.y = room.ROOM_SIZE.y * room.TILE_SIZE.y;
        roomSize = roomSize.multiply(new Vector2D(0.7,0.7));

        Vector2D roomPos = roomSize.divideBy(new Vector2D(-2,-2));

        Random random = new Random();

        Creature creature = new Monster(new Wolf());
        creature.setPosition(new Vector2D(random.nextInt((int) roomSize.x) + roomPos.x, random.nextInt((int) roomSize.y) + roomPos.y));
        Pickable pickable = new Pickable(new Apple());
        pickable.setPosition(new Vector2D(random.nextInt((int) roomSize.x) + roomPos.x, random.nextInt((int) roomSize.y) + roomPos.y));

        room.addEntity(creature);
        room.addEntity(pickable);

        room.addInterractionTo(player, creature);
        room.addInterractionTo(player, pickable);
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

    public Room getCurrentRoom() {
        return rooms.get((int) currentRoomPosition.x,(int) currentRoomPosition.y);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        player.updateOnTimeEvent(event);
    }
}
