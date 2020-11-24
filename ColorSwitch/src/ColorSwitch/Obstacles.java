package ColorSwitch;

import javafx.scene.Group;
import java.util.ArrayList;

public abstract class Obstacles extends GameObjects {

    private float speedOfRotation;
    private boolean direction;
    protected float opacity = 0;

    public abstract Group getGroup();

    public Obstacles(int p_speed, boolean dir, int x, int y) {
        super(x,y);
        this.speedOfRotation = p_speed;
        this.direction = dir;
    }

    /*public Obstacles(int p_speed, boolean dir, float [] arr) {
        super(arr);
        this.speedOfRotation = p_speed;
        this.direction = dir;
    }
    */

    public float getSpeedOfRotation() {return this.speedOfRotation;}
    public boolean isDirection() { return direction;}
    public abstract void Rotation();
    public abstract void stopRotation();

}