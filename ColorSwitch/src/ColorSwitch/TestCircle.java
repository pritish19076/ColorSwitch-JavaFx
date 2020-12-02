package ColorSwitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class TestCircle extends Application {
//    NormalCircle c1;
    ConcentricCircles tmpConCircles;
    DoubleStackCircle tmpDoubleStackCircle;
    TripleConcentricCircles tmpTripleConCircles;
    TripleStackCircle tmpTripleStackCircles;
    public static void main(String [] Args) {
        launch(Args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane tmpAnchorPane = new AnchorPane();
        stage.setTitle("CIRCLE OBSTACLE");
//        c1 = new NormalCircle(3000,true,100f,100f,200,300);
//        c1.display(tmpAnchorPane);
//        c1.getCircle().setOpacity(1);
//        tmpDoubleStackCircle = new DoubleStackCircle(5000,263,400);
//        tmpTripleConCircles = new TripleConcentricCircles(4000,3000,263,400);
//        tmpConCircles = new ConcentricCircles(3000,true,false,120f,120f,100f,100f,263,440);
//        tmpConCircles.display(tmpAnchorPane);
//        tmpTripleConCircles.display(tmpAnchorPane);
        tmpTripleStackCircles = new TripleStackCircle(3000,263,400);
        tmpTripleStackCircles.display(tmpAnchorPane);
//        tmpDoubleStackCircle.display(tmpAnchorPane);
        Scene tmpScene = new Scene(tmpAnchorPane,1280,1280);
        stage.setScene(tmpScene);
        stage.setResizable(false);
        stage.show();
    }
}
