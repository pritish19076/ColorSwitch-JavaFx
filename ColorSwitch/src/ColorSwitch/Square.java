package ColorSwitch;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Square extends Obstacles {
    private double side;
    private float [] center;
    private ArrayList<Stick> sticks;
    private Group StickGroup;
    private RotateTransition rt;

    public Square(int p_speed, boolean dir,double side,float x,float y) {

        super(p_speed, dir, (int)x, (int)y);
        this.side = side;
        center = new float[2];
        center[0] = x;
        center[1] = y;
        sticks = new ArrayList<>();
        StickGroup = new Group();
        Stick s1 = new Stick(x - (side / 2), y - (side / 2), x - (side / 2), y + (side / 2), 10, 1);
        Stick s2 = new Stick(x + (side / 2), y - (side / 2), x + (side / 2), y + (side / 2), 10, 2);
        Stick s3 = new Stick(x - (side / 2), y - (side / 2), x + (side / 2), y - (side / 2), 10, 3);
        Stick s4 = new Stick(x - (side / 2), y + (side / 2), x + (side / 2), y + (side / 2), 10, 4);
        sticks.add(s1);sticks.add(s2);sticks.add(s3);sticks.add(s4);
        StickGroup.getChildren().addAll(s1.getLine(),s2.getLine(), s3.getLine(), s4.getLine());

    }


    @Override
    public Group getGroup() {
        return StickGroup;
    }

    @Override
    public void Rotation() {
        rt = new RotateTransition(Duration.millis(getSpeedOfRotation()),StickGroup);
        if(isDirection()) {
            rt.setByAngle(360);
        }
        else {
            rt.setByAngle(-360);
        }
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(RotateTransition.INDEFINITE);
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
