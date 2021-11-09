package collision;

import utilities.Vector2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeCollision extends Collision{

    ArrayList<Collision> collisions;

    public CompositeCollision(Collision... collisions){
        this.collisions = new ArrayList<Collision>(List.of(collisions));
    }

    public CompositeCollision(){
        this(new Collision[]{});
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

    @Override
    public boolean isInside(Collision collision) {
        for(Collision collider : collisions)
            if(collider.isInside(collision))
                return true;

        return false;
    }

    @Override
    public boolean isInside(SegmentCollision segmentCollision) {
        for(Collision collider : collisions)
            if(collider.isInside(segmentCollision))
                return true;
        return false;
    }

    @Override
    public boolean isInside(Vector2D point) {
        for(Collision collider : collisions)
            if(collider.isInside(point))
                return true;
        return false;
    }
}
