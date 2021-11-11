package collision;

import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeCollision extends Collision{
    ArrayList<Collision> collisions;

    public CompositeCollision(Vector2D position, Collision... collisions){
        super(position);
        this.collisions = new ArrayList<Collision>(List.of(collisions));
    }

    public CompositeCollision(Collision... collisions){
        this(new Vector2D(), collisions);
    }

    public CompositeCollision(){
        this(new Vector2D(), new Collision[]{});
    }

    public void addCollision(Collision... collisions){
        this.collisions.addAll(List.of(collisions));
    }

    public void removeCollision(Collision... collisions){
        this.collisions.removeAll(List.of(collisions));
    }

    public ArrayList<Collision> getAllColliderWith(Collision collision){
        ArrayList<Collision> result = new ArrayList<>();
        for(Collision collider : collisions)
            if(collider.intersect(collision))
                result.add(collider);

        return result;
    }

    private boolean checkIfCollisionIsInAllCollider(Collision collision){
        for(Collision collider : collisions)
        {
            collider.getPosition().add(getPosition());
            if(collider.isInside(collision))
            {
                collider.getPosition().subtract(getPosition());
                return true;
            }
            collider.getPosition().subtract(getPosition());
        }
        return false;
    }

    @Override
    public boolean isInside(Collision collision) {
        return checkIfCollisionIsInAllCollider(collision);
    }

    @Override
    public boolean isInside(SegmentCollision segmentCollision) {
        return checkIfCollisionIsInAllCollider(segmentCollision);
    }

    @Override
    public boolean isInside(Vector2D point) {
        for(Collision collider : collisions)
        {
            collider.getPosition().add(getPosition());
            if(collider.isInside(point))
            {
                collider.getPosition().subtract(getPosition());
                return true;
            }
            collider.getPosition().subtract(getPosition());
        }
        return false;
    }
}
