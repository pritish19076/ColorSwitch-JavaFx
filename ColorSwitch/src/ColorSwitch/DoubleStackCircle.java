package ColorSwitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class DoubleStackCircle {
    private NormalCircle lowerCircle;
    private NormalCircle upperCircle;
    private float [] centers;
    private ArrayList<ArcClass> combinedArc;

    public DoubleStackCircle(int p_speed,float x,float y) {
        lowerCircle = new NormalCircle(p_speed,true,100f,100f,x,y);
        upperCircle = new NormalCircle(p_speed,false,100f,100f,x,y+214);
        lowerCircle.setColorSequence(1,2,3,4);
        upperCircle.setColorSequence(3,2,1,4);
        centers = new float[4];
        centers[0] = x;
        centers[1] = y;
        centers[2] = x;
        centers[3] = y+215;

        combinedArc = new ArrayList<>();

        combinedArc.addAll(lowerCircle.getCircleArc());
        combinedArc.addAll(upperCircle.getCircleArc());
    }

    public void display(AnchorPane gamePane) {
        lowerCircle.display(gamePane);
        upperCircle.display(gamePane);
    }

    public void stopRotation() {
        lowerCircle.stopRotation();
        upperCircle.stopRotation();
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
