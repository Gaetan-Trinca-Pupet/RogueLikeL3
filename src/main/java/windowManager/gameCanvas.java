package windowManager;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import utilities.Vector2D;

public class gameCanvas extends Group {
    private Vector2D screenSize;

    private Canvas Background;
    private Canvas ground;
    private Canvas Foreground;
    private Canvas HUD;

    public gameCanvas(double width, double height) {
        screenSize = new Vector2D(width, height);
        

        Background = new Canvas(width, height);
        ground = new Canvas(width, height);
        Foreground = new Canvas(width, height);
        HUD = new Canvas(width, height);

        this.getChildren().add(Background);
        this.getChildren().add(ground);
        this.getChildren().add(Foreground);
        this.getChildren().add(HUD);

        Vector2D rect = new Vector2D(0,0);
        ground.getGraphicsContext2D().fillRect(rect.x + screenSize.x/2, rect.y + screenSize.y/2, 100, 100);
    }

    public void paintAll(){

    }
}
