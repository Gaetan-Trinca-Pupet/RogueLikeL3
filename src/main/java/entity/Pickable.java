package entity;

public abstract class Pickable extends Entity implements Interactable {
    public void interact(Player player){
        player.addToInventory(this);
    }
}
