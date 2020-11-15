package ColorSwitch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GamePlayController implements Initializable {

    @FXML
    private Label Score;
    private Ball currentBall;
    @FXML
    private AnchorPane gamePlayAnchorPane;

    private static Scene gamePlayScene;
    private ArrayList<Obstacles> gameObstacles;
    private double Y_Ball;
    private double prevY_Ball;
    private boolean gameStarted=false;
    boolean found = false;
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("dsa");
        currentBall = new Ball(263,707,20,1,3,0);
        currentBall.display(gamePlayAnchorPane);
        gameObstacles = new ArrayList<>();
        Obstacles obs1 = new NormalCircle(3000,true,100.0f,100.0f,263,440);
        gameObstacles.add(obs1);
        obs1.display(gamePlayAnchorPane);
        CommonAnimation.fade(currentBall.getGameBall(),1).play();
        CommonAnimation.fade(((NormalCircle)obs1).getCircle(),1).play();
        System.out.println("sadasd");

        /*
        gamePlayAnchorPane.requestFocus();
        gamePlayAnchorPane.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.SHIFT) System.out.println("as");
            }

        });
        gamePlayAnchorPane.requestFocus();
           */
        /*gamePlayScene=GameMain.getCurrentScene;
        gamePlayScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.SHIFT) System.out.println("as");
            }

        });
        */

    }

    public void runGravity(){
            Timeline gravity=new Timeline();
            gravity.setCycleCount(Animation.INDEFINITE);

            KeyFrame grav=new KeyFrame(Duration.millis(20),e -> {
                currentBall.getGameBall().setCenterY(currentBall.getGameBall().getCenterY()+3);
                if(Y_Ball+60<prevY_Ball){
                    System.out.println("Yball: "+Y_Ball+ "prevY: "+ prevY_Ball );
                    prevY_Ball=currentBall.getGameBall().getCenterY();
                    found = true;
                    for(Obstacles o: gameObstacles)
                    {
                        o.getGroup().setLayoutY(o.getGroup().getLayoutY()+40);
                    }
                   // Y_Ball=currentBall.getGameBall().getCenterY()-100;

                }
                Y_Ball=currentBall.getGameBall().getCenterY();
                count++;
                System.out.println("BaharkaYball: "+Y_Ball);

            });
            gravity.getKeyFrames().add(grav);
            gravity.play();

    }
    public void setupScene(Scene p_scene) {
        System.out.println("initial");
        gamePlayScene = p_scene;
        gamePlayScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.W){
                    System.out.println(Score.getText());
                    System.out.println("jhjhhjjh");
                    //currentBall.moveTheBall(null,-100,100);
                    Timeline move=new Timeline();
                    move.setCycleCount(10);
                    KeyFrame mov=new KeyFrame(Duration.millis(2),e -> {
                        currentBall.getGameBall().setCenterY(currentBall.getGameBall().getCenterY()-10);
                    });
                    move.getKeyFrames().add(mov);
                    move.play();
                    if(!gameStarted){
                        Y_Ball=currentBall.getGameBall().getCenterY();
                        prevY_Ball=currentBall.getGameBall().getCenterY();
                        gameStarted=true;
                        runGravity();

                    }
                }
            }

        });
        System.out.println("final");
    }


}
