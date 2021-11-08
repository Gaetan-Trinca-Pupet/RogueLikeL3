package utilities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {
    ArrayList<Image> animation;

    public AnimatedSprite(Vector2D position, ArrayList<Image> animation) {
        super(position);
        this.animation = animation;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, Vector2D position) {
        for (Image img:animation) {
            graphicsContext.drawImage(img, position.x, position.y);
        }
    }

}
