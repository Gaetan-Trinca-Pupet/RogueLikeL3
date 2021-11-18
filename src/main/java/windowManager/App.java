package windowManager;

import gameComponents.GameContext;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utilities.Vector2D;

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
        primaryStage.show();
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, gameContext::closeGame);
    }


}
