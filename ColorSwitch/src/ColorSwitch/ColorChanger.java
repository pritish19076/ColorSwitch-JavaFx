package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

public class ColorChanger extends GameObjects {

    private float innerRadius;
    private float outerRadius;
    private float [] center;
    private transient ArrayList<ArcClass> circleArc;
    private transient Group arcGroup;

    public int getRandomcolor() {
        return randomcolor;
    }

    private int randomcolor;

    public ColorChanger(float x, float y, float p_innerRadius, float p_outerRadius, float opacity) {
        super(x,y);
        center = new float[2];
        circleArc = new ArrayList<>();
        arcGroup = new Group();
        center[0] = x;
        center[1] = y;
        innerRadius = p_innerRadius;
        outerRadius = p_outerRadius;

        ArcClass tmpArc1 = (new ArcClass(x,y,0.0f,90.0f,innerRadius,outerRadius,1));
        ArcClass tmpArc2 = (new ArcClass(x,y,90.0f,90.0f,innerRadius,outerRadius,2));
        ArcClass tmpArc3 = (new ArcClass(x,y,180.0f,90.0f,innerRadius,outerRadius,3));
        ArcClass tmpArc4 = (new ArcClass(x,y,270.0f,90.0f,innerRadius,outerRadius,4));


        tmpArc1.getArcQuadrant().setType(ArcType.ROUND);
        tmpArc2.getArcQuadrant().setType(ArcType.ROUND);
        tmpArc3.getArcQuadrant().setType(ArcType.ROUND);
        tmpArc4.getArcQuadrant().setType(ArcType.ROUND);

        tmpArc1.getArcQuadrant().setFill(javafx.scene.paint.Color.rgb(245,225,6));
        tmpArc2.getArcQuadrant().setFill(javafx.scene.paint.Color.rgb(137,21,250));
        tmpArc3.getArcQuadrant().setFill(javafx.scene.paint.Color.rgb(225,0,125));
        tmpArc4.getArcQuadrant().setFill(javafx.scene.paint.Color.rgb(50,223,239));

        tmpArc1.getArcQuadrant().setStrokeWidth(1);
        tmpArc2.getArcQuadrant().setStrokeWidth(1);
        tmpArc3.getArcQuadrant().setStrokeWidth(1);
        tmpArc4.getArcQuadrant().setStrokeWidth(1);

        circleArc.add(tmpArc1);
        circleArc.add(tmpArc2);
        circleArc.add(tmpArc3);
        circleArc.add(tmpArc4);

        arcGroup.getChildren().add(tmpArc1.getArcQuadrant());
        arcGroup.getChildren().add(tmpArc2.getArcQuadrant());
        arcGroup.getChildren().add(tmpArc3.getArcQuadrant());
        arcGroup.getChildren().add(tmpArc4.getArcQuadrant());

        arcGroup.setOpacity(opacity);

    }

    public Group getArcGroup() {return arcGroup;}

    @Override
    public boolean onCollide(GameObjects collidingBall) {
        AnchorPane anch = (AnchorPane) arcGroup.getParent();
        if (anch != null) {
            //ArcClass intersectingArc = null;
            for (ArcClass arcClass : circleArc) {
                Shape intersect = Shape.intersect(((Ball) collidingBall).getGameBall(), arcClass.getArcQuadrant());
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    changeColor((Ball) collidingBall);
                    anch.getChildren().remove(arcGroup);
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public void display(AnchorPane gameAnchor) {
        gameAnchor.getChildren().add(arcGroup);
    }
    private void changeColor(Ball inputball)
    {
        int currentcolor=inputball.getBallColor();
        Random random=new Random();
        randomcolor=random.nextInt(4)+1;
        while (randomcolor==currentcolor)
        {
            randomcolor=random.nextInt(4)+1;

        }
        inputball.setBallColor(randomcolor);


    }
}
