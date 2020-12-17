package ColorSwitch;

import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
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
    public void shake(){
        Group g=getGroup();
        TranslateTransition t1 = CommonAnimation.runTranslateTransition(g,0,40,1500);
        TranslateTransition t2 = CommonAnimation.runTranslateTransition(g,0,-40,1500);
        TranslateTransition t3 = CommonAnimation.runTranslateTransition(g,0,-40,1500);
        TranslateTransition t4 = CommonAnimation.runTranslateTransition(g,0,40,1500);
        t1.setInterpolator(Interpolator.LINEAR);
        t2.setInterpolator(Interpolator.LINEAR);
        t3.setInterpolator(Interpolator.LINEAR);
        t4.setInterpolator(Interpolator.LINEAR);
        SequentialTransition rt=new SequentialTransition(t1,t2,t3,t4);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(SequentialTransition.INDEFINITE);
        rt.play();
    }

}