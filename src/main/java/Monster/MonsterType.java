package Monster;

import equipment.EquipmentType;
import utilities.DropRate;

public enum MonsterType implements DropRate {
    WOLF, BEAR, FOX, FROG, GOKU, LANDMINE, MICE, RABBIT, TOAD;

    @Override
    public int getChance() {
        if (WOLF.equals(this)) return 10;
        else if (BEAR.equals(this)) return 30;
        else if (FOX.equals(this)) return 25;
        else if (FROG.equals(this)) return 20;
        else if (GOKU.equals(this)) return 50;
        else if (LANDMINE.equals(this)) return 20;
        else if (MICE.equals(this)) return 15;
        else if (RABBIT.equals(this)) return 20;
        else if (TOAD.equals(this)) return 30;
        return 0;
    }
}
