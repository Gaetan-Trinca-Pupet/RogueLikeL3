package windowManager;

import gameComponents.GameContext;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import test.TimeEvent;
import utilities.Vector2D;

import static javafx.stage.WindowEvent.ANY;
import static test.TimeEvent.TIME_PASSES;

public class App extends Application {
    private GameContext gameContext;
    private Vector2D screen;
    private GameWindow window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        window = new GameWindow(new Vector2D(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight()));
        window.setFocusTraversable(true); //Nécessaire pour accepter les inputs claviers

        gameContext = new GameContext(window);

        root.getChildren().add(window);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCodeCombination.NO_MATCH);

        primaryStage.show();
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, this::endLoop);

    }

    public void endLoop(WindowEvent event){
        TimeEvent.loop = false;
    }


}
