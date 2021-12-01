package entity;

import collision.SquareCollision;
import gameComponents.FightState;
import Monster.Fightable;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;

public class Monster extends Creature {
    private static Creature target;
    private Fightable self;

    public static void setTarget(Creature creature){
        target = creature;
    }

    public Monster(Fightable fightable){
        super(fightable.getLife(), fightable.getForce(), fightable.getDefense());
        self = fightable;
        sprite = fightable.getSprite();
        sprite.setPosition(position);
        size = fightable.getSize();

        hitBox = new SquareCollision(position, size);

        addSpriteTo(Ground.GROUND);
    }

    private void moveToTarget(float deltaTime){
        float speed = self.getSpeed() * deltaTime;
        Vector2D posTarget = target.getPosition().add(target.getSize().divideBy(new Vector2D(2,2)));
        Vector2D selfPos = position.add(size.divideBy(new Vector2D(2,2)));

        double opp = Math.abs(posTarget.y - selfPos.y);
        double adj = Math.abs(posTarget.x - selfPos.x);
        double angle = Math.atan(opp/adj);

        Vector2D translate = new Vector2D();
        translate.x = Math.cos(angle) * speed;
        translate.y = Math.sin(angle) * speed;

        if(posTarget.x < selfPos.x) translate.x *= -1;
        if(posTarget.y < selfPos.y) translate.y *= -1;

        translate(translate);
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
        moveToTarget(event.getDeltaTime());
        attack();
    }
}
