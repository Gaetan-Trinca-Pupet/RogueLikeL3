package sprite;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import utilities.Vector2D;

public class ImageSpriteFromTilemapFactory {
    public static ImageSprite construct(String path, int col, int row, Vector2D position, Vector2D size) {
        PixelReader reader = new Image("file:src/resources/" + path).getPixelReader();
        Image croppedImage = new WritableImage(reader, col*17, row*17, 16, 16);
        return new ImageSprite(croppedImage, position, size);
    }
}
