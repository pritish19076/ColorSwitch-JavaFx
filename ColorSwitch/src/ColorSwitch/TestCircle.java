package ColorSwitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class TestCircle extends Application {
    public static void main(String [] Args) {
        launch(Args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        NormalCircle tmpCircle = new NormalCircle(3000,true,100.0f,100.0f,300,200);

        stage.setTitle("CIRCLE OBSTACLE");
        tmpCircle.display(stage);
        stage.setResizable(false);
        stage.show();
    }
}
