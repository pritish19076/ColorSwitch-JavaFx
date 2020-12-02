package ColorSwitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TripleStackCircle {
    private NormalCircle lowerCircle;
    private NormalCircle middleCircle;
    private NormalCircle upperCircle;
    private float [] centers;
    private ArrayList<ArcClass> combinedArc;

    public TripleStackCircle(int speed, int x, int y) {
        lowerCircle = new NormalCircle(speed,true,100.0f,100.0f,x,y);
        middleCircle = new NormalCircle(speed,false,100.0f,100.0f,x,y+215);
        upperCircle = new NormalCircle(speed,true,100.0f,100.0f,x,y+430);

        lowerCircle.setColorSequence(1,2,3,4);
        middleCircle.setColorSequence(3,2,1,4);
        upperCircle.setColorSequence(1,2,3,4);


        centers = new float[6];
        combinedArc = new ArrayList<>();

        centers[0] = x;
        centers[1] = y;
        centers[2] = x;
        centers[3] = y+215;
        centers[4] = x;
        centers[5] = y+430;

        combinedArc.addAll(lowerCircle.getCircleArc());
        combinedArc.addAll(middleCircle.getCircleArc());
        combinedArc.addAll(upperCircle.getCircleArc());
    }

    public void display(AnchorPane gamePane) {
        lowerCircle.display(gamePane);
        middleCircle.display(gamePane);
        upperCircle.display(gamePane);
    }

    public void stopRotation() {
        lowerCircle.stopRotation();
        middleCircle.stopRotation();
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
