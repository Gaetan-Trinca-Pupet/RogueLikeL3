package gameComponents;

import entity.Creature;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FightState extends GameState{
    private Creature player;
    private Creature monster;

    public FightState(GameContext gameContext, GameState lastState, Creature player, Creature monster){
        super(gameContext, lastState);

        this.player = player;
        this.monster = monster;
    }

    @Override
    public void keyboardEvent(KeyEvent event) {

    }

    @Override
    public void mouseEvent(MouseEvent event) {

    }

    @Override
    public void update() {

    }
}
