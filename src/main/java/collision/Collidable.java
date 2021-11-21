package collision;

public interface Collidable {
    Collision getHitbox();
    CollisionType collide();
}
