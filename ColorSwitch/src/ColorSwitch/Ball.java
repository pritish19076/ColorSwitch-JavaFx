package ColorSwitch;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import static ColorSwitch.CommonAnimation.runTranslateTransition;
public class Ball extends GameObjects  {
    private float gravityVal;
    private int ballColor;
    private Circle gameBall;
    public Ball(float x,float y,float radius, int color, float p_gravity,float opacity) {
        super(x,y);

        GameColor tmpBallColor = new GameColor();
        gameBall = new Circle();
        gameBall.setCenterX(x);
        gameBall.setCenterY(y);
        gameBall.setRadius(radius);
        gameBall.setFill(tmpBallColor.getColor(color));
        gameBall.setOpacity(opacity);

    }

    @Override
    public void onCollide(GameObjects collidingBall) {

    }

    @Override
    public void display(AnchorPane gamePane) {
        gamePane.getChildren().add(gameBall);

    }
    public void moveTheBall(ArrayList<GameObjects> onScreenObjects,double yval,double duration)
    {
        runTranslateTransition(gameBall,0,yval,duration).play();

    }
    public Circle getGameBall() {return gameBall;}
}
