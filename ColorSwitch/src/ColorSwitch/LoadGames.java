package ColorSwitch;

import java.io.File;
import java.util.ArrayList;

public class LoadGames {
    private ArrayList<String> loadableGames;
    private ArrayList<String> loadablePlayers;
    public LoadGames() {
        loadableGames = new ArrayList<>();
        loadablePlayers = new ArrayList<>();
    }
    public ArrayList<String> getLoadableGamesList() {
        File [] directory = new File("ColorSwitch\\src\\SavedGames").listFiles();
        for(File file : directory) {
            loadableGames.add(file.getName());
        }
        return loadableGames;
    }
    public ArrayList<String> getLoadablePlayersList() {
        File [] directory = new File("ColorSwitch\\src\\SavedPlayers").listFiles();
        for(File file : directory) {
            loadablePlayers.add(file.getName());
        }
        return loadablePlayers;
    }
}
