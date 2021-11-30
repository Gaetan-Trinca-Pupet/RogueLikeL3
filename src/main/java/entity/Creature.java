package entity;

import Inventory.Equipment;
import Inventory.Inventory;
import collision.CircleCollision;
import collision.Collision;
import collision.SquareCollision;
import equipment.EquipmentNull;
import javafx.scene.paint.Color;
import sprite.Sprite;
import test.Square;
import utilities.Vector2D;
import windowManager.Ground;

import java.util.ArrayList;

import static java.util.List.of;

public abstract class Creature extends Interactable{
    protected int baseMaxLife;
    protected int maxLife;

    protected int currentLife;

    protected int baseForce;
    protected int force;

    protected int baseDefense;
    protected int defense;

    protected Vector2D size;

    protected Inventory inventory;
    protected Equipment equipped;

    private ArrayList<Interactable> interactableList;
    protected Vector2D facing;

    public Creature(int baseMaxLife, int baseForce, int baseDefense){
        this.interactableList = new ArrayList<Interactable>();
        this.baseMaxLife = baseMaxLife;
        this.maxLife = baseMaxLife;
        this.currentLife = baseMaxLife;
        this.baseForce = baseForce;
        this.force = baseForce;
        this.baseDefense = baseDefense;
        this.defense = baseDefense;

        position = new Vector2D();
        facing = new Vector2D(0,1);

        this.inventory = new Inventory(this);

        equipped = new EquipmentNull();
        setEquipment(new EquipmentNull());
    }

    public Creature(){
        this(0,0,1);
    }

    public void setEquipment(Equipment equipment){
        if(equipped.getClass() != new EquipmentNull().getClass())
            inventory.add(equipped);

        equipped = equipment;

        maxLife = baseMaxLife + equipped.getLife();
        currentLife = Math.min(currentLife, maxLife);
        force = baseForce + equipped.getForce();
        defense = baseDefense + equipped.getDefense();

        inventory.computeForeGround();
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void addToLife(int difference){
        currentLife = Math.min(currentLife + difference, maxLife);
        inventory.computeForeGround();

    }

    public void attack(Creature creature){
        creature.addToLife(-Math.max(force/Math.max(creature.defense, 1), 1));
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public int getForce() {
        return force;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void setPosition(Vector2D position){
        this.position = position;
        sprite.setPosition(this.position);
        hitBox.setPosition(this.position);
    }

    public void addInteraction(Interactable... interactables){
        interactableList.addAll(of(interactables));
    }

    public void removeInteraction(Interactable... interactables){
        interactableList.removeAll(of(interactables));
    }

    public Vector2D getFacing(){
        return facing;
    }

    public Vector2D getSize(){
        return size;
    }
}
