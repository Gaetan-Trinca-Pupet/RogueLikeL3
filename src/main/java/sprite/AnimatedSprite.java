package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utilities.Vector2D;

import java.util.ArrayList;

public class AnimatedSprite extends Sprite {
    private ArrayList<Sprite> animation;
    private int currentSprite;
    private int delay;
    private long chrono;

    public AnimatedSprite(Vector2D position, ArrayList<Sprite> animation, int delay) {
        super(position);
        this.animation = animation;
        this.delay = delay;
        currentSprite = 0;
        chrono = System.currentTimeMillis();
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Vector2D position) {
        if(System.currentTimeMillis() - chrono >= chrono){
            chrono = System.currentTimeMillis();
            ++currentSprite;
            if(currentSprite >= animation.size()) currentSprite = 0;
        }
        animation.get(currentSprite).draw(graphicsContext, position);
    }

    @Override
    public Sprite copy() {
        return new AnimatedSprite(position, animation, delay);
    }
}
