package collision;

import utilities.Vector2D;

import java.security.PrivateKey;

public class SquareCollision extends Collision {
    public Vector2D position;
    public Vector2D size;

    private SegmentCollision top;
    private SegmentCollision bottom;
    private SegmentCollision left;
    private SegmentCollision right;

    public SquareCollision(Vector2D position, Vector2D size){
        this.position = position;
        this.size = size;
        computeSegments();
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        computeSegments();
    }

    public void setSize(Vector2D size) {
        this.size = size;
        computeSegments();
    }

    private void computeSegments(){
        top = new SegmentCollision(new Vector2D(position), new Vector2D(position).add(new Vector2D(size.x, 0)));
        bottom = new SegmentCollision(new Vector2D(position).add(new Vector2D(0, size.y)), new Vector2D(position).add(size));
        left = new SegmentCollision(new Vector2D(position), new Vector2D(position).add(new Vector2D(0, size.y)));
        right = new SegmentCollision(new Vector2D(position).add(new Vector2D(size.x, 0)), new Vector2D(position).add(size));
    }

    @Override
    public boolean isInside(Collision collision) {
        if(collision.isInside(top)) return true;
        else if(collision.isInside(bottom)) return true;
        else if(collision.isInside(left)) return true;
        else if(collision.isInside(right)) return true;
        else return false;
    }

    @Override
    public boolean isInside(SegmentCollision segmentCollision) {
        if(isInside(segmentCollision.start) || isInside(segmentCollision.end)) return true;
        else if(segmentCollision.intersect(top)) return true;
        else if(segmentCollision.intersect(bottom)) return true;
        else if(segmentCollision.intersect(left)) return true;
        else if(segmentCollision.intersect(right)) return true;
        else return false;
    }

    @Override
    public boolean isInside(Vector2D point) {
        return (point.x >= position.x) && (point.x <= position.x + size.x) && (point.y >= position.y) && (point.y <= position.y + size.y);
    }
}
