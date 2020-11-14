package ColorSwitch;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameMain extends Application {
    private boolean loadgamescreen = false;


    private TranslateTransition runTranslateTransition(Node n,double x,double y,double duration)
    {
        TranslateTransition load = new TranslateTransition();
        load.setByY(y);
        load.setByX(x);
        load.setNode(n);
        load.setDuration(Duration.millis(duration));
        return load;


    }

    private TranslateTransition loadinAnimation(boolean reverse, double duration) {
        TranslateTransition load = new TranslateTransition();
        if (!reverse) load.setByY(683);
        else load.setByY(-683);
        load.setNode(LoadGameWindowGroup);
        load.setDuration(Duration.millis(duration));
        return load;
    }

    private FadeTransition fade(Node n, boolean fadein) {
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(1000));
        if (fadein) fadeload.setToValue(1);
        else fadeload.setToValue(0);
        fadeload.setNode(n);
        return fadeload;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
    private ImageView leaderboardbutton;

    @FXML
    private ImageView leftcircle;
    @FXML
    private ImageView Title;
    @FXML
    private ImageView leftcross;

    @FXML
    private ImageView rightcircle;

    @FXML
    private ImageView rightcross;

    @FXML
    private ImageView startButton;

    @FXML
    private Group LoadGameWindowGroup;

    @FXML
    private ImageView closeLoadGameButton;

    @FXML
    void closeLoadGame(MouseEvent event) {
        System.out.println("close");
        if (loadgamescreen) {
            new SequentialTransition(fade(LoadGameWindowGroup, false), loadinAnimation(true,1)).play();
            loadgamescreen = false;
        }
    }

    @FXML
    void loadGame(MouseEvent event) {
        System.out.println("Load");
        if (!loadgamescreen) {
            LoadGameWindowGroup.setOpacity(1);
            System.out.println("If ke andar");
            loadinAnimation(false,1000).play();
            loadgamescreen = true;
        }


    }

    @FXML
    void startGame(MouseEvent event) {
        System.out.println("start");
        runTranslateTransition(leftcross,-350,0,1000).play();
        runTranslateTransition(leftcircle,-350,0,1000).play();
        runTranslateTransition(rightcross,350,0,1000).play();
        runTranslateTransition(rightcircle,350,0,1000).play();
        runTranslateTransition(leaderboardbutton,0,300,1000).play();
        runTranslateTransition(loadGameButton,0,300,1000).play();
        runTranslateTransition(Title,0,-300,1000).play();
        fade(startButton,false).play();


    }

    public static void main(String[] args) {
        launch(args);
    }


}
