package ColorSwitch;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
    private int currentScore;
    private int highScore;
    private int gamesPlayed;
    private ArrayList<Integer> multiGameScore;

    Player(String name)
    {
        this.name=name;
        this.currentScore=0;
        this.highScore=0;
        this.gamesPlayed = 0;
        multiGameScore = new ArrayList<>();
    }


    public void setCurrentScore(int currentscore) {
        this.currentScore = currentscore;
        highScore=Math.max(highScore,currentscore);
    }
    public void setGamesPlayed(int p_gamesPlayed) {
        this.gamesPlayed = p_gamesPlayed;
    }
    public void setMultiGameScore(int score) {
        this.multiGameScore.add(score);
    }
    public void setMultiGameScore(int score,int pos) {
        this.multiGameScore.set(pos,score);
    }
    public String getName() {return this.name;}
    public int getGamesPlayed() {return this.gamesPlayed;}
    public int getCurrentscore() {
        return currentScore;
    }
    public int getGameScore(int index) { return this.multiGameScore.get(index);}
}
