package windowManager;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import sprite.Sprite;
import utilities.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameWindow extends Canvas {
    private Vector2D screenSize;
    private Vector2D screenCenter;


    public GameWindow(Vector2D screenSize) {
        super(screenSize.x, screenSize.y);
        this.screenSize = screenSize;
        screenCenter = new Vector2D(screenSize.x/2, screenSize.y/2);
    }

    public GameWindow(double width, double height) {
        this(new Vector2D(width, height));
    }

    public GameWindow(){
        this(0,0);
    }


    public final void paintAll(SpriteHandler... spriteHandlers){
        getGraphicsContext2D().clearRect(0,0,screenSize.x,screenSize.y);
        for(SpriteHandler spriteHandler : spriteHandlers)
            for (Ground ground : Ground.values())
                for (Sprite sprite : spriteHandler.getList(ground))
                    if(sprite != null) sprite.drawSelfOnto(getGraphicsContext2D(), screenCenter);

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

    public Vector2D getScreenSize(){
        return screenSize;
    }

    public <T extends Event> void setEventHandlerTo(final EventType<T> eventType, final EventHandler<? super T> eventHandler){
        setEventHandler(eventType, eventHandler);
    }
}