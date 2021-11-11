package ressourceManager;

import javafx.scene.image.Image;

public class ImageManager extends RessourceManager<Image>{
    @Override
    protected Image constructRessource(String source) {
        return new Image(source);
    }
}