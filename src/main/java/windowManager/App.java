package windowManager;

import gameComponents.Game;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Game RogueLike;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        RogueLike = new Game(primaryStage);

        RogueLike.configure();
        RogueLike.startGame();
        //RogueLike.endGame();
    }


}
