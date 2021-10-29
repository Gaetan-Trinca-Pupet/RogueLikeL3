package utilities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {
    protected Vector2D position;
    protected Vector2D drawerPosition;

    public abstract void drawSelfOnto(GraphicsContext graphicsContext);

    public Sprite(Vector2D position){
        this.position = position;
        drawerPosition = new Vector2D();
    }

    public void computeDrawerPosition(Vector2D center){
        drawerPosition = new Vector2D(position).add(center);
    }
}
