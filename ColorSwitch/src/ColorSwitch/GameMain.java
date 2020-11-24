package ColorSwitch;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ColorSwitch.CommonAnimation.runTranslateTransition;


public class GameMain extends Application implements Initializable {
    private boolean loadgamescreen = false;
    public static Stage myStage;
    public static Scene getCurrentScene;
    public static Parent p_root;
    public static void setupScene(Scene p_scene,Stage myStage) {
        System.out.println("initial");
        getCurrentScene= p_scene;
        myStage=myStage;
    }

    private TranslateTransition loadinAnimation(boolean reverse, double duration) {
        TranslateTransition load = new TranslateTransition();
        if (!reverse) {load.setByY(683);CommonAnimation.fade(MainMenuGroup,0.4).play();}
        else {load.setByY(-683);CommonAnimation.fade(MainMenuGroup,1).play();}
        load.setNode(LoadGameWindowGroup);
        load.setDuration(Duration.millis(duration));
        return load;
    }

    private TranslateTransition leaderinAnimation(boolean reverse, double duration) {
        TranslateTransition load = new TranslateTransition();
        if (!reverse) {load.setByY(-983);
            CommonAnimation.fade(MainMenuGroup,0.4).play();

        }
        else{ load.setByY(983);
            CommonAnimation.fade(MainMenuGroup,1).play();}
        load.setNode(LeaderboardWindowGroup);
        load.setDuration(Duration.millis(duration));
        return load;
    }
    private TranslateTransition exitinAnimation(boolean reverse, double duration) {
        TranslateTransition load = new TranslateTransition();
        if (!reverse) {load.setByY(-1100);CommonAnimation.fade(MainMenuGroup,0.4).play();}
        else {load.setByY(1100);CommonAnimation.fade(MainMenuGroup,1).play();}
        load.setNode(ExitPopUp);
        load.setDuration(Duration.millis(duration));
        return load;
    }



    private void introTransition(double out) {
        //Duration to be 1500
        //Setting Duration to 1 for fast Testing
        runTranslateTransition(leftcross, out * -350, 0, 1500).play();
        runTranslateTransition(leftcircle, out * -350, 0, 1500).play();
        runTranslateTransition(rightcross, out * 350, 0, 1500).play();
        runTranslateTransition(rightcircle, out * 350, 0, 1500).play();
        runTranslateTransition(leaderboardbutton, 0, out * 300, 1500).play();
        runTranslateTransition(loadGameButton, 0, out * 300, 1500).play();
        runTranslateTransition(Title, 0, out * -300, 1500).play();
        runTranslateTransition(exitButton, 0, out * 200, 1500).play();
        if (out == 1) {
            CommonAnimation.fade(startButton, 0).play();
            startButton.setDisable(true);
        } else {
            CommonAnimation.fade(startButton, 1).play();
            startButton.setDisable(false);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        myStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ColorSwitch.fxml"));
        primaryStage.setTitle("I Just Wanna Switch!");
        Scene myScene=new Scene(root, 1280, 720);
        myScene.setOnKeyPressed(new EventHandler<KeyEvent>(){


            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.SHIFT) {
                    System.out.println("");



                }


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
    private Group LeaderboardWindowGroup;

    @FXML
    private ImageView closeLeaderBoardGameButton;


    @FXML
    void openLeaderBoard(MouseEvent event) {
        System.out.println("Load");
        if (!loadgamescreen) {
            CommonAnimation.fade(LeaderboardWindowGroup, 1).play();
            System.out.println("If ke andar");
            leaderinAnimation(false, 1000).play();
            loadgamescreen = true;
        }


    }

    @FXML
    private Group ExitPopUp;

    @FXML
    private ImageView closePrompt;

    @FXML
    void closeGame(MouseEvent event) {
        Platform.exit();
        System.exit(0);

    }

    @FXML
    void closePopup(MouseEvent event) {
        System.out.println("blah");
        if (loadgamescreen) {
            new SequentialTransition(CommonAnimation.fade(ExitPopUp, 0), exitinAnimation(true, 1)).play();
            loadgamescreen = false;
        }

    }

    @FXML
    void exitTheGame(MouseEvent event) {
        if (!loadgamescreen) {
            CommonAnimation.fade(ExitPopUp, 1).play();
            System.out.println("If ke andar");
            exitinAnimation(false, 1000).play();
            loadgamescreen = true;
        }

    }
    @FXML
    private ImageView exitButton;

    @FXML
    void inputBall(KeyEvent event) {
        System.out.println(event.getCode());
    }
    @FXML
    void closeLoadGame(MouseEvent event) {
        System.out.println("close");
        if (loadgamescreen) {
            new SequentialTransition(CommonAnimation.fade(LoadGameWindowGroup, 0), loadinAnimation(true, 1)).play();
            loadgamescreen = false;
        }
    }
    @FXML
    void closeLeaderBoard(MouseEvent event) {
        System.out.println("close");
        if (loadgamescreen) {
            new SequentialTransition(CommonAnimation.fade(LeaderboardWindowGroup, 0), leaderinAnimation(true, 1)).play();
            loadgamescreen = false;
        }
    }

    @FXML
    void loadGame(MouseEvent event) {
        System.out.println("Load");
        if (!loadgamescreen) {
            CommonAnimation.fade(LoadGameWindowGroup, 1).play();
            System.out.println("If ke andar");
            loadinAnimation(false, 1000).play();
            loadgamescreen = true;
        }
    }
    private Timeline delay(double time)
    {
        return new Timeline(new KeyFrame(Duration.millis(time),e -> { }));
    }
    @FXML
    void startGame(MouseEvent event){
        System.out.println("start");

        introTransition(1);




        Timeline tim2=new Timeline();
        KeyFrame changeSceneSize=new KeyFrame(Duration.millis(20),e -> {
            myStage.setWidth(myStage.getWidth()-10);
            myStage.setHeight(myStage.getHeight()+0.4);

        });

        tim2.getKeyFrames().add(changeSceneSize);
        tim2.setCycleCount(80);

        Timeline swtichscenez=new Timeline(new KeyFrame(Duration.millis(1),e-> {
            //Parent root = null;
            GamePlayController ctrl = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "GamePlay.fxml"));

                p_root = (Parent) loader.load();
                ctrl = loader.getController();
//                ctrl.init(table.getSelectionModel().getSelectedItem());

//                p_root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Scene gameplayscene=new Scene(p_root,525,810);
            //GamePlayController.setupScense(gameplayscene);

            myStage.setScene(gameplayscene);
            getCurrentScene=gameplayscene;
            (ctrl).setupScene(getCurrentScene,myStage);

        }));


       /*GamePlayController GamePlayControl=new GamePlayController();
        Timeline setupGame=new Timeline(new KeyFrame(Duration.millis(1),e -> {
            GamePlayControl.setupGame();

        }));*/
        //Delay should be 1600 setting to 1 for faster testing
        new SequentialTransition(delay(1600),tim2,swtichscenez).play();



    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            introTransition(-1);
        }));
        new SequentialTransition(delay(1000), intro).play();
    }


}
