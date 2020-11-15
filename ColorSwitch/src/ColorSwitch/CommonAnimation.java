package ColorSwitch;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class CommonAnimation {
    public static FadeTransition fade(Node n, double fadeval) {
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(1500));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        if(fadeval==0)n.setDisable(true);
        else if(fadeval==1)n.setDisable(false);
        return fadeload;
    }
}
