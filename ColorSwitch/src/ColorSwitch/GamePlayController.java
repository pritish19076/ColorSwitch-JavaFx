package ColorSwitch;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
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
import javafx.stage.Stage;
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
    private Stage myStage;
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
    NormalCircle c1;

    private static Scene gamePlayScene;
    private ArrayList<Obstacles> gameObstacles;
    private ArrayList<ImageView> images;
    private ArrayList<ColorChanger> colors;

    private double Y_Ball;
    private double prevY_Ball;
    private boolean gameStarted=false;
    private Timeline gravity;
    private boolean found = false;
    private int count = 0;
    private double speedX=0;
    private double speedY=0;
    private ColorChanger CC1;
    private ColorChanger CC2;
    NormalCircle c2;
//    ConcentricCircles tmpConCircles;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("dsa");
        currentBall = new Ball(263,707,15,1,3,0);
        currentBall.display(gamePlayAnchorPane);
        gameObstacles = new ArrayList<>();
        Obstacles obs1 = new NormalCircle(3000,true,100.0f,100.0f,263,440);
        gameObstacles.add(obs1);
        CC1 = new ColorChanger(263,230,20f,20f,1);
        CC1.display(gamePlayAnchorPane);

        Obstacles obs2 = new NormalCircle(3000,true,100.0f,100.0f,263,20);
        gameObstacles.add(obs2);
        obs1.display(gamePlayAnchorPane);
        obs2.display(gamePlayAnchorPane);
        CC2 = new ColorChanger(263,-200,20f,20f,1);
        CC2.display(gamePlayAnchorPane);
        Obstacles obs3 = new NormalCircle(3000,true,100.0f,100.0f,263,-380);
        gameObstacles.add(obs3);
        obs3.display(gamePlayAnchorPane);
//        c1 = new NormalCircle(3000,true,100f,100f,263,440);
//        c2 = new NormalCircle(3000,false,120f,120f,263,440);
//        c1.display(gamePlayAnchorPane);
//        c1.getCircle().setOpacity(1);
//        c2.display(gamePlayAnchorPane);
//        c2.getCircle().setOpacity(1);
//        tmpConCircles = new ConcentricCircles(3000,true,false,100f,100f,110f,110f,263,440);
//        tmpConCircles.display(gamePlayAnchorPane);
        //InputStream stream = new FileInputStream("assets/Pic2.png");

        Image image = new Image("assets/Pic2.png",true);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(163);
        imageView.setY(-750);
        imageView.setFitWidth(175);
        imageView.setPreserveRatio(true);
        images=new ArrayList<>();
        images.add(imageView);
        gamePlayAnchorPane.getChildren().add(imageView);
        Image image2 = new Image("assets/Pic5.png",true);

        ImageView imageView2 = new ImageView();
        imageView2.setImage(image2);
        imageView2.setX(163);
        imageView2.setY(-980);
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
        colors=new ArrayList<>();
        colors.add(CC1);
        colors.add(CC2);
        CommonAnimation.fade(currentBall.getGameBall(),1).play();
        CommonAnimation.fade(((NormalCircle)obs1).getCircle(),1).play();
        CommonAnimation.fade(((NormalCircle)obs2).getCircle(),1).play();
        CommonAnimation.fade(((NormalCircle)obs3).getCircle(),1).play();

        System.out.println("sadasd");

    }

    public boolean detectCollision() {
        for (Obstacles o : gameObstacles) {
            boolean tmp = o.onCollide((GameObjects)currentBall);
            if(tmp) {
                return false;
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
                    CC1.getArcGroup().setOpacity(0);
                    CC2.getArcGroup().setOpacity(0);
                    currentBall.getGameBall().setOpacity(0);
                    for(int i=0;i<gameObstacles.size();i++) {
                        ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
                    }
                    for(int i=0;i<images.size();i++) {
                        (images.get(i)).setOpacity(0);
                    }

                    for(int i=0;i<colors.size();i++) {
                        (colors.get(i)).getArcGroup().setOpacity(0);
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
                    for(int i=0;i<colors.size();i++) {
                        (colors.get(i)).getArcGroup().setLayoutY((colors.get(i)).getArcGroup().getLayoutY()+1.5);
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

        for(int i=0;i<gameObstacles.size();i++) {
            ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
        }
        for(int i=0;i<images.size();i++) {
            (images.get(i)).setOpacity(0);
        }

        for(int i=0;i<colors.size();i++) {
            (colors.get(i)).getArcGroup().setOpacity(0);
        }
        PauseMenuGroup.setOpacity(0);
        CollideGroup.setOpacity(0);
        Timeline tim2=new Timeline();
        KeyFrame changeSceneSize=new KeyFrame(Duration.millis(20),e -> {
            myStage.setWidth(myStage.getWidth()+10);
            myStage.setHeight(myStage.getHeight()-0.4);

        });
        tim2.getKeyFrames().add(changeSceneSize);
        tim2.setCycleCount(80);
        Timeline swtichscenez=new Timeline(new KeyFrame(Duration.millis(1),e-> {
            Parent root = null;
            GamePlayController ctrl = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "ColorSwitch.fxml"));

               root = (Parent) loader.load();
                ctrl = loader.getController();
//                ctrl.init(table.getSelectionModel().getSelectedItem());

//                p_root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
           // Scene gameplayscene=new Scene(p_root,525,810);
            //GamePlayController.setupScense(gameplayscene);

           // myStage.setScene(gameplayscene);
           // getCurrentScene=gameplayscene;
           //((GameMain)ctrl).setupScene(gamePlayScene,myStage);


        }));
        System.out.println("Back to Main Menu");


    }
    public void update() {
        move(speedY);
        accelerate( 0.04); // gravity accelerates the object downwards each tick Range - 0.03 to 0.04
    }

    public void setupScene(Scene p_scene,Stage myStage) {
        System.out.println("initial");
        gamePlayScene = p_scene;
        this.myStage=myStage;
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
                    /*
                    for(int i=0;i<colors.size();i++) {
                        (colors.get(i)).getArcGroup().setOpacity(0.3);
                    }*/
                    CC1.getArcGroup().setOpacity(0);
                    CC2.getArcGroup().setOpacity(0);
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
                            CC1.getArcGroup().setOpacity(1);
                            CC2.getArcGroup().setOpacity(1);
                            for(int i=0;i<images.size();i++) {
                                (images.get(i)).setOpacity(1);
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