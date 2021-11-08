package collision;

import utilities.Vector2D;

public class CircleCollision extends Collision {
    public Vector2D position;
    public long radius;

    public CircleCollision(Vector2D position, long radius){
        this.position = position;
        this.radius = radius;
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }

    public void setRadius(long radius){
        this.radius = radius;
    }

    @Override
    public boolean isInside(Collision collision) {
        Vector2D newPointOnCircle;
        Vector2D oldPointOnCircle = new Vector2D(position.x + Math.cos(Math.toRadians(0)) * radius, position.y + Math.sin(Math.toRadians(0)) * radius);
        for(double angle = 360/100 ; angle < 360 ; angle += 360/100)
        {
            double radiantAngle = Math.toRadians(angle);
            newPointOnCircle = new Vector2D(position.x + Math.cos(radiantAngle) * radius, position.y + Math.sin(radiantAngle) * radius);
            if(collision.isInside(new SegmentCollision(newPointOnCircle, oldPointOnCircle)))
                return true;
            oldPointOnCircle = new Vector2D(newPointOnCircle);
        }

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
