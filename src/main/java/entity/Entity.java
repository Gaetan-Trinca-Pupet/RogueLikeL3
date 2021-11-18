package entity;

import collision.Collision;
import gameComponents.GameContext;
import gameComponents.GameState;
import sprite.Sprite;
import utilities.Updatable;
import utilities.Vector2D;

public abstract class Entity implements Updatable {
    static protected GameContext game;

    public static void setGameContext(GameContext context){
        game = context;
    }

    private Collision hitBox;

    private Sprite sprite;

    private Vector2D position;


    public boolean collideWith(Entity entity){
        return hitBox.intersect(entity.hitBox);
    }

    public void translate(Vector2D difference){
        position = position.add(difference);
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
        this.position = position;
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
}
