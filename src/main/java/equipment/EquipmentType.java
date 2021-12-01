package equipment;

import utilities.DropRate;

public enum EquipmentType implements DropRate {
    SWORD, BOOTS;

    @Override
    public int getChance() {
        if (SWORD.equals(this)) return 10;
        else if (BOOTS.equals(this)) return 100;
        return 0;
    }
}
