package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class DoubleStackCircle extends Obstacles {
    private transient NormalCircle lowerCircle;
    private transient NormalCircle upperCircle;
    private float [] centers;
    private transient ArrayList<ArcClass> combinedArc;
    private transient Group allArcGroup;
    private transient ArrayList<Group> allGroupList;
    public DoubleStackCircle(int p_speed,float x,float y) {
        super(p_speed,true,x,y);
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
        allArcGroup=new Group();
        allArcGroup.getChildren().add(lowerCircle.getCircle());
        allArcGroup.getChildren().add(upperCircle.getCircle());

        allGroupList = new ArrayList<>();
        allGroupList.add(lowerCircle.getCircle());
        allGroupList.add(upperCircle.getCircle());
    }
    @Override
    public void display(AnchorPane gamePane) {
        System.out.println("Inside printing");
        lowerCircle.display(gamePane);
        upperCircle.display(gamePane);
    }

    @Override
    public Group getGroup() {
        return null;
    }
    @Override
    public void Rotation() {
        lowerCircle.Rotation();
        upperCircle.Rotation();
    }
    @Override
    public void stopRotation() {
        lowerCircle.stopRotation();
        upperCircle.stopRotation();
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

    public ArrayList<Group> getGroupList() {
        return allGroupList;
    }
}
