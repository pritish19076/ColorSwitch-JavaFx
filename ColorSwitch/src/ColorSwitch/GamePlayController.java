package ColorSwitch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.lang.reflect.Array;
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
    double speedX=0;
    double speedY=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("dsa");
        currentBall = new Ball(263,707,15,1,3,0);
        currentBall.display(gamePlayAnchorPane);
        gameObstacles = new ArrayList<>();
        Obstacles obs1 = new NormalCircle(3000,true,100.0f,100.0f,263,440);
        gameObstacles.add(obs1);
        Obstacles obs2 = new NormalCircle(3000,true,100.0f,100.0f,263,20);
        gameObstacles.add(obs2);
        obs1.display(gamePlayAnchorPane);
        obs2.display(gamePlayAnchorPane);
        Obstacles obs3 = new NormalCircle(3000,true,100.0f,100.0f,263,-380);
        gameObstacles.add(obs3);
        obs3.display(gamePlayAnchorPane);
        CommonAnimation.fade(currentBall.getGameBall(),1).play();
        CommonAnimation.fade(((NormalCircle)obs1).getCircle(),1).play();
        CommonAnimation.fade(((NormalCircle)obs2).getCircle(),1).play();
        CommonAnimation.fade(((NormalCircle)obs3).getCircle(),1).play();

        System.out.println("sadasd");

    }

    public boolean detectCollision() {
        for (Obstacles o : gameObstacles) {
            Group tmpArcGroup = o.getGroup();
            ArrayList<ArcClass> tmpList = ((NormalCircle)o).getCircleArc();
            ArcClass intersectingArc = null;
            for(int i = 0;i<tmpList.size();i++) {
                Shape intersect = Shape.intersect(currentBall.getGameBall(),tmpList.get(i).getArcQuadrant());
                boolean isIntersected = false;
                if(intersect.getBoundsInLocal().getWidth() != -1) {
//                    System.out.println(intersect.getBoundsInLocal().getWidth());
                    intersectingArc = tmpList.get(i);
                    isIntersected = true;
                }
                if(isIntersected && intersectingArc.getColor() == currentBall.getBallColor()) {
                    System.out.println(intersectingArc.getColor());
                    return true;
                }
                else if(isIntersected && intersectingArc.getColor() != currentBall.getBallColor()) {
                    return false;
                }

            }
        }
        return true;
    }

    public void runGravity(){
            Timeline gravity=new Timeline();
            gravity.setCycleCount(Animation.INDEFINITE);

            KeyFrame grav=new KeyFrame(Duration.millis(15),e -> {
                update();
                boolean test = detectCollision();
                if(!test) {
                    gravity.pause();
                }
                if(speedY<-0.1)
                {
                    for(Obstacles o: gameObstacles)
                    {

                        o.getGroup().setLayoutY(o.getGroup().getLayoutY()+1.2);
                    }

                }
            });
            gravity.getKeyFrames().add(grav);
            gravity.play();

    }
    public void accelerate(double accelerationY) {

        speedY += accelerationY;
    }

    public void move(double yDelta) {
        currentBall.getGameBall().setCenterY(currentBall.getGameBall().getCenterY()+yDelta);

    }

    public void update() {
        move(speedY);
        accelerate( 0.04); // gravity accelerates the object downwards each tick Range - 0.03 to 0.04
    }

    public void setupScene(Scene p_scene) {
        System.out.println("initial");
        gamePlayScene = p_scene;
        gamePlayScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.W){
                    speedY-=2; //Range From 0.05 to 0.08

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