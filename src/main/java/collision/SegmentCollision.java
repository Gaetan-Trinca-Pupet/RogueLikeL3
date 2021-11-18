package collision;

import utilities.Vector2D;

public class SegmentCollision extends Collision{

    public Vector2D start;
    public Vector2D end;

    public SegmentCollision(Vector2D position, Vector2D start, Vector2D end) {
        super(position);
        this.start = start;
        this.end = end;
    }

    public SegmentCollision(Vector2D start, Vector2D end) {
        this(new Vector2D(), start, end);
    }

    public void setStart(Vector2D start) {
        this.start = start;
    }

    public void setEnd(Vector2D end) {
        this.end = end;
    }

    public Vector2D getRelativeStart(){
        return position.add(start);
    }

    public Vector2D getRelativeEnd(){
        return position.add(end);
    }

    @Override
    public boolean isInside(Collision collision) {
        return collision.isInside(this);
    }

    @Override
    public boolean isInside(SegmentCollision segmentCollision) {
        return ccw(getRelativeStart(), segmentCollision.getRelativeStart(), segmentCollision.getRelativeEnd()) != ccw(getRelativeEnd(), segmentCollision.getRelativeStart(), segmentCollision.getRelativeEnd()) && ccw(getRelativeStart(), getRelativeEnd(), segmentCollision.getRelativeStart()) != ccw(getRelativeStart(), getRelativeEnd(), segmentCollision.getRelativeEnd());
    }

    @Override
    public boolean isInside(Vector2D point) {
        if ( Math.abs((point.y - getRelativeStart().y) * (getRelativeEnd().x - getRelativeStart().x) - (point.x - getRelativeStart().x) * (getRelativeEnd().y - getRelativeStart().y)) > Math.ulp(1.0) )
            return false;

        float dotProduct = (float) ((point.x - getRelativeStart().x) * (getRelativeEnd().x - getRelativeStart().x) + (point.y - getRelativeStart().y) * (getRelativeEnd().y - getRelativeStart().y));
        if (dotProduct < 0)
            return false;

        float squareLength = (float) (Math.pow(getRelativeEnd().x - getRelativeStart().x, 2) + Math.pow(getRelativeEnd().y - getRelativeStart().y, 2));
        if (dotProduct > squareLength)
            return false;

        return true;
    }

    public static boolean ccw(Vector2D A, Vector2D B, Vector2D C){
        return (C.y-A.y) * (B.x-A.x) > (B.y-A.y) * (C.x-A.x);
    }
}
