package entity;

import collision.SquareCollision;
import gameComponents.FightState;
import Monster.Fightable;
import test.TimeEvent;
import windowManager.Ground;

public class Monster extends Creature {
    public Monster(Fightable fightable){
        super(fightable.getLife(), fightable.getForce(), fightable.getDefense());
        sprite = fightable.getSprite();
        sprite.setPosition(position);
        size = fightable.getSize();

        hitBox = new SquareCollision(position, size);

        addSpriteTo(Ground.GROUND);
    }

    @Override
    public void interact(Creature creature) {
        game.setState(new FightState(game, game.getState(), creature, this));
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {

    }
}
