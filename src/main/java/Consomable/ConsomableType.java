package Consomable;

import equipment.EquipmentType;
import utilities.DropRate;

public enum ConsomableType implements DropRate {
    APPLE;

    @Override
    public int getChance() {
        if (APPLE.equals(this)) return 10;
        return 0;
    }
}
