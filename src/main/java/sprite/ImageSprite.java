package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utilities.Vector2D;

public class ImageSprite extends Sprite {
    Vector2D size;
    Image image;

    public ImageSprite(Vector2D position, Image image, Vector2D size) {
        super(position);
        this.image = image;
        this.size = size;
    }

    public ImageSprite(Image image, Vector2D position) {
        super(position);
        this.image = image;
    }

    @Override
    public void drawSelfOnto(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, drawerPosition.x, drawerPosition.y);
    }
}
