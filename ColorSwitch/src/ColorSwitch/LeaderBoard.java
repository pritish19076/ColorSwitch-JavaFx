package ColorSwitch;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard {
    private ArrayList<PlayerLeaderBoard> playerList;

    public LeaderBoard() {
        playerList = new ArrayList<>();
    }

    public ArrayList<String> getStringParts(String s) {
        String [] arr = s.split("_",4);
        ArrayList<String> finalStr = new ArrayList<>();
        for(int i = 0;i<arr.length;i++) {
            finalStr.add(arr[i]);
        }
        return finalStr;
    }

    public Player getPlayerDetails(String fileName) throws IOException, ClassNotFoundException {
        Player generatedPlayer = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            try {
                generatedPlayer = (Player) in.readObject();
            }catch (ClassCastException e) {
                System.out.println("Invalid Class Cast Exception");
            }

        }finally {
            in.close();
        }
        return generatedPlayer;
    }

    public String getPlayersScore() throws IOException, ClassNotFoundException {
        File folder = new File("C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\LeaderBoard");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> filesArr = new ArrayList<>();
        for(int i=0;i<listOfFiles.length;i++) {
            String tmp = "C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\LeaderBoard\\";
            tmp = tmp.concat(listOfFiles[i].getName());
            filesArr.add(tmp);
        }
        for (String s : filesArr) {
            Player tmpPlayer = getPlayerDetails(s);
            PlayerLeaderBoard tmp = new PlayerLeaderBoard(tmpPlayer.getName(),tmpPlayer.getCurrentscore(),tmpPlayer.getGamesPlayed(),tmpPlayer.getRestartCount());
            playerList.add(tmp);
        }
        Collections.sort(playerList);
        String finalStr = "";

        for (PlayerLeaderBoard playerLeaderBoard : playerList) {
            String tmpStr = playerLeaderBoard.getPlayerName() + "                       " + Integer.toString(playerLeaderBoard.getMaxScore());
            finalStr = finalStr.concat(tmpStr);
            finalStr = finalStr.concat(Character.toString('\n'));
        }

        System.out.println(finalStr);
        return finalStr;
    }
}
