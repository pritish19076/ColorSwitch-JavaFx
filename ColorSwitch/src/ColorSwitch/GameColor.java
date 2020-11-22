package ColorSwitch;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class GameColor {
    private javafx.scene.paint.Color portionColor;
    public GameColor() {
        portionColor = Color.TRANSPARENT;
    }

    public javafx.scene.paint.Color getColor(int i) {
        if(i == 1) portionColor = javafx.scene.paint.Color.rgb(245,225,6);
        else if(i == 2) portionColor = javafx.scene.paint.Color.rgb(137,21,250);
        else if(i == 3) portionColor = javafx.scene.paint.Color.rgb(225,0,125);
        else portionColor = javafx.scene.paint.Color.rgb(50,223,239);
        return portionColor;
    }
}