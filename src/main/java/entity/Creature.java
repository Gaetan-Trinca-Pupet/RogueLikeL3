package entity;

import Inventory.Equipment;
import Inventory.Inventory;
import equipment.EquipmentNull;

public abstract class Creature extends Interactable{
    protected int baseMaxLife;
    protected int maxLife;

    protected int currentLife;

    protected int baseForce;
    protected int force;

    protected int baseDefense;
    protected int defense;

    protected Inventory inventory;
    protected Equipment equipped;

    public Creature(int baseMaxLife, int baseForce, int baseDefense){
        this.baseMaxLife = baseMaxLife;
        this.currentLife = baseMaxLife;
        this.baseForce = baseForce;
        this.baseDefense = baseDefense;

        setEquipment(new EquipmentNull());
    }

    public Creature(){
        this(0,0,1);
    }

    public void setEquipment(Equipment equipment){
        equipped = equipment;

        maxLife += equipped.getLife();
        force+= equipped.getForce();
        defense += equipped.getDefense();
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void addToLife(int difference){
        currentLife += Math.min(difference, maxLife);
    }

    public void attack(Creature creature){
        creature.addToLife(-Math.max(force/creature.defense, 1));
    }
}
