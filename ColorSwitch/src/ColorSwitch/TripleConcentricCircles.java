package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class TripleConcentricCircles extends Obstacles {
    private NormalCircle innerCircle;
    private NormalCircle middleCircle;
    private NormalCircle outerCircle;
    private ArrayList<ArcClass> combinedArc;
    private float [] center;
    private Group combineGroup;
    private ArrayList<Group> allGroupList;

    public TripleConcentricCircles(int speed1, int speed2, int x, int y) {
        super(speed1,true,x,y);
        innerCircle = new NormalCircle(speed1,true,100f,100f,x,y);
        middleCircle = new NormalCircle(speed1,true,120f,120f,x,y);
        outerCircle = new NormalCircle(speed2, false,140,140,x,y);

        center = new float[2];
        combinedArc = new ArrayList<>();

        center[0] = x;
        center[1] = y;

        combinedArc.addAll(innerCircle.getCircleArc());
        combinedArc.addAll(middleCircle.getCircleArc());
        combinedArc.addAll(outerCircle.getCircleArc());

        combineGroup = new Group();
        combineGroup.getChildren().add(innerCircle.getCircle());
        combineGroup.getChildren().add(middleCircle.getCircle());
        combineGroup.getChildren().add(outerCircle.getCircle());

        allGroupList = new ArrayList<>();
        allGroupList.add(innerCircle.getCircle());
        allGroupList.add(middleCircle.getCircle());
        allGroupList.add(outerCircle.getCircle());
    }
    @Override
    public void display(AnchorPane gameAnchorPane) {
        innerCircle.display(gameAnchorPane);
        middleCircle.display(gameAnchorPane);
        outerCircle.display(gameAnchorPane);
    }

    @Override
    public void Rotation() {
        innerCircle.Rotation();
        middleCircle.Rotation();
        outerCircle.Rotation();
    }
    @Override
    public void stopRotation() {
        innerCircle.stopRotation();
        middleCircle.stopRotation();
        outerCircle.stopRotation();
    }

    @Override
    public Group getGroup() {
        return combineGroup;
    }
    @Override
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

    public ArrayList<Group> getAllGroupList() {
        return allGroupList;
    }
}
