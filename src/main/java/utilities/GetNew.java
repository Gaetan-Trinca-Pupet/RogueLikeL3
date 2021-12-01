package utilities;

import entity.Monster;
import Monster.*;
import Consomable.*;
import equipment.*;
import entity.Pickable;

import java.util.HashMap;
import java.util.Random;

public class GetNew {

    public static MonsterType randomMonsterType(){
        for(Random random = new Random() ; ; )
            for (MonsterType type : MonsterType.values())
                if(random.nextInt(type.getChance()) == 0)
                    return type;
    }

    public static Monster monster(MonsterType type){
        switch (type){
            case BEAR : return new Monster(new Bear());
            case WOLF : return new Monster(new Wolf());
        }
        return null;
    }

    public static ConsomableType randomConsomableType(){
        for(Random random = new Random() ; ; )
            for (ConsomableType type : ConsomableType.values())
                if(random.nextInt(type.getChance()) == 0)
                    return type;
    }

    public static Pickable consomable(ConsomableType type){
        switch (type){
            case APPLE : return new Pickable(new Apple());
        }
        return null;
    }

    public static EquipmentType randomEquipmentType(){
        for(Random random = new Random() ; ; )
            for (EquipmentType type : EquipmentType.values())
                if(random.nextInt(type.getChance()) == 0)
                    return type;
    }

    public static Pickable equipment(EquipmentType type){
        switch (type){
            case SWORD : return new Pickable(new Sword());
            case BOOTS : return new Pickable(new RogueBoots());
        }
        return null;
    }
}
