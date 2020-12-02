package ColorSwitch;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class LongRod extends Obstacles {

    private float [] center;
    private ArrayList<Stick> sticks;
    private Group StickGroup;
    private SequentialTransition rt;

    public LongRod(int p_speed, boolean dir,float x,float y) {

        super(p_speed, dir, (int)x, (int)y);
        center = new float[2];
        center[0] = x;
        center[1] = y;
        sticks = new ArrayList<>();
        StickGroup = new Group();
        Stick s1 = new Stick(x - 200, y, x+20, y, 15, 1);
        Stick s2 = new Stick(x + 20, y, x + 240, y, 15, 2);
        Stick s3 = new Stick(x - 420, y, x - 200, y, 15, 3);
        Stick s4 = new Stick(x + 240, y, x + 460, y, 15, 4);
        sticks.add(s1);sticks.add(s2);sticks.add(s3);sticks.add(s4);
        StickGroup.getChildren().addAll(s1.getLine(),s2.getLine(), s3.getLine(), s4.getLine());

    }


    @Override
    public Group getGroup() {
        return StickGroup;
    }

    @Override
    public void Rotation() {
        TranslateTransition t1 = CommonAnimation.runTranslateTransition(StickGroup,300,0,1500);
        TranslateTransition t2 = CommonAnimation.runTranslateTransition(StickGroup,-300,0,1500);

        TranslateTransition t3 = CommonAnimation.runTranslateTransition(StickGroup,-350,0,1750);
        TranslateTransition t4 = CommonAnimation.runTranslateTransition(StickGroup,350,0,1750);
        t1.setInterpolator(Interpolator.LINEAR);
        t2.setInterpolator(Interpolator.LINEAR);
        t3.setInterpolator(Interpolator.LINEAR);
        t4.setInterpolator(Interpolator.LINEAR);
        rt=new SequentialTransition(t1,t2,t3,t4);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(SequentialTransition.INDEFINITE);
        rt.play();
    }

    @Override
    public void stopRotation() {
        rt.setCycleCount(0);
        rt.pause();
    }

    @Override
    public void onCollide(GameObjects collidingBall) {

    }

    @Override
    public void display(AnchorPane gamePane) {
        this.Rotation();
        gamePane.getChildren().add(StickGroup);
    }

    public Group getStickGroup() {
        return StickGroup;
    }

    public ArrayList<Stick> getSticks() {
        return sticks;
    }


}
