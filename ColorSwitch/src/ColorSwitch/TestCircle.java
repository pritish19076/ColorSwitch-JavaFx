package ColorSwitch;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class TestCircle extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        NormalCircle tmpCircle = new NormalCircle();
        Group tmpGroup = tmpCircle.getCircle();
        stage.setTitle("CIRCLE OBSTACLE");
        stage.setScene(new Scene(tmpGroup,1280,720));
        stage.setResizable(false);
        stage.show();
    }
}
