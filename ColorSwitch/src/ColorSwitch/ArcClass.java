package ColorSwitch;

import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class ArcClass{
    private Arc quadrant;
    private javafx.scene.paint.Color arcColor;
    private int colorNum;

    public ArcClass(float centerX, float centerY, float startAngle, float length, float radiusX, float radiusY, int p_color) {
        this.colorNum = p_color;

        if(p_color == 1) arcColor = javafx.scene.paint.Color.BLUE;
        else if(p_color == 2) arcColor = javafx.scene.paint.Color.RED;
        else if(p_color == 3) arcColor = javafx.scene.paint.Color.YELLOW;
        else arcColor = javafx.scene.paint.Color.GREEN;

        this.quadrant = new javafx.scene.shape.Arc();
        this.quadrant.setCenterX(centerX);
        this.quadrant.setCenterY(centerY);
        this.quadrant.setStartAngle(startAngle);
        this.quadrant.setLength(length);
        this.quadrant.setRadiusX(radiusX);
        this.quadrant.setRadiusY(radiusY);
        this.quadrant.setType(ArcType.OPEN);
        this.quadrant.setStrokeWidth(15);
        this.quadrant.setFill(javafx.scene.paint.Color.TRANSPARENT);
        this.quadrant.setStroke(arcColor);
    }

    //getters
    public int                                      getColorNum() {return colorNum;}
    public javafx.scene.paint.Color                 getArcColor() {return arcColor;}
    public Arc                                      getArcQuadrant() {return quadrant;}

    //
}