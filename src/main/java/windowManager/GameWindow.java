package windowManager;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import utilities.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;

public class GameWindow extends Canvas {
    private Vector2D screenSize;
    private Vector2D screenCenter;

    private ArrayList<Sprite> background;
    private ArrayList<Sprite> ground;
    private ArrayList<Sprite> foreground;
    private ArrayList<Sprite> HUD;

    public GameWindow(Vector2D screenSize) {
        super(screenSize.x, screenSize.y);
        this.screenSize = screenSize;
        screenCenter = new Vector2D(screenSize.x/2, screenSize.y/2);


        background = new ArrayList<>();
        ground = new ArrayList<>();
        foreground = new ArrayList<>();
        HUD = new ArrayList<>();
    }

    public GameWindow(double width, double height) {
        this(new Vector2D(width, height));
    }

    public GameWindow(){
        this(0,0);
    }

    public void paintAll(){
        for (int i = background.size()-1 ; i >=0 ; --i){
            background.get(i).computeDrawerPosition(screenCenter);
            background.get(i).drawSelfOnto(getGraphicsContext2D());
        }

        for (int i = ground.size()-1 ; i >=0 ; --i){
            ground.get(i).computeDrawerPosition(screenCenter);
            ground.get(i).drawSelfOnto(getGraphicsContext2D());
        }

        for (int i = foreground.size()-1 ; i >=0 ; --i){
            foreground.get(i).computeDrawerPosition(screenCenter);
            foreground.get(i).drawSelfOnto(getGraphicsContext2D());
        }

        for (int i = HUD.size()-1 ; i >=0 ; --i){
            HUD.get(i).computeDrawerPosition(screenCenter);
            HUD.get(i).drawSelfOnto(getGraphicsContext2D());
        }
    }

    public Vector2D getScreenCenter() {
        return screenCenter;
    }

    public ArrayList<Sprite> getBackground() {
        return background;
    }

    public ArrayList<Sprite> getGround() {
        return ground;
    }

    public ArrayList<Sprite> getForeground() {
        return foreground;
    }

    public ArrayList<Sprite> getHUD() {
        return HUD;
    }
}