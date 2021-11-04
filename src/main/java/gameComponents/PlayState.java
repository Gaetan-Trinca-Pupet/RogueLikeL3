package gameComponents;

import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import test.Square;
import utilities.Vector2D;

public class PlayState implements GameState{
    private Stage primaryStage;
    private GameContext gameContext;


    public PlayState(GameContext gameContext){
        this.gameContext = gameContext;
        startGame();
        addEventHandlers();
    }

    public void startGame(){
        //TODO
        //Exemple :
        gameContext.gameWindow.getBackground().add(new Square(new Vector2D(0,0), new Vector2D(50,50), new Color(0.5, 0.5, 1, 1)));
        gameContext.gameWindow.getBackground().add(0, new Square(new Vector2D(-5,-5), new Vector2D(50,50), new Color(1, 0.5, 0.5, 1)));

        gameContext.gameWindow.getGround().add(new Square(new Vector2D(-20,-10), new Vector2D(50,50), new Color(0, 0, 1, 1)));
        gameContext.gameWindow.getForeground().add(new Square(new Vector2D(-10,-15), new Vector2D(50,50), new Color(0, 1, 0, 1)));

        gameContext.gameWindow.paintAll();
    }

    //Exemple gestion des events
    public void addEventHandlers(){
        gameContext.gameWindow.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onClick);
    }

    public void onClick(Event event) {
        gameContext.gameWindow.getHUD().add(new Square(new Vector2D(50, -50), new Vector2D(50, 5), new Color(0, 0.2, 0.4, 1)));
        gameContext.gameWindow.paintAll();
    }

    public void endGame(){
        primaryStage.close();
    }

    @Override
    public void update() {
        gameContext.gameWindow.paintAll();
    }

    @Override
    public void handle(Event event) {
        System.out.println(event);
    }
}
