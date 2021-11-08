package collision;

import utilities.Vector2D;

public class SegmentCollision extends Collision{

    public Vector2D start;
    public Vector2D end;

    public SegmentCollision(Vector2D start, Vector2D end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(Vector2D start) {
        this.start = start;
    }

    public void setEnd(Vector2D end) {
        this.end = end;
    }

    @Override
    public boolean isInside(Collision collision) {
        return collision.isInside(this);
    }

    @Override
    public boolean isInside(SegmentCollision segmentCollision) {
        return ccw(start,segmentCollision.start,segmentCollision.end) != ccw(end,segmentCollision.start,segmentCollision.end) && ccw(start,end,segmentCollision.start) != ccw(start,end,segmentCollision.end);
    }

    @Override
    public boolean isInside(Vector2D point) {
        if ( Math.abs((point.y - start.y) * (end.x - start.x) - (point.x - start.x) * (end.y - start.y)) > Math.ulp(1.0) )
            return false;

        float dotProduct = (float) ((point.x - start.x) * (end.x - start.x) + (point.y - start.y) * (end.y - start.y));
        if (dotProduct < 0)
            return false;

        float squareLength = (float) (Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
        if (dotProduct > squareLength)
            return false;

        return true;
    }

    public static boolean ccw(Vector2D A, Vector2D B, Vector2D C){
        return (C.y-A.y) * (B.x-A.x) > (B.y-A.y) * (C.x-A.x);
    }
}
