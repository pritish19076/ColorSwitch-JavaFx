package ColorSwitch;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class NormalCircle extends Obstacles {
    private float innerRadius;
    private float outerRadius;
    private float [] center;
    private ArrayList<Arc> circleArc;
    private Group arcGroup;
    public NormalCircle(int p_speed, boolean dir,float p_InnerRadius, float p_OuterRadius,int x,int y) {
        super(p_speed,dir,x,y);
        center = new float[2];
        center[0] = x;
        center[1] = y;
        circleArc = new ArrayList<>();
        arcGroup = new Group();
        innerRadius = p_InnerRadius;
        outerRadius = p_OuterRadius;

        Arc tmpArc1 = (new ArcClass(x,y,0.0f,90.0f,innerRadius,outerRadius,1).getArcQuadrant());
        Arc tmpArc2 = (new ArcClass(x,y,90.0f,90.0f,innerRadius,outerRadius,2).getArcQuadrant());
        Arc tmpArc3 = (new ArcClass(x,y,180.0f,90.0f,innerRadius,outerRadius,3).getArcQuadrant());
        Arc tmpArc4 = (new ArcClass(x,y,270.0f,90.0f,innerRadius,outerRadius,4).getArcQuadrant());

        circleArc.add(tmpArc1);
        circleArc.add(tmpArc2);
        circleArc.add(tmpArc3);
        circleArc.add(tmpArc4);

        arcGroup.getChildren().add(tmpArc1);
        arcGroup.getChildren().add(tmpArc2);
        arcGroup.getChildren().add(tmpArc3);
        arcGroup.getChildren().add(tmpArc4);
    }

    @Override
    public void Rotation() {
        RotateTransition rt = new RotateTransition(Duration.millis(getSpeedOfRotation()),arcGroup);
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
    public void onCollide(GameObjects collidingBall) {

    }

    @Override
    public void display(AnchorPane gamePane) {
        this.Rotation();
        gamePane.getChildren().add(arcGroup);
    }

    public Group getCircle() {
        return arcGroup;
    }

}
