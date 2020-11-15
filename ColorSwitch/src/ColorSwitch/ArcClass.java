package ColorSwitch;

import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.awt.*;

public class ArcClass{
    private Arc quadrant;
    private javafx.scene.paint.Color arcColor;
    private GameColor Colors;
    private int colorCode;
    public ArcClass(float centerX, float centerY, float startAngle, float length, float radiusX, float radiusY, int p_color) {
        Colors = new GameColor();
        arcColor = Colors.getColor(p_color);
        colorCode = p_color;

        this.quadrant = new javafx.scene.shape.Arc();
        this.quadrant.setCenterX(centerX);
        this.quadrant.setCenterY(centerY);
        this.quadrant.setStartAngle(startAngle);
        this.quadrant.setLength(length);
        this.quadrant.setRadiusX(radiusX);
        this.quadrant.setRadiusY(radiusY);
        this.quadrant.setType(ArcType.OPEN);
        this.quadrant.setStrokeWidth(15);
        this.quadrant.setFill(null);
        this.quadrant.setStroke(arcColor);
    }

    //getters
    public javafx.scene.paint.Color                 getArcColor() {return arcColor;}
    public int                                      getColor() {return colorCode;}
    public Arc                                      getArcQuadrant() {return quadrant;}
}