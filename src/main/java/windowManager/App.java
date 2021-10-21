package windowManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        Group gameCanvas = new gameCanvas(screen.getWidth(), screen.getHeight());

        primaryStage.setScene(new Scene(gameCanvas));
        primaryStage.show();
    }


}
