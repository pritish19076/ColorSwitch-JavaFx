package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class ConcentricCircles extends Obstacles {
    private transient NormalCircle innerCircle;
    private transient NormalCircle outerCircle;
    private float[] center;
    private transient ArrayList<ArcClass> combinedArc;
    private transient Group combinedGroup;
    private transient ArrayList<Group> allGroupList;

    public ConcentricCircles(int p_speed, float x, float y) {
        super(p_speed,true,x,y);
        innerCircle = new NormalCircle(5000,true,100.0f,100.0f,x,y);
        outerCircle = new NormalCircle(4000,false,120.0f,120.0f,x,y);
        center = new float[2];
        center[0] = x;
        center[1] = y;
        combinedArc = new ArrayList<>();

        combinedArc.addAll(innerCircle.getCircleArc());
        combinedArc.addAll(outerCircle.getCircleArc());
        combinedGroup = new Group();
        combinedGroup.getChildren().add(innerCircle.getCircle());
        combinedGroup.getChildren().add(outerCircle.getCircle());

        allGroupList = new ArrayList<>();
        allGroupList.add(innerCircle.getCircle());
        allGroupList.add(outerCircle.getCircle());
    }

    @Override
    public void display(AnchorPane gamePane) {
        innerCircle.display(gamePane);
        outerCircle.display(gamePane);
    }

    @Override
    public Group getGroup() {
        return null;
    }

    @Override
    public void Rotation() {
        innerCircle.Rotation();
        outerCircle.Rotation();
    }
    @Override
    public void stopRotation() {
        innerCircle.stopRotation();
        outerCircle.stopRotation();
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

    public ArrayList<Group> getAllGroupList() {return allGroupList;}
}
