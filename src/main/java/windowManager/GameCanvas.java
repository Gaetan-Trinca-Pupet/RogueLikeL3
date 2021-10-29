package windowManager;

import javafx.scene.Group;
import utilities.Vector2D;

public class GameCanvas extends Group {
    private Vector2D screenSize;
    private Vector2D screenCenter;

    private GroupCanvas background;
    private GroupCanvas ground;
    private GroupCanvas foreground;
    private GroupCanvas HUD;

    public GameCanvas(Vector2D screenSize) {
        this.screenSize = screenSize;
        screenCenter = new Vector2D(screenSize.x/2, screenSize.y/2);
        

        background = new GroupCanvas(screenSize, screenCenter);
        ground = new GroupCanvas(screenSize, screenCenter);
        foreground = new GroupCanvas(screenSize, screenCenter);
        HUD = new GroupCanvas(screenSize, screenCenter);

        this.getChildren().add(background);
        this.getChildren().add(ground);
        this.getChildren().add(foreground);
        this.getChildren().add(HUD);
    }

    public GameCanvas(double width, double height) {
        this(new Vector2D(width, height));
    }

    public GameCanvas(){
        this(0,0);
    }

    public void paintAllCanvas(){
        background.paintAllSprites();
        ground.paintAllSprites();
        foreground.paintAllSprites();
        HUD.paintAllSprites();
    }

    public GroupCanvas getBackground() {
        return background;
    }

    public GroupCanvas getGround() {
        return ground;
    }

    public GroupCanvas getForeground() {
        return foreground;
    }

    public GroupCanvas getHUD() {
        return HUD;
    }
}
