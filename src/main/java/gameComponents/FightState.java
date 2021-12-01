package gameComponents;

import Consomable.Apple;
import EventManager.MouseEventManager;
import collision.SquareCollision;
import entity.Creature;
import entity.Entity;
import entity.Monster;
import Monster.Lion;
import entity.Pickable;
import equipment.RogueBoots;
import equipment.Sword;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sprite.CompositeSprite;
import sprite.LabelSprite;
import sprite.Sprite;
import sprite.SquareSprite;
import test.TimeEvent;
import utilities.GetNew;
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
        spriteList.addSpriteTo(Ground.GROUND, new SquareSprite(screenSize.divideBy(new Vector2D(-2,-2)),screenSize, new Color(0,0,0,0.5)));

        // Main box
        Vector2D size = new Vector2D(1200,800);
        Vector2D pos = new Vector2D(size).divideBy(new Vector2D(-2, -2));
        Sprite border = new SquareSprite(pos.subtract(new Vector2D(10,10)), size.add(new Vector2D(20,20)), new Color(1,1,1,1));
        Sprite centerSquare = new SquareSprite(pos, size, new Color(0,0,0,1));

        spriteList.addSpriteTo(Ground.GROUND, border);
        spriteList.addSpriteTo(Ground.GROUND, centerSquare);

        // Buttons size and label
        Vector2D sizeButton = new Vector2D(200, 100);
        LabelSprite label;

        // Attack button
        Vector2D posAttackButton = new Vector2D(sizeButton.x * -2, 0);
        attackCollision = new SquareCollision(new Vector2D(posAttackButton), sizeButton);
        CompositeSprite attackButton = new CompositeSprite(posAttackButton);

        border = new SquareSprite(new Vector2D(-10, -10), sizeButton.add(new Vector2D(20,20)), new Color(1,1,1,1));
        centerSquare = new SquareSprite(new Vector2D(), sizeButton, new Color(0,0,0,1));
        label = new LabelSprite("Attaquer", new Color(1,1,1,1), sizeButton.divideBy(new Vector2D(3,2)));
        label.setSize(20);

        attackButton.add(border);
        attackButton.add(centerSquare);
        attackButton.add(label);

        // Inventory button
        Vector2D posInventoryButton = new Vector2D(sizeButton.x, 0);
        inventoryCollision = new SquareCollision(new Vector2D(posInventoryButton), sizeButton);
        CompositeSprite inventoryButton = new CompositeSprite(posInventoryButton);

        border = new SquareSprite(new Vector2D(-10, -10), sizeButton.add(new Vector2D(20,20)), new Color(1,1,1,1));
        centerSquare = new SquareSprite(new Vector2D(), sizeButton, new Color(0,0,0,1));
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

    public void paint(){
        HPplayer.setText("HP : " + player.getCurrentLife() + " / " + player.getMaxLife());
        HPmonster.setText("HP : " + monster.getCurrentLife() + " / " + monster.getMaxLife());
        gameContext.gameWindow.paintAll(spriteList);
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        paint();
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
        else if (monster.getMaxLife() == 200) gameContext.setState(new WinState(gameContext, this));
        else {
            monster.getCurrentRoom().getSpriteHandler().removeSpriteTo(Ground.GROUND, monster.getSprite());
            monster.getCurrentRoom().removeEntity(monster);
            monster.getCurrentRoom().removeInteractionFrom(player, monster);
            Random random = new Random();
            Pickable drop = null;
            Entity.setSpriteHandler(monster.getCurrentRoom().getSpriteHandler());
            if (random.nextInt(3) != 0) { // 1 chance sur 3 d'avoir un consomable
                drop = GetNew.consomable(GetNew.randomConsomableType());
            }
            else if (random.nextInt(2) == 0) { // 1 chance sur 6 pour le monstre de drop un equipment
                drop = GetNew.equipment(GetNew.randomEquipmentType());
            }

            if(drop != null) {
                System.out.println("Objet : " + drop);
                drop.setPosition(monster.getPosition().add(monster.getSize().divideBy(new Vector2D(2,2))));
                monster.getCurrentRoom().addEntity(drop);
                monster.getCurrentRoom().addInterractionTo(player, drop);
            }

            backToLastContext();

        }
        if(player.getCurrentLife() <= 0) {
            paint();
            gameContext.setState(new GameOverState(gameContext, this));
        }
    }
}
