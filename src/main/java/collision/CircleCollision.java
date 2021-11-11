package collision;

import utilities.Vector2D;

import static java.lang.Math.toRadians;

public class CircleCollision extends Collision {
    private Vector2D position;
    private long radius;

    private SegmentCollision[] segmentedCircle;

    public CircleCollision(Vector2D position, long radius){
        this.position = position;
        this.radius = radius;

        segmentedCircle = new SegmentCollision[100];
        for(int i = 0 ; i < segmentedCircle.length ; ++i)
            segmentedCircle[i] = new SegmentCollision(new Vector2D(), new Vector2D());
        computeSegments();
    }

    public void computeSegments(){
        double radiantAngle = Math.toRadians(360/segmentedCircle.length * (segmentedCircle.length - 1));
        Vector2D newPointOnCircle;
        Vector2D oldPointOnCircle = new Vector2D(position.x + Math.cos(radiantAngle) * radius, position.y + Math.sin(radiantAngle) * radius);
        for(int i = 0 ; i < segmentedCircle.length ; ++i)
        {
            radiantAngle = Math.toRadians(360/segmentedCircle.length * i);
            newPointOnCircle = new Vector2D(position.x + Math.cos(radiantAngle) * radius, position.y + Math.sin(radiantAngle) * radius);
            segmentedCircle[i].setStart(newPointOnCircle);
            segmentedCircle[i].setEnd(oldPointOnCircle);
            oldPointOnCircle = new Vector2D(newPointOnCircle);
        }
    }

    @Override
    public void setPosition(Vector2D position){
        super.setPosition(position);
        computeSegments();
    }

    @Override
    public void translate(Vector2D position){
        super.translate(position);
        computeSegments();
    }

    public void setRadius(long radius){
        this.radius = radius;
        computeSegments();
    }

    @Override
    public boolean isInside(Collision collision) {
        for(SegmentCollision segment : segmentedCircle)
            if(collision.isInside(segment))
                return true;
        return false;
    }

    @Override
    public boolean isInside(SegmentCollision segmentCollision) {
        if(isInside(segmentCollision.start) || isInside(segmentCollision.end))
            return true;

        Vector2D nearestPoint = new Vector2D();
        nearestPoint.x = segmentCollision.end.x - segmentCollision.start.x;
        nearestPoint.y = segmentCollision.end.y - segmentCollision.start.y;

        if ((nearestPoint.x == 0) && (nearestPoint.y == 0)) // A and B are the same points, no way to calculate intersection
            return false;

        double dl = (nearestPoint.x * nearestPoint.x + nearestPoint.y * nearestPoint.y);
        double t = ((position.x - segmentCollision.start.x) * nearestPoint.x + (position.y - segmentCollision.start.y) * nearestPoint.y) / dl;

        // point on a line nearest to circle center
        nearestPoint = new Vector2D(segmentCollision.start.x + t * nearestPoint.x, segmentCollision.start.y + t * nearestPoint.y);


        if(segmentCollision.isInside(nearestPoint))
            return isInside(nearestPoint);
        return false;
    }

    @Override
    public boolean isInside(Vector2D point) {
        return Math.pow(point.x - position.x, 2) + Math.pow(point.y - position.y, 2) <= Math.pow(radius, 2);
    }
}
