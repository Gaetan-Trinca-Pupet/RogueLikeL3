package collision;

import utilities.Vector2D;

public abstract class Collision {
    public boolean intersect(Collision collision){
        return this.isInside(collision) || collision.isInside(this);
    }

    public abstract boolean isInside(Collision collision);
    public abstract boolean isInside(SegmentCollision segmentCollision);
    public abstract boolean isInside(Vector2D point);
}
