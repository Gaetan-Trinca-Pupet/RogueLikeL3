package test;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utilities.Sprite;
import utilities.Vector2D;

public class Square extends Sprite {
    Vector2D size;
    Color color;

    @Override
    public void drawSelfOnto(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(drawerPosition.x, drawerPosition.y, size.x, size.y);
    }

    public Square(Vector2D position, Vector2D size, Color color){
        super(position);
        this.size = size;
        this.color = color;
    }
}
