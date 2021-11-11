package test;

import entity.Creature;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utilities.Vector2D;

public class EntityTest extends Creature {
    public void mouseEvent(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
            this.getPosition().x = event.getX() - game.getGameWindow().getScreenCenter().x;
            this.getPosition().y = event.getY() - game.getGameWindow().getScreenCenter().y;
        }
    }

    public void keyboardEvent(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED)
        {
            KeyCode keyPressed = event.getCode();

            switch (keyPressed){
                case Z:
                    translate(new Vector2D(0, -5));
                    break;
                case Q:
                    translate(new Vector2D(-5, 0));
                    break;
                case S:
                    translate(new Vector2D(0, 5));
                    break;
                case D:
                    translate(new Vector2D(5, 0));
                    break;
            }
        }
    }
}
