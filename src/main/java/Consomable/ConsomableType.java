package Consomable;

import equipment.EquipmentType;
import utilities.DropRate;

public enum ConsomableType implements DropRate {
    APPLE, POISON, POTION, SANDWICH;

    @Override
    public int getChance() {
        if (APPLE.equals(this)) return 10;
        else if (POISON.equals(this)) return 50;
        else if (POTION.equals(this)) return 30;
        else if (SANDWICH.equals(this)) return 15;
        return 0;
    }
}
