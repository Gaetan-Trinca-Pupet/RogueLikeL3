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

    private Canvas canvas;

    public TimeEvent(EventType<? extends Event> eventType) {
        super(eventType);
        canvas = new Canvas();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    private int frequency = 1;

    public void setFrequency(int hz){
        frequency = hz;
    }

    private float deltaTime = 0;

    public float getDeltaTime(){
        return deltaTime;
    }

    public TimeEvent(Object source, EventTarget target, EventType<? extends Event> eventType) {
        super(source, target, eventType);
    }

    public void loop(){
        for(long chrono = System.currentTimeMillis() ; loop ; chrono = System.currentTimeMillis()){
            fireEvent(canvas, this);
            while(System.currentTimeMillis() - chrono < 1000/frequency);
            deltaTime =(float) (System.currentTimeMillis() - chrono) / 1000;
            System.out.println(deltaTime);
        }
    }
}
