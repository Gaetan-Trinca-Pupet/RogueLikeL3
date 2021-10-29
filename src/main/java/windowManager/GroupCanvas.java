package windowManager;

import javafx.scene.canvas.Canvas;
import utilities.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GroupCanvas extends Canvas {
    private Vector2D center;

    private ArrayList<Sprite> spriteList;

    public GroupCanvas(Vector2D screenSize, Vector2D center){
        super(screenSize.x, screenSize.y);
        spriteList = new ArrayList<>();
        this.center = center;
    }

    public GroupCanvas(Vector2D screenSize){
        this(screenSize, new Vector2D(screenSize.x/2, screenSize.y/2));
    }

    public void add(Sprite... sprites){
        spriteList.addAll(List.of(sprites));
    }

    public void add(int index, Sprite... sprites){
        spriteList.addAll(index, List.of(sprites));
    }

    public void remove(Sprite sprite){
        spriteList.remove(sprite);
    }

    public void paintAllSprites(){
        for (int i = spriteList.size()-1 ; i >=0 ; --i){
            spriteList.get(i).computeDrawerPosition(center);
            spriteList.get(i).drawSelfOnto(getGraphicsContext2D());
        }
    }
}
