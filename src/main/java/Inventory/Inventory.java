package Inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Stockable> inventory;

    public Inventory(){
        inventory = new ArrayList<Stockable>();
    }

    public void add(Stockable... stockables){
        inventory.addAll(List.of(stockables));
    }

    public void remove(Stockable... stockables){
        inventory.removeAll(List.of(stockables));
    }
}
