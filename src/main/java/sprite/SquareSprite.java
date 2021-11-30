package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utilities.Vector2D;

public class SquareSprite extends Sprite {
    private Vector2D size;
    private Color color;

    @Override
    public void draw(GraphicsContext graphicsContext, Vector2D position) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(position.x, position.y, size.x, size.y);
    }

    @Override
    public Sprite copy() {
        return new SquareSprite(position, size, color);
    }

    public SquareSprite(Vector2D position, Vector2D size, Color color){
        super(position);
        this.size = size;
        this.color = color;
    }

    public SquareSprite(Vector2D position, Vector2D size){
        this(position, size, new Color(0,0,0,1));
    }

    public SquareSprite(){
        this(new Vector2D(), new Vector2D());
    }
}
