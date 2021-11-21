package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.Vector2D;

public class LabelSprite extends Sprite{
    private String text;
    private Color color;

    public LabelSprite(String text, Color color, Vector2D position){
        super(position);
        this.text = text;
        this.color = color;
    }

    public LabelSprite(String text, Color color){
        this(text, color, new Vector2D());
    }

    public LabelSprite(String text, int size){
        this(text, new Color(0,0,0,1));
    }

    public LabelSprite(String text){
        this(text, 0);
    }

    public LabelSprite(){
        this("");
    }

    @Override
    protected void draw(GraphicsContext graphicsContext, Vector2D position) {
        graphicsContext.setFill(color);
        graphicsContext.fillText(text, position.x, position.y);
    }
}
