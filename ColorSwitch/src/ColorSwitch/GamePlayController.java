package ColorSwitch;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @FXML
    private Group PauseMenuGroup;

    @FXML
    private AnchorPane PauseMenuPane;

    @FXML
    private Button ResumeGameButton;

    @FXML
    private Group CollideGroup;
    @FXML
    private Label pauseLabel;

    @FXML
    private Button SaveGameButton;

    @FXML
    private Button MainMenuButton;
    @FXML
    private Button MainMenuButton1;

    private static Scene gamePlayScene;
    private ArrayList<Obstacles> gameObstacles;
    private ArrayList<ImageView> images;
    private double Y_Ball;
    private double prevY_Ball;
    private boolean gameStarted=false;
    private Timeline gravity;
    private boolean found = false;
    private int count = 0;
    private double speedX=0;
    private double speedY=0;
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
        //InputStream stream = new FileInputStream("assets/Pic2.png");

        Image image = new Image("assets/Pic2.png",true);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(163);
        imageView.setY(-720);
        imageView.setFitWidth(175);
        imageView.setPreserveRatio(true);
        images=new ArrayList<>();
        images.add(imageView);
        gamePlayAnchorPane.getChildren().add(imageView);
        Image image2 = new Image("assets/Pic5.png",true);

        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);
        imageView2.setX(163);
        imageView2.setY(-880);
        imageView2.setFitWidth(175);
        imageView2.setPreserveRatio(true);
        images.add(imageView2);
        gamePlayAnchorPane.getChildren().add(imageView2);
        Image image3 = new Image("assets/star.png",true);

        ImageView imageView3 = new ImageView();
        imageView3.setImage(image3);
        imageView3.setX(245);
        imageView3.setY(12);
        imageView3.setFitWidth(35);
        imageView3.setPreserveRatio(true);
        images.add(imageView3);
        gamePlayAnchorPane.getChildren().add(imageView3);

        Image image4 = new Image("assets/star.png",true);

        ImageView imageView4 = new ImageView();
        imageView4.setImage(image4);
        imageView4.setX(245);
        imageView4.setY(428);
        imageView4.setFitWidth(35);
        imageView4.setPreserveRatio(true);
        images.add(imageView4);
        gamePlayAnchorPane.getChildren().add(imageView4);

        Image image5 = new Image("assets/star.png",true);

        ImageView imageView5 = new ImageView();
        imageView5.setImage(image5);
        imageView5.setX(245);
        imageView5.setY(-398);
        imageView5.setFitWidth(35);
        imageView5.setPreserveRatio(true);
        images.add(imageView5);
        gamePlayAnchorPane.getChildren().add(imageView5);

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
            gravity=new Timeline();
            gravity.setCycleCount(Animation.INDEFINITE);
            KeyFrame grav=new KeyFrame(Duration.millis(15),e -> {
                update();
                boolean test = detectCollision();
                if(!test) {
                    //gravity.pause();
                    if(gravity!=null)gravity.pause();
                    for (Obstacles gameObstacle : gameObstacles) {
                        gameObstacle.stopRotation();
                    }
                    currentBall.getGameBall().setOpacity(0);
                    for(int i=0;i<gameObstacles.size();i++) {
                        ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
                    }
                    for(int i=0;i<images.size();i++) {
                        (images.get(i)).setOpacity(0);
                    }
                    CollideGroup.setDisable(false);
                    CollideGroup.setVisible(true);


                    /*for(int i = 0;i<gameObstacles.size();i++) {
                        gameObstacles.get(i).stopRotation();
                    }*/
                    return;
                }
                if(speedY<-0.1)
                {
                    for(Obstacles o: gameObstacles)
                    {
                        o.getGroup().setLayoutY(o.getGroup().getLayoutY()+1.5);
                    }
                    for(ImageView o: images)
                    {


                        o.setY(o.getY()+1.5);

                       // o.getGroup().setLayoutY(o.getGroup().getLayoutY()+1.2);
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

    @FXML
    void backtomain(MouseEvent event) {

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

                if(event.getCode() == KeyCode.P) {
                    if(gravity!=null)gravity.pause();
                    for (Obstacles gameObstacle : gameObstacles) {
                        gameObstacle.stopRotation();
                    }
                    currentBall.getGameBall().setOpacity(0);
                    for(int i=0;i<gameObstacles.size();i++) {
                        ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
                    }
                    PauseMenuGroup.setDisable(false);
                    PauseMenuGroup.setVisible(true);

                    for(int i=0;i<images.size();i++) {
                        (images.get(i)).setOpacity(0);
                    }
                    ResumeGameButton.setOnAction(new EventHandler<ActionEvent>(){

                        @Override
                        public void handle(ActionEvent actionEvent) {
                            PauseMenuGroup.setVisible(false);
                            PauseMenuGroup.setDisable(true);
                            for(int i=0;i<gameObstacles.size();i++) {
                                ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(1);
                            }
                            currentBall.getGameBall().setOpacity(1);
                            for (Obstacles gameObstacle : gameObstacles) {
                                gameObstacle.Rotation();
                            }
                            if(gravity!=null)gravity.play();
                        }
                    });
                }
            }
        });
        System.out.println("final");
    }
}