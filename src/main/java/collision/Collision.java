package collision;

import utilities.Vector2D;

public abstract class Collision {
    protected Vector2D position;

    public Collision(Vector2D position){
        this.position = position;
    }

    public Collision(){
        this(new Vector2D());
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void translate(Vector2D difference) {
        position.x += difference.x;
        position.y += difference.y;
    }

    public boolean intersect(Collision collision){
        return this.isInside(collision) || collision.isInside(this);
    }

    public abstract boolean isInside(Collision collision);
    public abstract boolean isInside(SegmentCollision segmentCollision);
    public abstract boolean isInside(Vector2D point);
}
