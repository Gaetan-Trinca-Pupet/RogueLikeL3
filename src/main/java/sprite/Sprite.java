package sprite;

import javafx.scene.canvas.GraphicsContext;
import utilities.Vector2D;

public abstract class Sprite {
    protected Vector2D position;

    public void drawSelfOnto(GraphicsContext graphicsContext, Vector2D parentPosition) {
        System.out.println(position.x + parentPosition.x + " " + position.y + parentPosition.y);
        draw(graphicsContext, new Vector2D(position.x + parentPosition.x, position.y + parentPosition.y));
    }

    protected abstract void draw(GraphicsContext graphicsContext, Vector2D position);

    public Sprite(Vector2D position){
        this.position = position;
    }
}
