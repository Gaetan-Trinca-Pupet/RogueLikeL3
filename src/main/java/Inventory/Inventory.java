package Inventory;

import Controller.MouseAndKeyboardController;
import EventManager.MouseEventManager;
import collision.Collision;
import collision.SquareCollision;
import entity.Creature;
import entity.Entity;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sprite.CompositeSprite;
import sprite.LabelSprite;
import sprite.Sprite;
import test.Square;
import utilities.Updatable;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Updatable, MouseEventManager {
    private ArrayList<Stockable> inventory;
    private ArrayList<Entity> entities;

    private SpriteHandler spriteHandler;

    private final int sizeItem = 100;
    public final Vector2D sizeInventory = new Vector2D(600,600);

    private Creature possessor;

    public Inventory(Creature possessor){
        this.possessor = possessor;
        entities = new ArrayList<Entity>();
        inventory = new ArrayList<Stockable>();
        spriteHandler = new SpriteHandler();

        drawFinalElement();
        computeForeGround();
    }

    private void drawFinalElement(){
        Vector2D pos = new Vector2D(sizeInventory).divideBy(new Vector2D(-2, -2));

        Sprite border = new Square(pos.subtract(new Vector2D(10,10)), sizeInventory.add(new Vector2D(20,20)), new Color(1,1,1,1));
        Sprite centerSquare = new Square(pos, sizeInventory, new Color(0,0,0,1));

        spriteHandler.addSpriteTo(Ground.BACKGROUND, border);
        spriteHandler.addSpriteTo(Ground.BACKGROUND, centerSquare);
    }

    public void computeForeGround(){
        spriteHandler.clean(Ground.FOREGROUND);

        CompositeSprite information = new CompositeSprite(new Vector2D(sizeInventory).add(new Vector2D(0,20)).divideBy(new Vector2D(2, -2)));
        final int rectH = 50;
        final int rectW = 200;

        CompositeSprite box = new CompositeSprite(new Vector2D(0,0));
        String str = "PV : " + possessor.getCurrentLife() + " / " + possessor.getMaxLife();
        Sprite text = new LabelSprite(str, new Color(1,1,1,1), new Vector2D(30,rectH/1.5));
        Sprite rectangle = new Square(new Vector2D(10,10), new Vector2D(rectW,rectH));
        Sprite outRectangle = new Square(new Vector2D(0,0), new Vector2D(rectW + 20,rectH + 20), new Color(1,1,1,1));
        box.add(outRectangle);
        box.add(rectangle);
        box.add(text);
        information.add(box);

        box = new CompositeSprite(new Vector2D(0,rectH));
        str = "Force : " + possessor.getForce();
        text = new LabelSprite(str, new Color(1,1,1,1), new Vector2D(30,rectH/1.5));
        rectangle = new Square(new Vector2D(10,10), new Vector2D(rectW,rectH));
        outRectangle = new Square(new Vector2D(0,0), new Vector2D(rectW + 20,rectH + 20), new Color(1,1,1,1));
        box.add(outRectangle);
        box.add(rectangle);
        box.add(text);
        information.add(box);

        box = new CompositeSprite(new Vector2D(0,rectH * 2));
        str = "Defense : " + possessor.getDefense();
        text = new LabelSprite(str, new Color(1,1,1,1), new Vector2D(30,rectH/1.5));
        rectangle = new Square(new Vector2D(10,10), new Vector2D(rectW,rectH));
        outRectangle = new Square(new Vector2D(0,0), new Vector2D(rectW + 20,rectH + 20), new Color(1,1,1,1));
        box.add(outRectangle);
        box.add(rectangle);
        box.add(text);
        information.add(box);

        spriteHandler.addSpriteTo(Ground.FOREGROUND, information);
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

            double posX = (-sizeInventory.x/2) + sizeItem/2 + ((sizeItem + (sizeItem/nColumn)) * column);
            double posY = (-sizeInventory.y/2) + sizeItem/2 + ((sizeItem + (sizeItem/nRow)) * row);

            posX -= inventory.get(i).size.x / 2;
            posY -= inventory.get(i).size.y / 2;

            Vector2D pos = new Vector2D(posX, posY).subtract(inventory.get(i).getSprite().getPosition());

            CompositeSprite sprite = new CompositeSprite(pos);

            LabelSprite label = new LabelSprite(inventory.get(i).info(), new Color(1,1,1,1));
            label.setPosition(inventory.get(i).getSprite().getPosition().add(new Vector2D(0,sizeItem)));

            sprite.add(label);

            sprite.add(inventory.get(i).getSprite());

            spriteHandler.addSpriteTo(Ground.GROUND, sprite);
        }
    }

    public SpriteHandler getSpriteHandler(){
        return spriteHandler;
    }

    public Stockable getClickedItem(Vector2D mousePos){
        final int nRow = (int) ((sizeInventory.y - sizeItem) / sizeItem);
        final int nColumn = (int) ((sizeInventory.x - sizeItem) / sizeItem);

        for(int i = 0 ; ( i < nRow * nColumn ) && i < inventory.size() ; ++i){
            int column = i % nColumn;
            int row = i / nColumn;

            double posX = (-sizeInventory.x/2) + sizeItem/2 + ((sizeItem + (sizeItem/nColumn)) * column);
            double posY = (-sizeInventory.y/2) + sizeItem/2 + ((sizeItem + (sizeItem/nRow)) * row);

            posX -= sizeItem / 2;
            posY -= sizeItem / 2;

            Collision itemCollision = new SquareCollision(new Vector2D(posX, posY), new Vector2D(sizeItem, sizeItem));

            if(itemCollision.isInside(mousePos))
                return inventory.get(i);
        }
        return null;
    }

    public Creature getPossessor(){
        return possessor;
    }

    @Override
    public void update() {
        //for(Entity entity : entities)
        //    entity.update();
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        //todo si on clique dans une section, utiliser le use de l'objet, et le supprimer de l'inventaire
//        Collision collision = new SquareCollision(new Vector2D(x,y), new Vector2D(10,10));
//        collision.isInside(new Vector2D(event.getX(), event.getY()));
    }

    public void eventController(MouseAndKeyboardController controller){
        Stockable item = getClickedItem(new Vector2D(controller.getMousePosition()));
        if(item != null) {
            item.use(possessor);
            inventory.remove(item);
            computeSpriteHandler();
        }
    }
}
