package ColorSwitch;

import javafx.scene.layout.AnchorPane;
import java.io.Serializable;


public abstract class GameObjects implements Serializable {
    private float [] position;
    private String objectType;
    private int opacity;
    private float [] centerPosition;

    public GameObjects(float x,float y) {
        position = new float[2];
        centerPosition = new float[2];
        position[0] = x;
        position[1] = y;
        centerPosition[0] = x;
        centerPosition[1] = y;
        opacity = 1;
    }

    public GameObjects(float [] positionArr) {
        this.position = positionArr;
    }

    public abstract boolean onCollide(GameObjects collidingBall);
    public abstract void display(AnchorPane gameAnchor);

    public float[] getPosition() {return position;}
    public float getPositionX() {return position[0];}
    public float getPositionY() {return position[1];}
    public float getCenterPositionX() {return centerPosition[0];}
    public float getCenterPositionY() {return centerPosition[1];}
    public String getObjectType() {return objectType;}
    public int getOpacity() {return opacity;}
    public void setPosition(float[] position) {this.position = position;}
    public void setObjectType(String p_type) {this.objectType = p_type;}
    public void setPosition(float x,float y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    public void setOpacity(int p_Opacity) {opacity = p_Opacity;}
}
