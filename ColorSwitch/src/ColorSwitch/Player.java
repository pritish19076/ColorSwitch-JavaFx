package ColorSwitch;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int currentScore;
    private int highScore;

    public int getCurrentscore() {
        return currentScore;
    }

    public void setCurrentScore(int currentscore) {
        this.currentScore = currentscore;
        highScore=Math.max(highScore,currentscore);
    }


    Player(String name)
    {
        this.name=name;
        currentScore=0;
        highScore=0;

    }


}
