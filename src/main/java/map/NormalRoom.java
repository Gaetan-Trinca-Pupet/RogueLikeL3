package map;

import collision.CollisionType;
import entity.*;
import Monster.Lion;
import javafx.scene.paint.Color;
import sprite.CompositeSprite;
import sprite.Sprite;
import sprite.SquareSprite;
import utilities.Vector2D;

import java.util.*;
import java.util.Map;

public class NormalRoom extends Room{
    protected Map <Vector2D, Boolean> exits;
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
        Random rand = new Random();
        for (int y = 1; y < ROOM_SIZE.y-1; ++y) {
            for (int x = 1; x < ROOM_SIZE.x-1; ++x) {
                if (rand.nextInt(100) == 0) setTrap(x, y);
                else setFloor(x, y);
            }
        }
    }

    public void addExit(Vector2D direction) {
        exits.put(direction, true);
        if (direction == Vector2D.TOP) {
            setDoor((int) (ROOM_SIZE.x / 2 ) - 1,0, CollisionType.EXIT_TOP);
            setDoor((int) (ROOM_SIZE.x / 2 ),0, CollisionType.EXIT_TOP);
        } else if (direction == Vector2D.RIGHT) {
            setDoor((int) ROOM_SIZE.x - 1,(int) (ROOM_SIZE.y / 2 ) - 1, CollisionType.EXIT_RIGHT);
            setDoor((int) ROOM_SIZE.x - 1, (int) (ROOM_SIZE.y / 2 ), CollisionType.EXIT_RIGHT);
        } else if (direction == Vector2D.BOTTOM) {
            setDoor((int) (ROOM_SIZE.x / 2 ) - 1,(int) ROOM_SIZE.y - 1, CollisionType.EXIT_BOTTOM);
            setDoor((int) (ROOM_SIZE.x / 2 ),(int) ROOM_SIZE.y - 1, CollisionType.EXIT_BOTTOM);
        } else if (direction == Vector2D.LEFT) {
            setDoor(0,(int) (ROOM_SIZE.y / 2 ) - 1, CollisionType.EXIT_LEFT);
            setDoor(0, (int) (ROOM_SIZE.y / 2 ), CollisionType.EXIT_LEFT);
        }
        actualizeSprite();
    }

    private void setFloor (int x, int y) {
        tiles.set(x, y, new Floor(new Vector2D(x, y), TILE_SIZE, SPRITE_POSITION));
    }

    private void setTrap (int x, int y) {
        Tile newTile = new Trap(new Vector2D(x, y), TILE_SIZE, SPRITE_POSITION, 10);
        tiles.set(x, y, newTile);
        getCollidables().add(newTile);
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
        sprite.add(new SquareSprite(new Vector2D(), size, Color.POWDERBLUE));

        Color color = Color.BLACK;
        if(updatableEntity.isEmpty()) color = Color.DARKGREEN;
        else {
            int hierarchy = Integer.MAX_VALUE;
            for (Entity entity : updatableEntity) {
                if (hierarchy > 0 && entity instanceof Player) { color = Color.BLUE; hierarchy = 0 ; }
                else if (hierarchy > 1 && entity instanceof Boss) { color = Color.PINK; hierarchy = 1 ; }
                else if (hierarchy > 2 && entity instanceof Pickable) { color = Color.YELLOW; hierarchy = 2 ; }
                else if (hierarchy > 3 && entity instanceof Monster) { color = Color.RED; hierarchy = 3 ; }

            }
        }

        sprite.add(new SquareSprite(size.divideBy(vectorThree), size.divideBy(vectorThree), color));
        for (Map.Entry<Vector2D, Boolean> exit : exits.entrySet()) {
            if (exit.getValue()) {
                sprite.add(new SquareSprite(size.multiply(exit.getKey().add(new Vector2D(1, 1))).divideBy(vectorThree), size.divideBy(vectorThree), Color.GRAY));
            }
        }
        return sprite;
    }
}
