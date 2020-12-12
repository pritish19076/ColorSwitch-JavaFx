package ColorSwitch;

import javafx.scene.Group;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Obstacles extends GameObjects implements Serializable {

    private float speedOfRotation;
    private boolean direction;

    public Obstacles(int p_speed, boolean dir, float x, float y) {
        super(x,y);
        this.speedOfRotation = p_speed;
        this.direction = dir;
    }

    public float getSpeedOfRotation() {return this.speedOfRotation;}
    public boolean isDirection() { return direction;}
    public abstract void Rotation();
    public abstract void stopRotation();
    public abstract Group getGroup();

}