package map;

import EventManager.KeyEventManager;
import collision.Collidable;
import collision.SquareCollision;
import entity.Creature;
import entity.Entity;
import entity.Interactable;
import sprite.Sprite;
import sprite.CompositeSprite;
import test.TimeEvent;
import utilities.Grid;
import utilities.UpdateOnTimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Room implements UpdateOnTimeEvent {
    protected Grid<Tile> tiles;
    private CompositeSprite sprite;

    private SpriteHandler spriteHandler;

    protected List<Entity> updatableEntity;
    private HashMap<Creature, List<Interactable>> interactionList;

    private List<Collidable> collidables;
    private Vector2D position;

    protected final Vector2D ROOM_SIZE = new Vector2D(16, 16);
    protected final Vector2D TILE_SIZE = new Vector2D(32, 32);
    protected final Vector2D SPRITE_POSITION = TILE_SIZE.multiply(ROOM_SIZE).divideBy(new Vector2D(-2, -2));

    protected void generate() {
        sprite = new CompositeSprite(SPRITE_POSITION);
        tiles = new Grid<>((int) ROOM_SIZE.x, (int) ROOM_SIZE.y);
        spriteHandler = new SpriteHandler();
        updatableEntity = new ArrayList<>();
        interactionList = new HashMap<>();
        collidables = new ArrayList<>();
        generateRoom();
        actualizeSprite();
        spriteHandler.addSpriteTo(Ground.BACKGROUND, sprite);
    }

    protected Vector2D getTilePxPosition(Vector2D position) {
        return position.multiply(TILE_SIZE).add(this.position);
    }

    protected abstract void generateRoom();

    public abstract Sprite getMinimapSprite(Vector2D position, Vector2D size);

    protected void actualizeSprite() {
        sprite.clear();
        for (int y = 0; y < tiles.getSizeWidth(); ++y) {
            for (int x = 0; x < tiles.getSizeHeight(); ++x) {
                sprite.add(tiles.get(y).get(x).getSprite());
            }
        }
    }

    public abstract void addExit(Vector2D direction);

    public Sprite getSprite() {
        return sprite;
    }

    public List<Collidable> getCollidables() {
        return collidables;
    }

    public void addInterractionTo(Creature creature, Interactable interactable){
        if( ! interactionList.containsKey(creature))
            interactionList.put(creature, new ArrayList<>());

        interactionList.get(creature).add(interactable);
    }

    public void removeInteractionFrom(Creature creature, Interactable interactable){
        if( ! interactionList.containsKey(creature))
            return;

        interactionList.get(creature).remove(interactable);
    }

    public void checkInteractionForCreature(Creature creature){
        double minSize = Math.min(creature.getSize().x, creature.getSize().y);

        Vector2D centerPos = creature.getPosition().add(creature.getSize().divideBy(new Vector2D(2,2)));

        Vector2D sizeCollision = new Vector2D(minSize, minSize);
        Vector2D posCollision = centerPos.add(sizeCollision.multiply(creature.getFacing()).subtract(sizeCollision.divideBy(new Vector2D(2,2))));
        SquareCollision collision = new SquareCollision(posCollision, sizeCollision);

        ArrayList<Interactable> interactables = new ArrayList<>(interactionList.get(creature));
        for(Interactable interactable : interactables)
            if(collision.intersect(interactable.getHitBox())) {
                interactable.interact(creature);
                break;
            }
    }

    public void checkAllInteraction(){
        for(Creature creature : interactionList.keySet()){
            checkInteractionForCreature(creature);
        }
    }

    public void addEntity(Entity entity){
        entity.setRoom(this);
        spriteHandler.addSpriteTo(Ground.FOREGROUND, entity.getSprite());
        updatableEntity.add(entity);
    }

    public void removeEntity(Entity entity){
        spriteHandler.removeSpriteTo(Ground.FOREGROUND, entity.getSprite());
        updatableEntity.remove(entity);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        for(int i = 0 ; i < updatableEntity.size() ; ++i)
            updatableEntity.get(i).updateOnTimeEvent(event);

    }

    public SpriteHandler getSpriteHandler(){
        return spriteHandler;
    }
}
