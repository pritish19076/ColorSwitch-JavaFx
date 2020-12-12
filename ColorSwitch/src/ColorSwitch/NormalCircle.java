package ColorSwitch;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
public class NormalCircle extends Obstacles  {

    private float innerRadius;
    private float outerRadius;
    private float [] center;
    private transient ArrayList<ArcClass> circleArc;
    private transient Group arcGroup;
    private transient RotateTransition rt;

    public NormalCircle(int p_speed, boolean dir,float p_InnerRadius, float p_OuterRadius,float x,float y) {

        super(p_speed,dir,x,y);
        center = new float[2];
        center[0] = x;
        center[1] = y;
        circleArc = new ArrayList<>();
        arcGroup = new Group();
        innerRadius = p_InnerRadius;
        outerRadius = p_OuterRadius;

        ArcClass tmpArc1 = (new ArcClass(x,y,-45.0f,90.0f,innerRadius,outerRadius,1));//right
        ArcClass tmpArc2 = (new ArcClass(x,y,45.0f,90.0f,innerRadius,outerRadius,2));//top
        ArcClass tmpArc3 = (new ArcClass(x,y,135.0f,90.0f,innerRadius,outerRadius,3));//left
        ArcClass tmpArc4 = (new ArcClass(x,y,225.0f,90.0f,innerRadius,outerRadius,4));//bottom

        circleArc.add(tmpArc1);
        circleArc.add(tmpArc2);
        circleArc.add(tmpArc3);
        circleArc.add(tmpArc4);

        arcGroup.getChildren().add(tmpArc1.getArcQuadrant());
        arcGroup.getChildren().add(tmpArc2.getArcQuadrant());
        arcGroup.getChildren().add(tmpArc3.getArcQuadrant());
        arcGroup.getChildren().add(tmpArc4.getArcQuadrant());
    }

    public void setPositonY(double val){
        double temp=arcGroup.getLayoutY()+val;
        setPosition(getPositionX(), (float)temp);
        arcGroup.setLayoutY(temp);
    }

    public void setColorSequence(int lower,int left,int upper,int right) {
        circleArc.get(0).setArcColor(right);
        circleArc.get(1).setArcColor(upper);
        circleArc.get(2).setArcColor(left);
        circleArc.get(3).setArcColor(lower);
    }



    @Override
    public Group getGroup() {
        return arcGroup;
    }

    @Override
    public void Rotation() {
        rt = new RotateTransition(Duration.millis(getSpeedOfRotation()),arcGroup);
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
    public boolean onCollide(GameObjects collidingBall) {
        ArcClass intersectingArc = null;
        for (ArcClass arcClass : circleArc) {
            Shape intersect = Shape.intersect(((Ball) collidingBall).getGameBall(), arcClass.getArcQuadrant());
            boolean isIntersected = false;
            if (intersect.getBoundsInLocal().getWidth() != -1) {
                intersectingArc = arcClass;
                isIntersected = true;
            }
            if (isIntersected && intersectingArc.getColor() == ((Ball) collidingBall).getBallColor()) {
                return false;
            }
            else if (isIntersected && intersectingArc.getColor() != ((Ball) collidingBall).getBallColor()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void display(AnchorPane gamePane) {
        this.Rotation();
        arcGroup.setOpacity(1);
        gamePane.getChildren().add(arcGroup);
    }



    public Group getCircle() {
        return arcGroup;
    }

    public ArrayList<ArcClass> getCircleArc() {
        return circleArc;
    }

}