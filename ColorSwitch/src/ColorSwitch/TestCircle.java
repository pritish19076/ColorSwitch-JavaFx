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
    public static void main(String [] Args) {
        launch(Args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        ColorChanger tmpColorChanger = new ColorChanger(500,500,20f,20f,1);
        NormalCircle tmpCircle = new NormalCircle(3000,true,100.0f,100.0f,500,500);
//        Ball tmpBall = new Ball(600,450,10,3,10,1);
        AnchorPane tmpAnchorPane = new AnchorPane();
        stage.setTitle("CIRCLE OBSTACLE");
        tmpCircle.display(tmpAnchorPane);
//        tmpBall.display(tmpAnchorPane);
        tmpColorChanger.display(tmpAnchorPane);
        Scene tmpScene = new Scene(tmpAnchorPane,1280,1280);
        stage.setScene(tmpScene);
        stage.setResizable(false);
//        ObservableList<Node> tmp = tmpAnchorPane.getChildren();

//        ArrayList<ArcClass> tmpCircleGrp = tmpCircle.getCircleArc();
//        Circle tmpGameBall = tmpBall.getGameBall();

//        for(int i=0;i<tmpCircleGrp.size();i++) {
//            Shape intersect = Shape.intersect(tmpGameBall,tmpCircleGrp.get(i).getArcQuadrant());
//            boolean isIntersected = false;
//            if(intersect.getBoundsInLocal().getWidth() != -1) {
//                System.out.println(intersect.getBoundsInLocal().getWidth());
//                isIntersected = true;
//            }
//
//            System.out.println(isIntersected);
//            System.out.println(tmpCircleGrp.get(i).getColor());
//        }

        stage.show();
    }
}
