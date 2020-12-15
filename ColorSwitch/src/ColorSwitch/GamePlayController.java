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


import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class GamePlayController implements Initializable {

    @FXML
    private Label Score;
    private Ball currentBall;
    private Player currentPlayer;
    public boolean fromload=false;
    @FXML
    private AnchorPane gamePlayAnchorPane;
    @FXML
    private Group PauseMenuGroup;

    @FXML
    private AnchorPane PauseMenuPane;

    @FXML
    private Button ResumeGameButton;
    private AnchorPane panel;
    private Obstacles collidedObstacle;
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

    int totalstarscollected=0;
    private static Stage myStage;
    private EventHandler<MouseEvent> resumeGame;
    private EventHandler<MouseEvent> saveGame;
    private EventHandler<MouseEvent> restartGame;
    private EventHandler<MouseEvent> reviveGame;
    private EventHandler<MouseEvent> backtoMainMenu;
    private static Scene gamePlayScene;
    private ArrayList<Obstacles> gameObstacles;
    private ArrayList<ImageView> images;
    private ArrayList<ColorChanger> colors;
    private ArrayList<GameObjects> gameObjects;
    private ArrayList<Obstacles> gameObstaclesJoined;
    private double Y_Ball;
    private double prevY_Ball;
    private boolean gameStarted=false;
    private Timeline gravity;
    private boolean found = false;
    private int count = 0;
    private double speedX=0;
    private double speedY=0;
    private ArrayList<ArrayList<Group>> customObstacleList;
    int prevobstacley=900;

    public void updateScore(int score)
    {
        Score.setText(Integer.toString(score));
    }
    
    private void moveDown(double val) {
        for(GameObjects o: gameObjects)
        {
            if(o instanceof Star)
            {
                ((Star)o).getImageView().setLayoutY(((Star)o).getImageView().getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (((Star)o).imageView.getLayoutY());
                o.setPosition(newX,newY);
            }
            else if(o instanceof ColorChanger){
                ((ColorChanger)o).getArcGroup().setLayoutY(((ColorChanger)o).getArcGroup().getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (((ColorChanger)o).getArcGroup().getLayoutY());
                o.setPosition(newX,newY);
            }
            else if(o instanceof DoubleStackCircle) {
                ArrayList<Group> tmpList = ((DoubleStackCircle)o).getGroupList();
                tmpList.get(0).setLayoutY(tmpList.get(0).getLayoutY()+val);
                tmpList.get(1).setLayoutY(tmpList.get(1).getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (tmpList.get(0).getLayoutY());
                o.setPosition(newX,newY);
            }
            else if(o instanceof ConcentricCircles) {
                ArrayList<Group> tmpList = ((ConcentricCircles)o).getAllGroupList();
                tmpList.get(0).setLayoutY(tmpList.get(0).getLayoutY()+val);
                tmpList.get(1).setLayoutY(tmpList.get(1).getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (tmpList.get(0).getLayoutY());
                o.setPosition(newX,newY);
            }
            else if(o instanceof TripleConcentricCircles) {
                ArrayList<Group> tmpList = ((TripleConcentricCircles)o).getAllGroupList();
                tmpList.get(0).setLayoutY(tmpList.get(0).getLayoutY()+val);
                tmpList.get(1).setLayoutY(tmpList.get(1).getLayoutY()+val);
                tmpList.get(2).setLayoutY(tmpList.get(2).getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (tmpList.get(0).getLayoutY());
                o.setPosition(newX,newY);
            }
            else if(o instanceof TripleStackCircle) {
                ArrayList<Group> tmpList = ((TripleStackCircle)o).getAllGroupList();
                tmpList.get(0).setLayoutY(tmpList.get(0).getLayoutY()+val);
                tmpList.get(1).setLayoutY(tmpList.get(1).getLayoutY()+val);
                tmpList.get(2).setLayoutY(tmpList.get(2).getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (tmpList.get(0).getLayoutY());
                o.setPosition(newX,newY);
            }
            else if (o instanceof Obstacles)
            {
                ((Obstacles)o).getGroup().setLayoutY(((Obstacles)o).getGroup().getLayoutY()+val);
                float newX = o.getPositionX();
                float newY = (float) (((Obstacles)o).getGroup().getLayoutY());
                o.setPosition(newX,newY);

            }
        }
        prevobstacley+=val;

    }

    private void addObstacle(int c){
        int distance=400;
        if(c==1){
            Obstacles obs1=new NormalCircle(3000,true,100.0f,100.0f,263,prevobstacley - distance);
            obs1.setObjectType("NormalCircle");
            gameObjects.add(obs1);
            gameObstacles.add(obs1);
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            gameObjects.add(s);
            s.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            gameObjects.add(CC1);
            CC1.display(gamePlayAnchorPane);

            prevobstacley-=distance;
        }
        if(c==2){
            Obstacles obs1=new Square(3000,true,100,263,prevobstacley-distance);
            obs1.setObjectType("Square");
            gameObjects.add(obs1);
            gameObstacles.add(obs1);
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            gameObjects.add(s);
            s.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);

            prevobstacley-=distance;
        }

        if(c==3){
            Obstacles obs1=new Cross(3000,true,150,263,prevobstacley-distance);
            obs1.setObjectType("Cross");
            gameObjects.add(obs1);
            gameObstacles.add(obs1);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);

            obs1.display(gamePlayAnchorPane);
            prevobstacley-=distance;
        }

        if(c==4){
            Obstacles obs1=new Diamond(3000,true,100,263,prevobstacley-distance);
            obs1.setObjectType("Diamond");
            gameObjects.add(obs1);
            gameObstacles.add(obs1);
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            gameObjects.add(s);
            s.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            gameObjects.add(CC1);
            CC1.display(gamePlayAnchorPane);

            prevobstacley-=distance;
        }

        if(c==5){
            Obstacles obs1=new LongRod(distance,true,263,prevobstacley-distance);
            obs1.setObjectType("LongRod");
            gameObjects.add(obs1);
            gameObstacles.add(obs1);
            obs1.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            gameObjects.add(CC1);
            CC1.display(gamePlayAnchorPane);

            prevobstacley-=distance;
        }

        if(c==6){
            Obstacles obs1=new DoubleStackCircle(3000,263,prevobstacley - (distance*2));
            obs1.setObjectType("DoubleStackCircle");
            customObstacleList.add(((DoubleStackCircle)obs1).getGroupList());
            gameObjects.add(obs1);
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            gameObjects.add(s);
            s.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);

            prevobstacley-=(distance*2);
        }

        if(c==7) {
            Obstacles obs1 = new ConcentricCircles(3000,263,prevobstacley - distance);
            obs1.setObjectType("ConcentricCircles");
            gameObjects.add(obs1);
            customObstacleList.add(((ConcentricCircles)obs1).getAllGroupList());
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            gameObjects.add(s);
            s.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);

            prevobstacley-=(distance);
        }

        if(c==8) {
            Obstacles obs1 = new TripleConcentricCircles(4000,3000,263,prevobstacley-distance);
            obs1.setObjectType("TripleConcentricCircles");
            gameObjects.add(obs1);
            customObstacleList.add(((TripleConcentricCircles)obs1).getAllGroupList());
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            gameObjects.add(s);
            s.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);

            prevobstacley-=(distance);
        }

        if(c==9){
            Obstacles obs1=new TripleStackCircle(3000,263,prevobstacley - (distance*2));
            obs1.setObjectType("TripleStackCircle");
            gameObjects.add(obs1);
            customObstacleList.add(((TripleStackCircle)obs1).getAllGroupList());
            obs1.display(gamePlayAnchorPane);

            Star s = new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
            s.setObjectType("Star");
            Star s2 = new Star(obs1.getPositionX()-16,obs1.getPositionY()+215-12);
            s2.setObjectType("Star");
            Star s3 = new Star(obs1.getPositionX()-16,obs1.getPositionY()+430-12);
            s3.setObjectType("Star");
            gameObjects.add(s);
            gameObjects.add(s2);
            gameObjects.add(s3);
            s.display(gamePlayAnchorPane);
            s2.display(gamePlayAnchorPane);
            s3.display(gamePlayAnchorPane);

            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-185,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);

            prevobstacley-=(distance*2);
        }

    }

    public void Serialize(String fileName) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(currentBall);
            for (GameObjects gameObject : gameObjects) {
                out.writeObject(gameObject);
            }
        }finally {
            assert out != null;
            out.close();
        }
    }

    public void SerializePlayer(String fileName) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            try {
                out.writeObject(currentPlayer);
            }
            catch (NullPointerException e) {
                System.out.println("PLAYER NOT INITIALIZED");
            }
        }finally {
            assert out != null;
            out.close();
        }
    }

    public String getFileName(String rootDirectory) {
        String currentPlayerName = null;
        int gamesPlayed = 0;
        try {
             currentPlayerName = currentPlayer.getName();
             gamesPlayed = currentPlayer.getGamesPlayed();
        }catch (NullPointerException e) {
            System.out.println("PLAYER NOT INITIALIZED");
            return null;
        }
        String fileName = currentPlayerName;
        fileName = fileName.concat("_");
        fileName = fileName.concat(Integer.toString(gamesPlayed));
        String finalFile = rootDirectory;
        finalFile = finalFile.concat("\\");
        finalFile = finalFile.concat(fileName);
        finalFile = finalFile.concat(".txt");
        return finalFile;
    }

    public String getPlayerFileName(String rootDirectory) {
        String playerName = null;
        try{
            playerName = currentPlayer.getName();
        }
        catch (NullPointerException e) {
            System.out.println("Player not initialized");
        }
        rootDirectory = rootDirectory.concat("\\");
        rootDirectory = rootDirectory.concat(playerName);
        rootDirectory = rootDirectory.concat(".txt");
        return rootDirectory;
    }

    public void letsgetitstarted() {
        currentBall = new Ball(263, 707, 15, 4, 3, 1);
        currentBall.setObjectType("Ball");
        currentBall.getGameBall().setCenterY(707);
        currentBall.getGameBall().setCenterX(263);
        currentBall.display(gamePlayAnchorPane);
        gameObstacles = new ArrayList<>();
        gameObjects = new ArrayList<>();
        gameObjects.add(currentBall);
        addObstacle(1);
        addObstacle(1);
        /*
        for (GameObjects o : gameObjects) {
            if (o instanceof Ball) CommonAnimation.fade(((Ball) o).getGameBall(), 1).play();
            if (o instanceof NormalCircle) CommonAnimation.fade(((NormalCircle) o).getCircle(), 1).play();
            if (o instanceof Star) CommonAnimation.fade(((Star) o).getImageView(), 1).play();
        }
        */
        speedY = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customObstacleList=new ArrayList<>();
        resumeGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gamePlayAnchorPane.getChildren().remove(panel);
                if (gravity != null) gravity.play();
            }
        };
        saveGame=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String playerFile = getPlayerFileName("C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\SavedPlayers");
                    String fileName = getFileName("C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\SavedGames");
                    SerializePlayer(playerFile);
                    Serialize(fileName);
                    Serialize("out.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        reviveGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (gravity != null) gravity.play();
                if(currentPlayer.getCurrentscore()>=-100){
                    currentPlayer.setCurrentScore(currentPlayer.getCurrentscore()-3);
                    Score.setText(Integer.toString(currentPlayer.getCurrentscore()));
                    gamePlayAnchorPane.getChildren().remove(panel);
                    for (int i=0;i<gameObjects.size();i++) {
                        GameObjects o=gameObjects.get(i);
                        if(o instanceof Obstacles&&((Obstacles)o)==collidedObstacle)
                        {
                            if(gameObjects.get(i+1) instanceof Star){
                                gamePlayAnchorPane.getChildren().remove(((Star) gameObjects.get(i+1)).getImageView());
                                gameObjects.remove(i+1);
                            }
                            gamePlayAnchorPane.getChildren().remove(((Obstacles)gameObjects.get(i)).getGroup());
                            gameObjects.remove(i);
                        }
                    }

                }
                else{
                    //Exception and Handling
                }

            }
        };
        restartGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GamePlayController temp = null;
                Parent p_root = null;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
                    p_root = (Parent) loader.load();
                    temp = loader.getController();
                    temp.letsgetitstarted();
//                ctrl.init(table.getSelectionModel().getSelectedItem());

//                p_root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Scene gameplayscene = new Scene(p_root, 525, 810);
                //GamePlayController.setupScense(gameplayscene);

                myStage.setScene(gameplayscene);
                //getCurrentScene=gameplayscene;
                //gameStarted=false;
                gravity.play();
                temp.gamePlayAnchorPane = (AnchorPane) p_root;
                temp.setupScene(gameplayscene, myStage,currentPlayer);
            }
        };
        backtoMainMenu = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                /*for(int i=0;i<gameObstacles.size();i++) {
                    ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
                }
                for(int i=0;i<images.size();i++) {
                    (images.get(i)).setOpacity(0);
                }*/
                /*
                for(int i=0;i<colors.size();i++) {
                    (colors.get(i)).getArcGroup().setOpacity(0);
                }
                */
                //PauseMenuGroup.setOpacity(0);
                //CollideGroup.setOpacity(0);
                //Opacity Lists
                gamePlayAnchorPane.getChildren().remove(panel);
                gamePlayAnchorPane.getChildren().remove(Score);
                Timeline tim2 = new Timeline();
                KeyFrame changeSceneSize = new KeyFrame(Duration.millis(20), e -> {
                    if (myStage.getWidth() < 1280) myStage.setWidth(myStage.getWidth() + 10);
                    if (myStage.getHeight() > 760) myStage.setHeight(myStage.getHeight() - 2);
                });
                tim2.getKeyFrames().add(changeSceneSize);
                tim2.setCycleCount(100);

                Timeline swtichscenez = new Timeline(new KeyFrame(Duration.millis(1), e -> {
                    Parent root = null;
                    GameMain ctrl = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ColorSwitch.fxml"));
                        root = (Parent) loader.load();
                        ctrl = loader.getController();
                        Scene mainmenuscene = new Scene(root, 1280, 720);
                        myStage.setScene(mainmenuscene);

//
//                         ctrl.init(table.getSelectionModel().getSelectedItem());

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
                new SequentialTransition(CommonAnimation.delay(1), tim2, swtichscenez).play();

            }
        };
        //currentBall.display(gamePlayAnchorPane);

        /*Obstacles obs1 = new NormalCircle(3000,true,100.0f,100.0f,263,440);
        gameObstacles.add(obs1);
        //CC1.display(gamePlayAnchorPane);
        Obstacles obs2 = new NormalCircle(3000,true,100.0f,100.0f,263,20);
        gameObstacles.add(obs2);
        //obs1.display(gamePlayAnchorPane);
        //obs2.display(gamePlayAnchorPane);
        //CC2 = new ColorChanger(263,-200,20f,20f,1);
        //CC2.display(gamePlayAnchorPane);
        Obstacles obs3 = new NormalCircle(3000,true,100.0f,100.0f,263,-380);
        gameObstacles.add(obs3);
        //obs3.display(gamePlayAnchorPane);
        GameObjects star1=new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
        GameObjects star2=new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
        GameObjects star3=new Star(obs1.getPositionX()-16,obs1.getPositionY()-12);
        gameObjects.add(star1);gameObjects.add(star2);gameObjects.add(star3);
        gameObjects.add(obs1);gameObjects.add(obs2);gameObjects.add(obs3);
        //gameObjects.add(CC1);gameObjects.add(CC2);
        gameObjects.add(currentBall);*/


        //CommonAnimation.fade(currentBall.getGameBall(),1).play();
        //CommonAnimation.fade(((NormalCircle)obs1).getCircle(),1).play();
        //CommonAnimation.fade(((NormalCircle)obs2).getCircle(),1).play();
        //CommonAnimation.fade(((NormalCircle)obs3).getCircle(),1).play();



    }

    public boolean detectCollision() {
        boolean tmp = false;
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObjects o = gameObjects.get(i);
            tmp = o.onCollide((GameObjects) currentBall);
            if (tmp && (o instanceof Star || o instanceof ColorChanger)) {
                if (o instanceof Star) totalstarscollected++;
                if(totalstarscollected%3==0){
                    for(int j=0;j<gameObjects.size();j++){
                        GameObjects g=gameObjects.get(j);
                        if(g instanceof DoubleStackCircle) {
                            ArrayList<Group> list=((DoubleStackCircle)g).getGroupList();
                            for(Group grp: list) gamePlayAnchorPane.getChildren().remove(grp);
                        }
                        else if(g instanceof ConcentricCircles) {

                            ArrayList<Group> list=((ConcentricCircles)g).getAllGroupList();
                            for(Group grp: list) gamePlayAnchorPane.getChildren().remove(grp);
                        }
                        else if(g instanceof TripleConcentricCircles) {

                            ArrayList<Group> list=((TripleConcentricCircles)g).getAllGroupList();
                            for(Group grp: list) gamePlayAnchorPane.getChildren().remove(grp);
                        }
                        else if(g instanceof TripleStackCircle) {
                            ArrayList<Group> list=((TripleStackCircle)g).getAllGroupList();
                            for(Group grp: list) gamePlayAnchorPane.getChildren().remove(grp);
                        }
                        else if(g instanceof Obstacles)
                        {

                            gamePlayAnchorPane.getChildren().remove(((Obstacles) g).getGroup());
                            gameObjects.remove(j);
                            break;
                        }
                    }
                    i--;
                }
                gameObjects.remove(i);
                i--;
                int randomnum = 0;
                Random random = new Random();
                if(totalstarscollected==0){
                    randomnum=1;
                }
                else if (totalstarscollected <= 8) {
                    randomnum = random.nextInt(totalstarscollected) + 1;
                } else randomnum = random.nextInt(8) + 1;
                addObstacle(randomnum);
                return false;
            }
            if (tmp & o instanceof Obstacles) {

                collidedObstacle = ((Obstacles) o);
                return tmp;
            }
        }
        return tmp;
    }

    public void runGravity() {
        if (gravity == null) {
            gravity = new Timeline();
            gravity.setCycleCount(Animation.INDEFINITE);
            KeyFrame grav = new KeyFrame(Duration.millis(15), e -> {
                update();
                boolean test = detectCollision();
                test=false;//OnCollide Disabled
                if (test) {
                    //gravity.pause();
                    if (gravity != null) gravity.pause();

                    /*for (GameObjects gameObj : gameObjects) {
                        if(gameObj instanceof Obstacles)((Obstacles)(gameObj)).stopRotation();
                    }*/
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviveMenu.fxml"));
                    try {
                        panel = (AnchorPane) loader.load();
                        panel.getChildren().get(0).setOnMouseClicked(reviveGame);
                        panel.getChildren().get(1).setOnMouseClicked(restartGame);
                        panel.getChildren().get(2).setOnMouseClicked(backtoMainMenu);
                        gamePlayAnchorPane.getChildren().add(panel);
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                    //if(gravity!=null)gravity.pause();

                    //CC1.getArcGroup().setOpacity(0);
                    //CC2.getArcGroup().setOpacity(0);
                    //currentBall.getGameBall().setOpacity(0);
                    /*for(int i=0;i<gameObstacles.size();i++) {
                       ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
                    }*/

                    //CollideGroup.setDisable(false);
                    //CollideGroup.setVisible(true);


                    /*for(int i = 0;i<gameObstacles.size();i++) {
                        gameObstacles.get(i).stopRotation();
                    }*/

                    return;
                }

                if (speedY < -0.1) {
                   moveDown(1.8);


                }
                if (speedY < -1) {
                    moveDown(1.5);


                }

            });
            gravity.getKeyFrames().add(grav);
            gravity.play();
        } else gravity.play();
    }

    public void accelerate(double accelerationY) {
        speedY += accelerationY;
    }

    public void move(double yDelta) {
        currentBall.getGameBall().setCenterY(currentBall.getGameBall().getCenterY() + yDelta);
    }


    @FXML
    void backtomain(MouseEvent event) {


    }

    public void update() {
        move(speedY);
        accelerate(0.04); // gravity accelerates the object downwards each tick Range - 0.03 to 0.04
    }
    public void loadtheGame(String filename) throws IOException, ClassNotFoundException {
        ReGenerateObstacles regenObs = new ReGenerateObstacles();
        gameObjects = regenObs.regenerateGameObjects(filename);
        for(GameObjects g:gameObjects){
            if(g instanceof Ball)currentBall=(Ball)g;
                g.display(gamePlayAnchorPane);
        }
        gameObstacles=new ArrayList<>();
        prevobstacley = (int) gameObjects.get((gameObjects.size()-1)).getPositionY();

    }
    public void setupScene(Scene p_scene, Stage myStage, Player p_player) {
        gamePlayScene = p_scene;
        this.myStage = myStage;
        currentPlayer = p_player;
        gamePlayScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    speedY -= 2; //Range From 0.05 to 0.08

                    if (!gameStarted) {
                        Y_Ball = currentBall.getGameBall().getCenterY();
                        prevY_Ball = currentBall.getGameBall().getCenterY();
                        gameStarted = true;
                        runGravity();
                    }
                }

                if (event.getCode() == KeyCode.P) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseMenu.fxml"));
                    try {
                        panel = (AnchorPane) loader.load();
                        panel.getChildren().get(0).setOnMouseClicked(resumeGame);
                        panel.getChildren().get(1).setOnMouseClicked(saveGame);
                        panel.getChildren().get(2).setOnMouseClicked(restartGame);
                        panel.getChildren().get(3).setOnMouseClicked(backtoMainMenu);
                        gamePlayAnchorPane.getChildren().add(panel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (gravity != null) gravity.pause();


                    //currentBall.getGameBall().setOpacity(0);
                    /*for(int i=0;i<gameObstacles.size();i++) {
                        ((NormalCircle)gameObstacles.get(i)).getCircle().setOpacity(0);
                    }*/

                    //PauseMenuGroup.setDisable(false);
                    //PauseMenuGroup.setVisible(true);

                    //for(int i=0;i<images.size();i++) {
                    //  (images.get(i)).setOpacity(0);
                    //}
                    /*
                    for(int i=0;i<colors.size();i++) {
                        (colors.get(i)).getArcGroup().setOpacity(0.3);
                    }*/
                    //CC1.getArcGroup().setOpacity(0);
                    //CC2.getArcGroup().setOpacity(0);
                    /*ResumeGameButton.setOnAction(new EventHandler<ActionEvent>(){

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
                            //CC1.getArcGroup().setOpacity(1);
                            //CC2.getArcGroup().setOpacity(1);
                            for(int i=0;i<images.size();i++) {
                                (images.get(i)).setOpacity(1);
                            }
                            if(gravity!=null)gravity.play();
                        }
                    });*/
                }
            }
        });
    }
}