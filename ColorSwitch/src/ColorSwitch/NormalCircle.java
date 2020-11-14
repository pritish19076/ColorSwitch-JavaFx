package ColorSwitch;

import javafx.scene.Group;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class NormalCircle {
    private float innerRadius;
    private float outerRadius;
    private float [] center;
    private ArrayList<Arc> circleArc;
    private Group arcGroup;
    public NormalCircle() {
        circleArc = new ArrayList<>();
        arcGroup = new Group();
        innerRadius = 100.0f;
        outerRadius = 100.0f;

        Arc tmpArc1 = (new ArcClass(300.0f,200.0f,0.0f,90.0f,innerRadius,outerRadius,1).getArcQuadrant());
        Arc tmpArc2 = (new ArcClass(300.0f,200.0f,90.0f,90.0f,innerRadius,outerRadius,2).getArcQuadrant());
        Arc tmpArc3 = (new ArcClass(300.0f,200.0f,180.0f,90.0f,innerRadius,outerRadius,3).getArcQuadrant());
        Arc tmpArc4 = (new ArcClass(300.0f,200.0f,270.0f,90.0f,innerRadius,outerRadius,4).getArcQuadrant());
        circleArc.add(tmpArc1);
        circleArc.add(tmpArc2);
        circleArc.add(tmpArc3);
        circleArc.add(tmpArc4);

        arcGroup.getChildren().add(tmpArc1);
        arcGroup.getChildren().add(tmpArc2);
        arcGroup.getChildren().add(tmpArc3);
        arcGroup.getChildren().add(tmpArc4);
    }

    public Group getCircle() {
        return arcGroup;
    }

}
