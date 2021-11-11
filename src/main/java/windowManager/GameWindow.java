package windowManager;

import javafx.scene.canvas.Canvas;
import sprite.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

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
        getGraphicsContext2D().clearRect(0,0,screenSize.x,screenSize.y);

        for (int i = background.size()-1 ; i >=0 ; --i){
            background.get(i).drawSelfOnto(getGraphicsContext2D(), screenCenter);
        }

        for (int i = ground.size()-1 ; i >=0 ; --i){
            ground.get(i).drawSelfOnto(getGraphicsContext2D(), screenCenter);
        }

        for (int i = foreground.size()-1 ; i >=0 ; --i){
            foreground.get(i).drawSelfOnto(getGraphicsContext2D(), screenCenter);
        }

        for (int i = HUD.size()-1 ; i >=0 ; --i){
            HUD.get(i).drawSelfOnto(getGraphicsContext2D(), screenCenter);
        }
    }

    public Vector2D getScreenCenter() {
        return screenCenter;
    }

    private void addToList(ArrayList<Sprite> spriteList, int index, Sprite... sprites){
        spriteList.addAll(index, List.of(sprites));
    }

    private void removeFromList(ArrayList<Sprite> spriteList, Sprite... sprites){
        spriteList.removeAll(List.of(sprites));
    }

///
/// AddToList
///
    public void addToBackground(int index, Sprite... sprites){
        addToList(background, index, sprites);
    }

    public void addToBackground(Sprite... sprites){
        addToList(background, background.size()-1, sprites);
    }

    public void addToGround(int index, Sprite... sprites){
        addToList(ground, index, sprites);
    }

    public void addToGround(Sprite... sprites){
        addToList(ground, background.size()-1, sprites);
    }
    public void addToForeground(int index, Sprite... sprites){
        addToList(foreground, index, sprites);
    }

    public void addToForeground(Sprite... sprites){
        addToList(foreground, background.size()-1, sprites);
    }
    public void addToHUD(int index, Sprite... sprites){
        addToList(HUD, index, sprites);
    }

    public void addToHUD(Sprite... sprites){
        addToList(HUD, background.size()-1, sprites);
    }
///
/// RemoverFromList
///
    public void removeFromBackground(Sprite... sprites){
        removeFromList(background, sprites);
    }

    public void removeGround(Sprite... sprites){
        removeFromList(ground, sprites);
    }

    public void removeFromForeground(Sprite... sprites){
        removeFromList(foreground, sprites);
    }

    public void removeFromHUD(Sprite... sprites){
        removeFromList(HUD, sprites);
    }

///
/// Getter
///
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