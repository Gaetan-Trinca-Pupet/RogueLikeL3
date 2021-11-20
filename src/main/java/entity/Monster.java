package entity;

import gameComponents.FightState;

public class Monster extends Creature {

    @Override
    public void interact(Player player) {
        game.setState(new FightState(game, game.getState(), player, this));
    }

    @Override
    public void update() {

    }
}
