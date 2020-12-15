package ColorSwitch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReGeneratePlayer {
    private Player generatedPlayer;

    public Player getPlayer(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            generatedPlayer = (Player)in.readObject();
        }finally {
            in.close();
        }
        return generatedPlayer;
    }
}
