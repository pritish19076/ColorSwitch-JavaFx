package ColorSwitch;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ColorSwitch.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    @FXML
    private Group MainMenuGroup;

    @FXML
    private ImageView loadGameButton;

    @FXML
    private ImageView startButton;

    @FXML
    private Group LoadGameWindowGroup;

    @FXML
    private ImageView closeLoadGameButton;

    @FXML
    void closeLoadGame(MouseEvent event) {
        System.out.println("close");
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(1000));
        fadeload.setToValue(0);
        fadeload.setNode(LoadGameWindowGroup);
        TranslateTransition load = new TranslateTransition();
        load.setByY(-683);
        load.setNode(LoadGameWindowGroup);
        load.setDuration(Duration.millis(1000));
        new SequentialTransition(fadeload,load).play();


    }

    @FXML
    void loadGame(MouseEvent event) {
        System.out.println("Load");
        if(LoadGameWindowGroup.getLayoutY()<0) {
            LoadGameWindowGroup.setOpacity(1);
            System.out.println("If ke andar");
            TranslateTransition load = new TranslateTransition();
            load.setByY(683);
            load.setNode(LoadGameWindowGroup);
            load.setDuration(Duration.millis(1000));
            load.play();

        }


    }

    @FXML
    void startGame(MouseEvent event) {

    }
    public static void main(String[] args) {
        launch(args);
    }



}
