package gameComponents;

import Controller.Action;
import EventManager.MouseEventManager;
import collision.Collision;
import collision.SquareCollision;
import entity.Creature;
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

public class FightState extends GameState{
    private Creature player;
    private Creature monster;

    private SquareCollision attackCollision;
    private SquareCollision inventoryCollision;

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

        // Buttons
        Vector2D sizeButton = new Vector2D(200, 100);
        Sprite label;

        // Attack button
        Vector2D posAttackButton = new Vector2D(sizeButton.x * -2, 0);
        attackCollision = new SquareCollision(new Vector2D(posAttackButton), sizeButton);
        CompositeSprite attackButton = new CompositeSprite(posAttackButton);

        border = new Square(new Vector2D(-10, -10), sizeButton.add(new Vector2D(20,20)), new Color(1,1,1,1));
        centerSquare = new Square(new Vector2D(), sizeButton, new Color(0,0,0,1));
        label = new LabelSprite("Attaquer", new Color(1,1,1,1), new Vector2D(10,10));


        attackButton.add(border);
        attackButton.add(centerSquare);
        attackButton.add(label);

        // Inventory button
        Vector2D posInventoryButton = new Vector2D(sizeButton.x, 0);
        inventoryCollision = new SquareCollision(new Vector2D(posAttackButton), sizeButton);
        CompositeSprite inventoryButton = new CompositeSprite(posInventoryButton);

        border = new Square(new Vector2D(-10, -10), sizeButton.add(new Vector2D(20,20)), new Color(1,1,1,1));
        centerSquare = new Square(new Vector2D(), sizeButton, new Color(0,0,0,1));
        label = new LabelSprite("Inventaire", new Color(1,1,1,1), new Vector2D(10,10));

        inventoryButton.add(border);
        inventoryButton.add(centerSquare);
        inventoryButton.add(label);

        // Buttons
        CompositeSprite buttons = new CompositeSprite(size.multiply(new Vector2D(0, 0.25)));
        attackCollision.getPosition().add(buttons.getPosition());
        inventoryCollision.getPosition().add(buttons.getPosition());
        buttons.add(attackButton);
        buttons.add(inventoryButton);

        spriteList.addSpriteTo(Ground.FOREGROUND, buttons);



        // Player
        CompositeSprite playerSprite = new CompositeSprite(player.getSprite().getPosition().multiply(new Vector2D(-1,-1)));
        playerSprite.add(player.getSprite());
        playerSprite.setPosition(playerSprite.getPosition().add(new Vector2D()));
        spriteList.addSpriteTo(Ground.FOREGROUND, playerSprite);

        // Monster
        Sprite monsterSprite = monster.getSprite();
        monsterSprite.setPosition(new Vector2D(size).divideBy(new Vector2D(-8,-4)));
        spriteList.addSpriteTo(Ground.FOREGROUND, monsterSprite);

        spriteList.addHandlerToGround(Ground.BACKGROUND, lastState.getSpriteList());
    }

    @Override
    public void keyboardEvent(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED){
            if(event.getCode() == controller.keyCodeForAction(Action.ESCAPE))
                backToLastContext();

            if(event.getCode() == controller.keyCodeForAction(Action.INVENTORY))
                gameContext.setState(new InventoryState(player, gameContext, this));
        }
    }

    @Override
    public void mouseEvent(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
            player.getInventory().eventController(controller);
        for(MouseEventManager mouseEvent : mouseEventList)
            mouseEvent.mouseEvent(event);
        System.out.println(attackCollision.isInside(controller.getMousePosition()));
        System.out.println(inventoryCollision.isInside(controller.getMousePosition()));
    }

    @Override
    public void updateOnTimeEvent(TimeEvent event) {
        gameContext.gameWindow.paintAll(spriteList);
    }
}
