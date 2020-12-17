package ColorSwitch;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.*;

public class TestCircle extends Application {
    DoubleStackCircle tmp;
    public static void main(String [] Args) {
        launch(Args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane tmpAnchorPane = new AnchorPane();
//        CommonAnimation.DeathAnimation(500,500,tmpAnchorPane);
//        CommonAnimation.starAnimation(500,500,tmpAnchorPane);
        Scene tmpScene = new Scene(tmpAnchorPane,1280,1280);
        stage.setScene(tmpScene);
        stage.setResizable(false);
        stage.show();
    }
}
