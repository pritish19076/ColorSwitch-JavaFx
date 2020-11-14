package ColorSwitch;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class GameMain extends Application implements Initializable {
    private boolean loadgamescreen = false;


    private TranslateTransition runTranslateTransition(Node n, double x, double y, double duration) {
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

    private FadeTransition fade(Node n,double fadeval) {
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(1500));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        return fadeload;
    }

    private void introTransition(double out) {
        runTranslateTransition(leftcross, out * -350, 0, 1500).play();
        runTranslateTransition(leftcircle, out * -350, 0, 1500).play();
        runTranslateTransition(rightcross, out * 350, 0, 1500).play();
        runTranslateTransition(rightcircle, out * 350, 0, 1500).play();
        runTranslateTransition(leaderboardbutton, 0, out * 300, 1500).play();
        runTranslateTransition(loadGameButton, 0, out * 300, 1500).play();
        runTranslateTransition(Title, 0, out * -300, 1500).play();
        if (out == 1) {
            fade(startButton, 0).play();
            startButton.setDisable(true);
        } else {
            fade(startButton, 1).play();
            startButton.setDisable(false);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ColorSwitch.fxml"));
        primaryStage.setTitle("I Just Wanna Switch!");
        Scene myScene=new Scene(root, 1280, 720);
        myScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.SHIFT) System.out.println("as");
            }
        });
        primaryStage.setScene(myScene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    @FXML
    private Group MainMenuGroup;

    @FXML
    private ImageView loadGameButton;

    @FXML
    private AnchorPane mainAnchorPane;
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
    private Group ObstaclesGroup;


    @FXML
    void inputBall(KeyEvent event) {
        System.out.println(event.getCode());
    }
    @FXML
    void closeLoadGame(MouseEvent event) {
        System.out.println("close");
        if (loadgamescreen) {
            new SequentialTransition(fade(LoadGameWindowGroup, 0), loadinAnimation(true, 1)).play();
            loadgamescreen = false;
        }
    }

    @FXML
    void loadGame(MouseEvent event) {
        System.out.println("Load");
        if (!loadgamescreen) {
            LoadGameWindowGroup.setOpacity(1);
            System.out.println("If ke andar");
            loadinAnimation(false, 1000).play();
            loadgamescreen = true;
        }


    }

    @FXML
    void startGame(MouseEvent event) {
        System.out.println("start");
        introTransition(1);


    }

    public static void main(String[] args) {
        launch(args);
    }
    private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                    event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                //your code for moving the ship
            }
            else if(event.getCode() == KeyCode.SPACE) {
                //your code for shooting the missile
                System.out.println("space");
            }
            event.consume();
        }
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        introTransition(-1);


    }
}
