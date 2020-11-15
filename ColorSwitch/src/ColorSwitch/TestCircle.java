package ColorSwitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class TestCircle extends Application {
    public static void main(String [] Args) {
        launch(Args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        NormalCircle tmpCircle = new NormalCircle(3000,true,100.0f,100.0f,500,500);
        Ball tmpBall = new Ball(500,500,10,3,10);
        AnchorPane tmpAnchorPane = new AnchorPane();
        stage.setTitle("CIRCLE OBSTACLE");
        tmpCircle.display(tmpAnchorPane);
        tmpBall.display(tmpAnchorPane);
        Scene tmpScene = new Scene(tmpAnchorPane,1280,1280);
        stage.setScene(tmpScene);
        stage.setResizable(false);
        stage.show();
    }
}
