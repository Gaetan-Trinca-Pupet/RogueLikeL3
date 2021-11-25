package gameComponents;

import Consomable.Apple;
import Controller.Action;
import EventManager.MouseEventManager;
import collision.Collision;
import collision.SquareCollision;
import entity.Creature;
import entity.Pickable;
import equipment.RogueBoots;
import equipment.Sword;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sprite.CompositeSprite;
import sprite.LabelSprite;
import sprite.Sprite;
import test.Square;
import test.TimeEvent;
import utilities.Vector2D;
import windowManager.Ground;
import windowManager.SpriteHandler;

import java.util.Random;

public class FightState extends GameState{
    private Creature player;
    private Creature monster;

    private SquareCollision attackCollision;
    private SquareCollision inventoryCollision;

    private LabelSprite HPplayer;
    private LabelSprite HPmonster;

    public FightState(GameContext gameContext, GameState lastState, Creature player, Creature monster){
        super(gameContext, lastState);

        this.player = player;
        this.monster = monster;

        spriteList = new SpriteHandler();
        Vector2D screenSize = gameContext.gameWindow.getScreenSize();
        spriteList.addSpriteTo(Ground.GROUND, new Square(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));

        // Main box
        Vector2D size = new Vector2D(1200,800);
        Vector2D pos = new Vector2D(size).divideBy(new Vector2D(-2, -2));
        Sprite border = new Square(pos.subtract(new Vector2D(10,10)), size.add(new Vector2D(20,20)), new Color(1,1,1,1));
        Sprite centerSquare = new Square(pos, size, new Color(0,0,0,1));

        spriteList.addSpriteTo(Ground.GROUND, border);
        spriteList.addSpriteTo(Ground.GROUND, centerSquare);

        // Buttons size and label
        Vector2D sizeButton = new Vector2D(200, 100);
        LabelSprite label;

        // Attack button
        Vector2D posAttackButton = new Vector2D(sizeButton.x * -2, 0);
        attackCollision = new SquareCollision(new Vector2D(posAttackButton), sizeButton);
        CompositeSprite attackButton = new CompositeSprite(posAttackButton);

        border = new Square(new Vector2D(-10, -10), sizeButton.add(new Vector2D(20,20)), new Color(1,1,1,1));
        centerSquare = new Square(new Vector2D(), sizeButton, new Color(0,0,0,1));
        label = new LabelSprite("Attaquer", new Color(1,1,1,1), sizeButton.divideBy(new Vector2D(3,2)));
        label.setSize(20);

        attackButton.add(border);
        attackButton.add(centerSquare);
        attackButton.add(label);

        // Inventory button
        Vector2D posInventoryButton = new Vector2D(sizeButton.x, 0);
        inventoryCollision = new SquareCollision(new Vector2D(posInventoryButton), sizeButton);
        CompositeSprite inventoryButton = new CompositeSprite(posInventoryButton);

        border = new Square(new Vector2D(-10, -10), sizeButton.add(new Vector2D(20,20)), new Color(1,1,1,1));
        centerSquare = new Square(new Vector2D(), sizeButton, new Color(0,0,0,1));
        label = new LabelSprite("Inventaire", new Color(1,1,1,1), sizeButton.divideBy(new Vector2D(3,2)));
        label.setSize(20);

        inventoryButton.add(border);
        inventoryButton.add(centerSquare);
        inventoryButton.add(label);

        // Buttons
        CompositeSprite buttons = new CompositeSprite(size.multiply(new Vector2D(0, 0.25)));
        attackCollision.getPosition().y += buttons.getPosition().y;
        inventoryCollision.getPosition().y += buttons.getPosition().y;
        buttons.add(attackButton);
        buttons.add(inventoryButton);

        System.out.println(attackButton.getPosition() + " | " + inventoryButton.getPosition());

        spriteList.addSpriteTo(Ground.FOREGROUND, buttons);



        // Player
        CompositeSprite playerSprite = new CompositeSprite(player.getSprite().getPosition().multiply(new Vector2D(-1,-1)));
        playerSprite.add(player.getSprite());
        playerSprite.setPosition(playerSprite.getPosition().add(attackButton.getPosition().subtract(new Vector2D(0,  200))));
        spriteList.addSpriteTo(Ground.FOREGROUND, playerSprite);
        HPplayer = new LabelSprite("HP : " + player.getCurrentLife() + " / " + player.getMaxLife(), Color.WHITE, playerSprite.getPosition().add(new Vector2D(0,200).add(player.getSprite().getPosition())));
        spriteList.addSpriteTo(Ground.GROUND, HPplayer);

        // Monster
        CompositeSprite monsterSprite = new CompositeSprite(monster.getSprite().getPosition().multiply(new Vector2D(-1,-1)));
        monsterSprite.add(monster.getSprite());
        monsterSprite.setPosition(monsterSprite.getPosition().add(inventoryButton.getPosition().subtract(new Vector2D(0,  200))));
        spriteList.addSpriteTo(Ground.FOREGROUND, monsterSprite);
        HPmonster = new LabelSprite("HP : " + monster.getCurrentLife() + " / " + monster.getMaxLife(), Color.WHITE, monsterSprite.getPosition().add(new Vector2D(0,200).add(monster.getSprite().getPosition())));
        spriteList.addSpriteTo(Ground.FOREGROUND, HPmonster);

        spriteList.addHandlerToGround(Ground.BACKGROUND, lastState.getSpriteList());

        HPplayer.setSize(20);
        HPmonster.setSize(20);
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            if(attackCollision.isInside(controller.getMousePosition())){
                turn(Turn.ATTACK);
            }
            if(inventoryCollision.isInside(controller.getMousePosition())){
                turn(Turn.INVENTORY);
            }
        }
        for(MouseEventManager mouseEvent : mouseEventList)
            mouseEvent.mouseEvent(event);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        HPplayer.setText("HP : " + player.getCurrentLife() + " / " + player.getMaxLife());
        HPmonster.setText("HP : " + monster.getCurrentLife() + " / " + monster.getMaxLife());
        gameContext.gameWindow.paintAll(spriteList);
    }

    private enum Turn{
        ATTACK, INVENTORY
    }

    public void turn(Turn action){
        switch (action){
            case ATTACK :
                player.attack(monster);
                break;
            case INVENTORY :
                InventoryState inventoryState = new InventoryState(player, gameContext, this);
                inventoryState.setCanUseMultipleItem(false);
                gameContext.setState(inventoryState);
                break;
        }
        if(monster.getCurrentLife() > 0) monster.attack(player);
        else {

            Random random = new Random();
            if (random.nextInt(2) == 0) { // 1 chance sur 2 pour le monstre de drop une pomme
                Pickable pom = new Pickable(new Apple());
                pom.setPosition(monster.getPosition());
                player.addInteraction(pom);
            }
            else if (random.nextInt(3) == 0) { // 1 chance sur 3 pour le monstre de drop une épée
                Pickable epee = new Pickable(new Sword());
                epee.setPosition(monster.getPosition());
                player.addInteraction(epee);
            }
            else if (random.nextInt(10) == 0) { // 1 chance sur 10 pour le monstre de drop LES BOTTES
                Pickable boots = new Pickable(new RogueBoots());
                boots.setPosition(monster.getPosition());
                player.addInteraction(boots);
            }

            backToLastContext();

        }
        if(player.getCurrentLife() < 0)
            gameContext.setState(new GameOverState(gameContext, this));
    }
}
