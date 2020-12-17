package ColorSwitch;



import java.util.Comparator;

public class PlayerLeaderBoard implements Comparable<PlayerLeaderBoard> {
    private String playerName;
    private int maxScore;
    private int gameNumber;
    private int restartNumber;

    public PlayerLeaderBoard(String name,int score,int gNumber,int rNumber) {
        this.playerName = name;
        this.maxScore = score;
        this.gameNumber = gNumber;
        this.restartNumber = rNumber;
    }

    public String getPlayerName() { return playerName; }
    public int getMaxScore() { return maxScore; }
    public int getGameNumber() { return gameNumber; }
    public int getRestartNumber() { return restartNumber;}

    @Override
    public int compareTo(PlayerLeaderBoard o) {
        if(maxScore > o.maxScore){
            return -1;
        }
        if (o.maxScore < maxScore) {
            return 1;
        }
        return 0;
    }

}
