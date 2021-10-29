package gameComponents;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import test.Square;
import utilities.Vector2D;
import windowManager.GameCanvas;

public class Game {
    private Stage primaryStage;
    private Vector2D screen;
    private GameCanvas gameCanvas;


    public Game(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.gameCanvas = new GameCanvas();
    }

    public void configure(){
        screen = new Vector2D(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        gameCanvas = new GameCanvas(screen);

        primaryStage.setScene(new Scene(gameCanvas));
        primaryStage.show();
    }

    public void startGame(){
        //TODO
        //Exemple :
        gameCanvas.getBackground().add(new Square(new Vector2D(0,0), new Vector2D(50,50), new Color(0.5, 0.5, 1, 1)));
        gameCanvas.getBackground().add(0, new Square(new Vector2D(-5,-5), new Vector2D(50,50), new Color(1, 0.5, 0.5, 1)));

        gameCanvas.getGround().add(new Square(new Vector2D(-20,-10), new Vector2D(50,50), new Color(0, 0, 1, 1)));
        gameCanvas.getForeground().add(new Square(new Vector2D(-10,-15), new Vector2D(50,50), new Color(0, 1, 0, 1)));


        gameCanvas.paintAllCanvas();;
    }

    public void endGame(){
        primaryStage.close();
    }
}
