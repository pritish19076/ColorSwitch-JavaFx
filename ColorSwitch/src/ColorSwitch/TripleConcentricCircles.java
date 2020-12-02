package ColorSwitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TripleConcentricCircles {
    private NormalCircle innerCircle;
    private NormalCircle middleCircle;
    private NormalCircle outerCircle;
    ArrayList<ArcClass> combinedArc;
    float [] center;

    public TripleConcentricCircles(int speed1, int speed2, int x, int y) {
        innerCircle = new NormalCircle(speed1,true,100f,100f,x,y);
        middleCircle = new NormalCircle(speed1,true,120f,120f,x,y);
        outerCircle = new NormalCircle(speed2, false,140,140,x,y);

        center = new float[2];
        combinedArc = new ArrayList<>();

        center[0] = x;
        center[1] = y;

        combinedArc.addAll(innerCircle.getCircleArc());
        combinedArc.addAll(outerCircle.getCircleArc());
    }

    public void display(AnchorPane gameAnchorPane) {
        innerCircle.display(gameAnchorPane);
        middleCircle.display(gameAnchorPane);
        outerCircle.display(gameAnchorPane);
    }

    public void stopRotation() {
        innerCircle.stopRotation();
        middleCircle.stopRotation();
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
