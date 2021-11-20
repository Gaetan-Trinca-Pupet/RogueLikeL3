package Inventory;

import entity.Creature;

public abstract class Equipment extends Stockable{
    protected int life;
    protected int force;
    protected int defense;

    public Equipment(int life, int force, int defense){
        this.life = life;
        this.force = force;
        this.defense = defense;
    }

    public Equipment(){
        this(0,0,0);
    }

    @Override
    public void use(Creature creature){
        creature.setEquipment(this);
    }

    public int getLife(){
        return life;
    }

    public int getForce(){
        return force;
    }

    public int getDefense(){
        return defense;
    }


    public void setLife(int life){
        this.life = life;
    }

    public void setForce(int force){
        this.force = force;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }
}
