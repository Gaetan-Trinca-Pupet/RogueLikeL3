package collision;

import utilities.Vector2D;

import java.security.PrivateKey;

public class SquareCollision extends Collision {
    private Vector2D size;

    private SegmentCollision top;
    private SegmentCollision bottom;
    private SegmentCollision left;
    private SegmentCollision right;

    public SquareCollision(Vector2D position, Vector2D size){
        super(position);
        this.size = size;
        computeSegments();
    }

    @Override
    public void setPosition(Vector2D position) {
        super.setPosition(position);
        computeSegments();
    }

    @Override
    public void translate(Vector2D position){
        super.translate(position);
        computeSegments();
    }

    public void setSize(Vector2D size) {
        this.size = size;
        computeSegments();
    }

    private void computeSegments(){
        top = new SegmentCollision(new Vector2D(position), position.add(new Vector2D(size.x, 0)));
        bottom = new SegmentCollision(position.add(new Vector2D(0, size.y)), position.add(size));
        left = new SegmentCollision(new Vector2D(position), position.add(new Vector2D(0, size.y)));
        right = new SegmentCollision(position.add(new Vector2D(size.x, 0)), position.add(size));
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
