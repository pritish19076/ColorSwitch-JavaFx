package ColorSwitch;

import javafx.animation.RotateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class ConcentricCircles {
    private NormalCircle innerCircle;
    private NormalCircle outerCircle;
    private float[] center;
    private ArrayList<ArcClass> combinedArc;

    public ConcentricCircles(int p_speed, boolean dirInner,boolean dirOuter, float p_InnerRadiusOuterCircle, float p_OuterRadiusOuterCircle, float p_InnerRadiusInnerCircle, float p_OuterRadiusInnerCircle, int x, int y) {
        innerCircle = new NormalCircle(5000,dirInner,p_InnerRadiusInnerCircle,p_OuterRadiusInnerCircle,x,y);
        outerCircle = new NormalCircle(4000,dirOuter,p_InnerRadiusOuterCircle,p_OuterRadiusOuterCircle,x,y);
        center = new float[2];
        center[0] = x;
        center[1] = y;
        combinedArc = new ArrayList<>();

        combinedArc.addAll(innerCircle.getCircleArc());
        combinedArc.addAll(outerCircle.getCircleArc());
    }

    public void display(AnchorPane gamePane) {
        innerCircle.display(gamePane);
        outerCircle.display(gamePane);
    }

    public void stopRotation() {
        innerCircle.stopRotation();
        outerCircle.stopRotation();
    }

    public boolean onCollide(GameObjects collidingBall) {
        ArcClass intersectingArc = null;
        for (ArcClass arcClass : combinedArc) {
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
}
