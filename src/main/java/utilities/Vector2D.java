package utilities;

import java.util.Vector;

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

    public Vector2D add(Vector2D vector2D){
        this.x += vector2D.x;
        this.y += vector2D.y;

        return this;
    }

    public Vector2D subtract(Vector2D vector2D){
        this.x -= vector2D.x;
        this.y -= vector2D.y;

        return this;
    }

    public Vector2D multiply(Vector2D vector2D){
        this.x *= vector2D.x;
        this.y *= vector2D.y;

        return this;
    }

    public Vector2D divideBy(Vector2D vector2D){
        this.x /= vector2D.x;
        this.y /= vector2D.y;

        return this;
    }
}