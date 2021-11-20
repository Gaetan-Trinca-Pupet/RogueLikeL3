package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utilities.Vector2D;

public class ImageSprite extends Sprite {
    private Vector2D size;
    private Image image;

    public ImageSprite(Image image) {
        this(image, new Vector2D(), new Vector2D(image.getWidth(), image.getHeight()));
    }

    public ImageSprite(Image image, Vector2D position, Vector2D size) {
        super(position);
        this.image = image;
        this.size = size;
    }

    public ImageSprite(Image image, Vector2D position) {
        this(image, position, new Vector2D(image.getWidth(), image.getHeight()));
    }

    public ImageSprite(Image image) {
        this(image, new Vector2D());
    }

    public void setSize(Vector2D size){
        this.size = size;
    }

    public Vector2D getSize(){
        return size;
    }

    public Image getImage(){
        return image;
    }

    @Override
    protected void draw(GraphicsContext graphicsContext, Vector2D position) {
        graphicsContext.drawImage(image, position.x, position.y, size.x, size.y);
    }
}
