package utilities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {
    protected Vector2D position;
    //protected Vector2D drawerPosition;

    public void drawSelfOnto(GraphicsContext graphicsContext, Vector2D parentPosition) {
        System.out.println(position.x + parentPosition.x + " " + position.y + parentPosition.y);
        draw(graphicsContext, new Vector2D(position.x + parentPosition.x, position.y + parentPosition.y));
    }

    protected abstract void draw(GraphicsContext graphicsContext, Vector2D position);

    public Sprite(Vector2D position){
        this.position = position;
        //drawerPosition = new Vector2D();
    }

    /*public void computeDrawerPosition(Vector2D center){
        drawerPosition = new Vector2D(position).add(center);
    }*/
}
