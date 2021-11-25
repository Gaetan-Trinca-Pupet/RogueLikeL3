package utilities;

import java.util.*;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0,0);
    }

    public Vector2D(Vector2D vector2D) {
        this(vector2D.x, vector2D.y);
    }

    public Vector2D add(Vector2D vector2D){
        return new Vector2D(this.x + vector2D.x, this.y + vector2D.y);
    }

    public Vector2D subtract(Vector2D vector2D){
        return new Vector2D(this.x - vector2D.x, this.y - vector2D.y);
    }

    public Vector2D multiply(Vector2D vector2D){
        return new Vector2D(this.x * vector2D.x, this.y * vector2D.y);
    }

    public Vector2D divideBy(Vector2D vector2D){
        return new Vector2D(this.x / vector2D.x, this.y / vector2D.y);
    }

    @Override
    public String toString(){
        return "[ "+ x + " ; " + y + " ]";
    }


    public static final Vector2D TOP = new Vector2D(0, -1);
    public static final Vector2D RIGHT = new Vector2D(1, 0);
    public static final Vector2D BOTTOM = new Vector2D(0, 1);
    public static final Vector2D LEFT = new Vector2D(-1, 0);
    public static final List<Vector2D> DIRECTIONS = new ArrayList(Arrays.asList(TOP, RIGHT, BOTTOM, LEFT));
    public static final Map<Vector2D, Vector2D> OPPOSITE_DIRECTION;

    static{
        OPPOSITE_DIRECTION = new HashMap<>();
        OPPOSITE_DIRECTION.put(LEFT, RIGHT);
        OPPOSITE_DIRECTION.put(RIGHT, LEFT);
        OPPOSITE_DIRECTION.put(TOP, BOTTOM);
        OPPOSITE_DIRECTION.put(BOTTOM, TOP);
    }
}
