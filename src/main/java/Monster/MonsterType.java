package Monster;

import equipment.EquipmentType;
import utilities.DropRate;

public enum MonsterType implements DropRate {
    WOLF, BEAR;

    @Override
    public int getChance() {
        if (WOLF.equals(this)) return 10;
        else if (BEAR.equals(this)) return 20;
        return 0;
    }
}
