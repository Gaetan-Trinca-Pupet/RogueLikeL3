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
    public void drawSelfOnto(GraphicsContext graphicsContext) {
        for (Image img:animation) {
            graphicsContext.drawImage(img, drawerPosition.x, drawerPosition.y);
        }
    }

}
