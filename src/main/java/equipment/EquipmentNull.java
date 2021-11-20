package equipment;

import Inventory.Equipment;
import test.Square;
import utilities.Vector2D;

public class EquipmentNull extends Equipment {
    public EquipmentNull(){
        super(0,0,0);
        sprite = new Square();
        size = new Vector2D();
    }

    @Override
    public String info(){
        return "No equipment.";
    }
}
