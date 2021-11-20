package sprite;

import javafx.scene.canvas.GraphicsContext;
import sprite.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class CompositeSprite extends Sprite{
    private List<Sprite> sprites;

    public CompositeSprite(Vector2D position) {
        super(position);
        sprites = new ArrayList<>();
    }

    public CompositeSprite() {
        this(new Vector2D());
    }

    public void add(Sprite sprite) {
        sprites.add(sprite);
    }

    public void remove(Sprite sprite) {
        sprites.remove(sprite);
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Vector2D position) {
        for (Sprite sprite : sprites) {
            sprite.drawSelfOnto(graphicsContext, new Vector2D(position.x, position.y));
        }
    }
}
