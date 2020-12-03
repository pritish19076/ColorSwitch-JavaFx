package ColorSwitch;

public class Player {
    private String name;

    public int getCurrentscore() {
        return currentscore;
    }

    public void setCurrentscore(int currentscore) {
        this.currentscore = currentscore;
        highscore=Math.max(highscore,currentscore);
    }

    private int currentscore;
    private int highscore;
    Player(String name)
    {
        this.name=name;
        currentscore=0;
        highscore=0;

    }


}
