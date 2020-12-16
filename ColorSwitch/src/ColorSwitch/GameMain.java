package ColorSwitch;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static ColorSwitch.CommonAnimation.runTranslateTransition;


public class GameMain extends Application implements Initializable, Serializable {
    private boolean onPanel = false;
    public static Stage myStage;
    public static Scene getCurrentScene;
    public static Parent p_root;
    private static Player currentPlayer;
    private static GamePlayController currentSceneController;
    private LoadGames loadableFiles = new LoadGames();


    public static void setupScene(Scene p_scene,Stage p_Stage) {
        System.out.println("initial");
        getCurrentScene= p_scene;
        myStage=p_Stage;
    }
    public static void increaseScore(int scoreIncrease){
        currentPlayer.setCurrentScore(currentPlayer.getCurrentscore()+scoreIncrease);
        currentSceneController.updateScore(currentPlayer.getCurrentscore());
    }

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Group MainMenuGroup;

    @FXML
    private ImageView exitButton;

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
    private Group LeaderboardWindowGroup;

    @FXML
    private ImageView closeLeaderBoardGameButton;

    @FXML
    private Group ExitPopUp;

    @FXML
    private ImageView closePrompt;

    @FXML
    private Group EnterPlayerNameGroup;

    @FXML
    private TextField NameTextField;

    @FXML
    private ChoiceBox<String> ModesChoices;

    @FXML
    private ImageView StartTheGameButton;

    @FXML
    private ImageView closeLeaderBoardGameButton1;

    @FXML
    private ChoiceBox<String> gameSelectionChoice;

    @FXML
    private ImageView loadButton;

    @FXML
    private ImageView loadGameLabel;

    @FXML
    private Label leaderBoardTable;

    @FXML
    private ImageView changeTheme;


    private ArrayList<String> savedPlayerList;
    private ArrayList<String> savedGameList;
    private LeaderBoard finalStandings;


    @FXML
    void changeThemeMenu(MouseEvent event) {

    }

    public String getPureString(String s) {
        int count = 0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i) == '.')
                break;
            count++;
        }
        String tmp = s.substring(0,count);
        return tmp;
    }

    public String getPlayerNameFromString(String s) {
        return s.split("_")[0];
    }
    public int getPlayerGameNumberFromString(String s) {
        return Integer.parseInt(s.split("_")[1]);
    }

    @FXML
    void loadSavedGameFinal(MouseEvent event) throws IOException, ClassNotFoundException {
        String tmp = gameSelectionChoice.getValue();
        String pureStr = getPureString(tmp);

        String playerName = getPlayerNameFromString(pureStr);
        int gameNum = getPlayerGameNumberFromString(pureStr);
        playerName = playerName.concat(".txt");

        String finalFileStr = "C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\SavedGames";
        finalFileStr = finalFileStr.concat("\\");
        finalFileStr = finalFileStr.concat(tmp);
        System.out.println(finalFileStr);
        String finalPlayerStr = "C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\SavedPlayers\\";
        finalPlayerStr = finalPlayerStr.concat(playerName);

        loadFile(finalFileStr,finalPlayerStr,gameNum);
    }

    @FXML
    void startGame(MouseEvent event) {

        if(onPanel){CommonAnimation.fade(EnterPlayerNameGroup, 0,1000).play();onPanel=false;}

        String tmpPlayer = NameTextField.getText();
        tmpPlayer = tmpPlayer.concat(".txt");
        ArrayList<String> loadablePlayerList = loadableFiles.getLoadablePlayersList();
        boolean isPlayerFound = false;

        ReGeneratePlayer tmpRegen = new ReGeneratePlayer();
        String finalFileStr = "C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\SavedPlayers";
        finalFileStr = finalFileStr.concat("\\");
        finalFileStr = finalFileStr.concat(tmpPlayer);

        for(int i=0;i<loadablePlayerList.size();i++) {
            System.out.println("inside printing");
            if(loadablePlayerList.get(i).equals(tmpPlayer)){
                try {
                    currentPlayer = tmpRegen.getPlayer(finalFileStr);
                    currentPlayer.setGamesPlayed(currentPlayer.getGamesPlayed()+1);
                    currentPlayer.setCurrentScore(0);
                    isPlayerFound = true;
                    System.out.println("Player found");
                } catch (IOException e) {
                    System.out.println("Input Output Exception");
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found exception");
                }
            }
        }

        if(!isPlayerFound) {
            currentPlayer=new Player(NameTextField.getText());
            currentPlayer.setGamesPlayed(1);
        }

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
            //GamePlayController ctrl = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "GamePlay.fxml"));

                p_root = (Parent) loader.load();
                currentSceneController = loader.getController();
                currentSceneController.letsgetitstarted();
//                ctrl.init(table.getSelectionModel().getSelectedItem());

//                p_root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Scene gameplayscene=new Scene(p_root,525,810);
            //GamePlayController.setupScense(gameplayscene);

            myStage.setScene(gameplayscene);
            getCurrentScene=gameplayscene;
            (currentSceneController).setupScene(getCurrentScene,myStage,currentPlayer);

        }));


       /*GamePlayController GamePlayControl=new GamePlayController();
        Timeline setupGame=new Timeline(new KeyFrame(Duration.millis(1),e -> {
            GamePlayControl.setupGame();

        }));*/
        //CommonAnimation.delay should be 1600 setting to 1 for faster testing
        new SequentialTransition(CommonAnimation.delay(1600),tim2,swtichscenez).play();




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
        runTranslateTransition(changeTheme,0,out* 200,1500).play();
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
        primaryStage.setScene(myScene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }



    @FXML
    void viewLeaderBoard(MouseEvent event) throws IOException, ClassNotFoundException {
        System.out.println("Load");
        if (!onPanel) {
            CommonAnimation.fade(LeaderboardWindowGroup, 1).play();
            CommonAnimation.loadPanel(false, 1000,-983,MainMenuGroup,LeaderboardWindowGroup).play();
            onPanel = true;
        }

        leaderBoardTable.setText(finalStandings.getPlayersScore());
        leaderBoardTable.setFont(Font.font(30));
    }


    @FXML
    void closeGame(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }



    @FXML
    void exitTheGame(MouseEvent event) {
        if (!onPanel) {
            CommonAnimation.fade(ExitPopUp, 1).play();
            CommonAnimation.loadPanel(false, 1000,-1100,MainMenuGroup,ExitPopUp).play();
            onPanel = true;
        }
    }

    private void loadFile(String file,String playerFile,int gameNumber) throws IOException, ClassNotFoundException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePlay.fxml"));
            p_root = (Parent) loader.load();
            currentSceneController = loader.getController();
            currentSceneController.fromload=true;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Scene gameplayscene=new Scene(p_root,525,810);
        ReGeneratePlayer regenPlayer = new ReGeneratePlayer();
        myStage.setScene(gameplayscene);
        getCurrentScene=gameplayscene;
        currentPlayer=regenPlayer.getPlayer(playerFile);
        currentPlayer.setCurrentScore(currentPlayer.getGameScore(gameNumber-1));
        (currentSceneController).setupScene(getCurrentScene,myStage,currentPlayer);
        currentSceneController.loadtheGame(file);
    }



    @FXML
    void closePanel(MouseEvent event) throws IOException, ClassNotFoundException {
        Node Panel=((Node)event.getTarget()).getParent();
        double distance=0;
        if(Panel==LoadGameWindowGroup)distance=683;
        else if(Panel==ExitPopUp)distance=-1100;
        else distance=-983;
        if (onPanel) {
            new SequentialTransition(CommonAnimation.fade(Panel, 0), CommonAnimation.loadPanel(true, 1,distance,MainMenuGroup,Panel)).play();
            onPanel = false;
        }
    }

    @FXML
    void loadGame(MouseEvent event) {
        System.out.println("Load");
        if (!onPanel) {
            CommonAnimation.fade(LoadGameWindowGroup, 1).play();
            CommonAnimation.loadPanel(false, 1000,683,MainMenuGroup,LoadGameWindowGroup).play();
            onPanel = true;
        }
    }

    @FXML
    void newGame(MouseEvent event){
        System.out.println("start");
        if (!onPanel) {
            CommonAnimation.fade(EnterPlayerNameGroup, 1).play();
            CommonAnimation.loadPanel(false, 1000,-983,MainMenuGroup,EnterPlayerNameGroup).play();
            onPanel = true;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline intro = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            introTransition(-1);
        }));
        finalStandings = new LeaderBoard();
        savedGameList = loadableFiles.getLoadableGamesList();
        savedPlayerList = loadableFiles.getLoadablePlayersList();
        ModesChoices.setItems(FXCollections.observableArrayList("Normal Color Switch","Flappy Switch","Bee Mode"));
        ModesChoices.setValue("Normal Color Switch");
//        gameSelectionChoice = new ChoiceBox<>();
        gameSelectionChoice.setItems(FXCollections.observableList(savedGameList));
        new SequentialTransition(CommonAnimation.delay(1000), intro).play();
    }
}
