package entity;

import collision.SquareCollision;
import gameComponents.FightState;
import Monster.Fightable;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;

public class Boss extends Creature {
    private static Creature target;
    private Fightable self;

    public static void setTarget(Creature creature){
        target = creature;
    }

    public Boss(Fightable fightable){
        super(fightable.getLife(), fightable.getForce(), fightable.getDefense());
        self = fightable;
        sprite = fightable.getSprite();
        sprite.setPosition(position);
        size = fightable.getSize();

        hitBox = new SquareCollision(position, size);

        addSpriteTo(Ground.GROUND);
    }

    public void attack(){
        if(target.getHitBox().isInside(position.add(size.divideBy(new Vector2D(2,2)))))
            this.interact(target);
    }

    @Override
    public void interact(Creature creature) {
        game.setState(new FightState(game, game.getState(), creature, this));
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        attack();
    }
}
