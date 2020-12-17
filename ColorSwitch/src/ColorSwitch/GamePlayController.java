package ColorSwitch;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
import java.util.Scanner;
//C:\Users\Keshav Gambhir\Desktop\ColorSwitch-JavaFx\ColorSwitch\src\LeaderBoard

public class GamePlayController implements Initializable {

    public boolean shake = false;
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
    private int chosenTheme=-1;
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
        ObstacleFactory f=new ObstacleFactory();
        Obstacles obs1=null;
        if(c==6||c==9)obs1=f.createObstacle(c,263,prevobstacley-(2*distance));
        else obs1=f.createObstacle(c,263,prevobstacley-distance);
        obs1.display(gamePlayAnchorPane);
        gameObjects.add(obs1);
        gameObstacles.add(obs1);
        if(c<=5&&shake)obs1.shake();
        if(c==1){

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



            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            CC1.display(gamePlayAnchorPane);
            gameObjects.add(CC1);


            prevobstacley-=distance;
        }

        if(c==4){




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




            ColorChanger CC1 = new ColorChanger(obs1.getPositionX(),obs1.getPositionY()-180,20f,20f,1);
            CC1.setObjectType("ColorChanger");
            gameObjects.add(CC1);
            CC1.display(gamePlayAnchorPane);

            prevobstacley-=distance;
        }

        if(c==6){

            customObstacleList.add(((DoubleStackCircle)obs1).getGroupList());
            gameObjects.add(obs1);


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

            gameObjects.add(obs1);
            customObstacleList.add(((ConcentricCircles)obs1).getAllGroupList());


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

            customObstacleList.add(((TripleConcentricCircles)obs1).getAllGroupList());


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


            customObstacleList.add(((TripleStackCircle)obs1).getAllGroupList());


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

    public String getPlayerFileNameWithRestartValue(String rootDirectory) {
        String playerName = null;
        try{
            playerName = currentPlayer.getName();

        }
        catch (NullPointerException e) {
            System.out.println("Player not initialized");
        }
        playerName = playerName.concat("_");
        playerName = playerName.concat(Integer.toString(currentPlayer.getGamesPlayed()));
        playerName = playerName.concat("_");
        playerName = playerName.concat(Integer.toString(currentPlayer.getRestartCount()));

        rootDirectory = rootDirectory.concat("\\");
        rootDirectory = rootDirectory.concat(playerName);
        rootDirectory = rootDirectory.concat(".txt");

        return rootDirectory;
    }

    public void letsgetitstarted() {
        currentBall = new Ball(263, 707, 15, 4, 3, 1);
        currentBall.getGameBall().setCenterX(263);
        currentBall.getGameBall().setCenterY(707);
        currentBall.display(gamePlayAnchorPane);
        gameObstacles = new ArrayList<>();
        gameObjects = new ArrayList<>();
        gameObjects.add(currentBall);
        addObstacle(1);
        addObstacle(1);
        speedY = 0;
    }

    public void revivePlayer() throws InsufficientStarsException {
        if (gravity != null) gravity.play();
        if(currentPlayer.getCurrentscore() < 3) {
            throw new InsufficientStarsException("Stars are not enough");
        }
        if(currentPlayer.getCurrentscore()>=3){
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customObstacleList=new ArrayList<>();
        //String image = GamePlayController.class.getClassLoader().getResource("assets/back1.jpg").toExternalForm();
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
                    String playerFile = getPlayerFileName("C:\\Users\\Pritish\\IdeaProjects\\ColorSwitch-JavaFx6\\ColorSwitch\\src\\SavedPlayers");
                    String fileName = getFileName("C:\\Users\\Pritish\\IdeaProjects\\ColorSwitch-JavaFx6\\ColorSwitch\\src\\SavedGames");
                    String leaderBoardFile = getPlayerFileNameWithRestartValue("C:\\Users\\Pritish\\IdeaProjects\\ColorSwitch-JavaFx6\\ColorSwitch\\src\\LeaderBoard");
                    System.out.println(leaderBoardFile);
                    SerializePlayer(leaderBoardFile);
                    currentPlayer.setMultiGameScore(currentPlayer.getCurrentscore());
                    SerializePlayer(playerFile);
                    Serialize(fileName);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        reviveGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    revivePlayer();
                } catch (InsufficientStarsException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You don't have sufficient stars to revive into the game!!!");
                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    alert.getDialogPane().getButtonTypes().add(type);
                    alert.showAndWait();
                    System.out.println(e.getMessage());
                }
            }
        };
        restartGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String fileName = getPlayerFileNameWithRestartValue("C:\\Users\\Pritish\\IdeaProjects\\ColorSwitch-JavaFx6\\ColorSwitch\\src\\LeaderBoard");
                try {
                    SerializePlayer(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GamePlayController temp = null;
                Parent p_root = null;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
                    p_root = (Parent) loader.load();
                    temp = loader.getController();
                    new ThemeDecorator((AnchorPane) p_root,chosenTheme);
                    GameMain.currentSceneController=temp;
                    temp.gamePlayAnchorPane=(AnchorPane) p_root;
                    temp.letsgetitstarted();
//                ctrl.init(table.getSelectionModel().getSelectedItem());

//                p_root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Scene gameplayscene = new Scene(p_root, 525, 810);
                BallSingleton.getInstance();
                myStage.setScene(gameplayscene);

                temp.gamePlayAnchorPane = (AnchorPane) p_root;
                currentPlayer.setRestartCount(currentPlayer.getRestartCount()+1);
//                currentPlayer.se
                temp.setupScene(gameplayscene, myStage,currentPlayer,chosenTheme);
                if(gravity!=null)gravity.play();
            }
        };
        backtoMainMenu = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String fileName = getPlayerFileNameWithRestartValue("C:\\Users\\Pritish\\IdeaProjects\\ColorSwitch-JavaFx6\\ColorSwitch\\src\\LeaderBoard");
                try {
                    SerializePlayer(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gamePlayAnchorPane.getChildren().remove(panel);
                gamePlayAnchorPane.getChildren().remove(Score);
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("BlackPane.fxml"));
                try {
                    panel = (AnchorPane) loader1.load();
                    gamePlayAnchorPane.getChildren().add(panel);
                } catch (IOException error) {
                    error.printStackTrace();
                }
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
    }

    public boolean detectCollision() {
        boolean tmp = false;
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObjects o = gameObjects.get(i);
            tmp = o.onCollide((GameObjects) currentBall);
            if (tmp && (o instanceof Star || o instanceof ColorChanger)) {
                if (o instanceof Star){
                    totalstarscollected++;
                    Timeline death=new Timeline(new KeyFrame(Duration.millis(1),err-> {
                        CommonAnimation.starAnimation2(((Star)o),gamePlayAnchorPane);
                    }));
                    Timeline remove = new Timeline(new KeyFrame(Duration.millis(1),err->{
                        gamePlayAnchorPane.getChildren().remove(((Star)o).imageView);
                    }));
                    new SequentialTransition(death,CommonAnimation.delay(200),remove,CommonAnimation.delay(1000)).play();
                }
                if(totalstarscollected%3==0){
                    for(int j=0;j<gameObjects.size();j++){
                        GameObjects g=gameObjects.get(j);
                        if(g instanceof Obstacles)
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
                if (currentPlayer.getCurrentscore() >= 5 && !shake) {
                    shake = true;
                    for (GameObjects b : gameObjects) {
                        if (b instanceof Obstacles) ((Obstacles) b).shake();
                    }
                }
                boolean test = detectCollision();
               //test=false;//OnCollide Disabled
                if (test) {
                    //gravity.pause();
                    Media sound = new Media(new File("ColorSwitch\\src\\sounds\\Die.mp3").toURI().toString());
                    MediaPlayer diesound = new MediaPlayer(sound);
                    diesound.play();
                    if (gravity != null) gravity.pause();
                    currentBall.getGameBall().setOpacity(0);
                    Timeline death=new Timeline(new KeyFrame(Duration.millis(1),err-> {
                        CommonAnimation.DeathAnimation((int)currentBall.getGameBall().getCenterX(),(int)currentBall.getGameBall().getCenterY(),gamePlayAnchorPane);
                    }));

                    Timeline revivemenu=new Timeline(new KeyFrame(Duration.millis(1),err-> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviveMenu.fxml"));
                        try {
                            panel = (AnchorPane) loader.load();
                            panel.getChildren().get(0).setOnMouseClicked(reviveGame);
                            panel.getChildren().get(1).setOnMouseClicked(restartGame);
                            panel.getChildren().get(2).setOnMouseClicked(backtoMainMenu);
                            gamePlayAnchorPane.getChildren().add(panel);
                        } catch (IOException error) {
                            error.printStackTrace();
                        }
                    }));

                    new SequentialTransition(death,CommonAnimation.delay(2000),revivemenu).play();






                     // death animation
                    /*for (GameObjects gameObj : gameObjects) {
                        if(gameObj instanceof Obstacles)((Obstacles)(gameObj)).stopRotation();
                    }*/



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
        currentBall.setY(currentBall.getGameBall().getCenterY()+yDelta);
    }


    @FXML
    void backtomain(MouseEvent event) {


    }

    public void update() {
        move(speedY);
        accelerate(0.04); // gravity accelerates the object downwards each tick Range - 0.03 to 0.04
    }

    public void loadtheGame(String filename) throws IOException, ClassNotFoundException {
        Score.setText(Integer.toString(currentPlayer.getCurrentscore()));
        ReGenerateObstacles regenObs = new ReGenerateObstacles();
        System.out.println(filename);
        gameObjects = regenObs.regenerateGameObjects(filename);
        for(GameObjects g:gameObjects){
            if(g instanceof Ball)currentBall=(Ball)g;
                g.display(gamePlayAnchorPane);
        }
        gameObstacles=new ArrayList<>();
        prevobstacley = (int) gameObjects.get((gameObjects.size()-1)).getPositionY();

    }
    public void setupScene(Scene p_scene, Stage myStage, Player p_player,int theme) {
        gamePlayScene = p_scene;
        chosenTheme=theme;
        this.myStage = myStage;
        currentPlayer = p_player;
        gamePlayScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    speedY -= 2; //Range From 0.05 to 0.08
                    Media sound = new Media(new File("ColorSwitch\\src\\sounds\\tap.mp3").toURI().toString());
                    MediaPlayer tapsound = new MediaPlayer(sound);
                    tapsound.play();
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