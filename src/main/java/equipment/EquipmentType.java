package equipment;

import utilities.DropRate;

public enum EquipmentType implements DropRate {
    SWORD, BOOTS, ARMOR, DAGGER ;

    @Override
    public int getChance() {
        if (SWORD.equals(this)) return 10;
        else if (DAGGER.equals(this)) return 20;
        else if (ARMOR.equals(this)) return 30;
        else if (BOOTS.equals(this)) return 100;
        return 0;
    }
}
