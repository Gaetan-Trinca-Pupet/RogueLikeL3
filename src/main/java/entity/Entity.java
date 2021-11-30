package entity;

import collision.Collision;
import gameComponents.GameContext;
import map.Room;
import ressourceManager.ImageManager;
import sprite.Sprite;
import utilities.UpdateOnTimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

public abstract class Entity implements UpdateOnTimeEvent {
    static protected GameContext game;
    static protected SpriteHandler spriteHandler = new SpriteHandler();
    static protected ImageManager imageManager = new ImageManager();

    public static void setGameContext(GameContext context){
        game = context;
    }

    public static void setSpriteHandler(SpriteHandler handler) {
        spriteHandler = handler;
    }

    protected Room inWhatRoom;

    public void setRoom(Room room){
        inWhatRoom = room;
    }

    public Room getCurrentRoom(){
        return inWhatRoom;
    }

    protected SpriteHandler selfSpriteHandler;

    protected Collision hitBox;

    protected Sprite sprite;

    protected Vector2D position;


    public boolean collideWith(Entity entity){
        return hitBox.intersect(entity.hitBox);
    }

    public Vector2D translate(Vector2D difference){
        position.x += difference.x;
        position.y += difference.y;

        return position;
    }

    public void translateHitBox(Vector2D difference){
        hitBox.translate(difference);
    }

    public void translateSprite(Vector2D difference){
        sprite.translate(difference);
    }

    public void translateAll(Vector2D difference){
        translate(difference);
        translateHitBox(difference);
        translateSprite(difference);
    }


///
/// Setter
///
    public void setHitBox(Collision hitBox) {
        this.hitBox = hitBox;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setPosition(Vector2D position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }


///
/// Getter
///
    public Collision getHitBox() {
        return hitBox;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2D getPosition() {
        return position;
    }


    protected void addSpriteTo(Ground ground){
        selfSpriteHandler = spriteHandler;
        selfSpriteHandler.addSpriteTo(ground, sprite);
    }

    protected void removeSpriteTo(Ground ground){
        selfSpriteHandler.removeSpriteTo(ground, sprite);
    }
}
