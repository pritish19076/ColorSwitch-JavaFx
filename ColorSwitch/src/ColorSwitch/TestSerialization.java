package ColorSwitch;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TestSerialization extends Application {
    public AnchorPane tmpAnchorPane = new AnchorPane();
    public ArrayList<GameObjects> finalList;
    public ReGenerateObstacles regenObs;

    public static void main(String [] Args) throws IOException, ClassNotFoundException {
        launch(Args);
    }

    @Override
    public void start(Stage stage) throws Exception, IOException {
        regenObs = new ReGenerateObstacles();
        finalList = regenObs.regenerateGameObjects("out.txt");
        for(int i=0;i<finalList.size();i++) {
            finalList.get(i).display(tmpAnchorPane);
        }

        Scene tmpScene = new Scene(tmpAnchorPane,1280,1280);
        stage.setScene(tmpScene);
        stage.show();
    }
}
