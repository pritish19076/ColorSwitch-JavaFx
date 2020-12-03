package ColorSwitch;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class CommonAnimation {
    public static FadeTransition fade(Node n, double fadeval) {
        //Fade Duration Must be 1500
        //Setting Fades to 1 for Fast Testing
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(1500));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        if(fadeval==0)n.setDisable(true);
        else if(fadeval==1)n.setDisable(false);
        return fadeload;
    }
    public static FadeTransition fade(Node n, double fadeval,double time) {
        //Fade Duration Must be 1500
        //Setting Fades to 1 for Fast Testing
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(time));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        if(fadeval==0)n.setDisable(true);
        else if(fadeval==1)n.setDisable(false);
        return fadeload;
    }
    public static TranslateTransition runTranslateTransition(Node n, double x, double y, double duration) {
        TranslateTransition load = new TranslateTransition();
        load.setByY(y);
        load.setByX(x);
        load.setNode(n);
        load.setDuration(Duration.millis(duration));
        return load;
    }
    public static Timeline delay(double time)
    {
        return new Timeline(new KeyFrame(Duration.millis(time), e -> { }));
    }

    public static TranslateTransition loadPanel(boolean reverse, double duration,double distance,Node MainNode, Node Panel) {
        TranslateTransition load = new TranslateTransition();
        if (!reverse) {load.setByY(distance);CommonAnimation.fade(MainNode,0.4).play();}
        else {load.setByY(-distance);CommonAnimation.fade(MainNode,1).play();}
        load.setNode(Panel);
        load.setDuration(Duration.millis(duration));
        return load;
    }

}

