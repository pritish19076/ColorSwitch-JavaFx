package ColorSwitch;

import javafx.scene.layout.AnchorPane;

public abstract class GameObjects {
    private float [] position;

    public GameObjects(float x,float y) {
        position = new float[2];
        position[0] = x;
        position[1] = y;
    }

    public GameObjects(float [] positionArr) {
        this.position = positionArr;
    }

    public abstract boolean onCollide(GameObjects collidingBall);


    public float[] getPosition() {return position;}
    public float getPositionX() {return position[0];}
    public float getPositionY() {return position[1];}

    public void setPosition(float[] position) {this.position = position;}
    public void setPosition(float x,float y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    public abstract void display(AnchorPane gameAnchor);

}
