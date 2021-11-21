package Inventory;

import EventManager.MouseEventManager;
import collision.Collision;
import collision.SquareCollision;
import entity.Entity;
import gameComponents.GameContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sprite.CompositeSprite;
import sprite.Sprite;
import test.Square;
import utilities.Updatable;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.security.cert.CertificateNotYetValidException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory implements Updatable, MouseEventManager {
    private ArrayList<Stockable> inventory;
    private ArrayList<Entity> entities;

    private SpriteHandler spriteHandler;

    private final int sizeItem = 100;
    public final Vector2D sizeInventory = new Vector2D(600,600);

    public Inventory(){
        entities = new ArrayList<Entity>();
        inventory = new ArrayList<Stockable>();
        spriteHandler = new SpriteHandler();
    }

    public void add(Stockable... stockables){
        inventory.addAll(List.of(stockables));
        computeSpriteHandler();
    }

    public void remove(Stockable... stockables){
        inventory.removeAll(List.of(stockables));
        computeSpriteHandler();
    }

    public void computeSpriteHandler(){
        spriteHandler.clean(Ground.GROUND);
        final int nRow = (int) ((sizeInventory.y - sizeItem) / sizeItem);
        final int nColumn = (int) ((sizeInventory.x - sizeItem) / sizeItem);

        for(int i = 0 ; ( i < nRow * nColumn ) && i < inventory.size() ; ++i){
            int column = i % nColumn;
            int row = i / nColumn;

            double posX = (-sizeInventory.x/2) + sizeItem/nColumn + ((sizeItem + (sizeItem/nColumn)) * column);
            double posY = (-sizeInventory.y/2) + sizeItem/nRow + ((sizeItem + (sizeItem/nColumn)) * row);

            posX += inventory.get(i).size.x / 2;
            posY += inventory.get(i).size.y / 2;

            CompositeSprite sprite = new CompositeSprite(new Vector2D(posX, posY).subtract(inventory.get(i).getSprite().getPosition()));

            sprite.add(inventory.get(i).getSprite());

            spriteHandler.addSpriteTo(Ground.GROUND, sprite);
        }
    }

    public SpriteHandler getSpriteHandler(){
        return spriteHandler;
    }

    @Override
    public void update() {
        for(Entity entity : entities)
            entity.update();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        //todo si on clique dans une section, utiliser le use de l'objet, et le supprimer de l'inventaire
//        Collision collision = new SquareCollision(new Vector2D(x,y), new Vector2D(10,10));
//        collision.isInside(new Vector2D(event.getX(), event.getY()));
    }
}
