package test;

import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class TimeEvent extends Event {
    public static final EventType<TimeEvent> TIME_PASSES = new EventType<>(Event.ANY, "TIME_PASSES");
    public static boolean loop = true;

    private static Canvas canvas;

    public TimeEvent(EventType<? extends Event> eventType) {
        super(eventType);
        canvas = new Canvas();
    }

    public static void setCanvas(Canvas canvas) {
        TimeEvent.canvas = canvas;
    }

    public TimeEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }

    public void loop(){
        int hz = 60;
        float deltaTime = 1;
        for(long chrono = System.currentTimeMillis() ; loop ; chrono = System.currentTimeMillis()){
            fireEvent(canvas, this);
            while(System.currentTimeMillis() - chrono < 1000/hz);
            deltaTime = (System.currentTimeMillis() - chrono) / (1000/hz);
        }
    }
}
