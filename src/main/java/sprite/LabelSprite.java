package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.Vector2D;

public class LabelSprite extends Sprite{
    private String text;
    private Color color;
    private Color strokeColor;
    private int size;

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setText(String text){
        this.text = text;
    }

    public LabelSprite(String text, Color color, Vector2D position){
        super(position);
        this.text = text;
        this.color = color;
        strokeColor = null;
        this.size = 10;
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
        graphicsContext.setFont(new Font(size));
        graphicsContext.fillText(text, position.x, position.y);
        if(strokeColor != null) {
            graphicsContext.setStroke(strokeColor);
            graphicsContext.strokeText(text, position.x, position.y);
        }
    }
}
