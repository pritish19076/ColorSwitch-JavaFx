package ColorSwitch;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int currentScore;
    private int highScore;
    private int gamesPlayed;

    Player(String name)
    {
        this.name=name;
        this.currentScore=0;
        this.highScore=0;
        this.gamesPlayed = 0;
    }


    public void setCurrentScore(int currentscore) {
        this.currentScore = currentscore;
        highScore=Math.max(highScore,currentscore);
    }
    public void setGamesPlayed(int p_gamesPlayed) {this.gamesPlayed = p_gamesPlayed;}
    public String getName() {return this.name;}
    public int getGamesPlayed() {return this.gamesPlayed;}
    public int getCurrentscore() {
        return currentScore;
    }
}
