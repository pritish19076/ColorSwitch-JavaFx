package ColorSwitch;

import javafx.scene.shape.Line;

public class Stick {
    private transient Line line;
    private transient javafx.scene.paint.Color lineColor;
    private transient GameColor Colors;
    private int colorCode;

    public Stick(double startX, double startY,double endX, double endY,float width, int p_color) {
        Colors = new GameColor();
        lineColor = Colors.getColor(p_color);
        colorCode = p_color;
        this.line = new Line(startX,startY,endX,endY);
        this.line.setStrokeWidth(width);
        this.line.setStroke(lineColor);
    }

    //getters
    public javafx.scene.paint.Color                 getlineColor() {return lineColor;}
    public int                                      getColor() {return colorCode;}
    public Line                                      getLine() {return line;}

}
