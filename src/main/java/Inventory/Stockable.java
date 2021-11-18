package Inventory;

import javafx.scene.image.Image;
import ressourceManager.ImageManager;
import sprite.Sprite;
import utilities.Vector2D;

public abstract class Stockable {
    static protected ImageManager imageManager = new ImageManager();

    public void addSelfToInventory(Inventory inventory){
        inventory.add(this);
    }

    protected Sprite sprite;
    protected Vector2D size;

    public Sprite getSprite(){
        return sprite;
    }

    public Vector2D getSize(){
        return size;
    }

    public abstract String info();
}
