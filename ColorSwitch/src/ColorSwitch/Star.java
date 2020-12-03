package ColorSwitch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class Star extends GameObjects{
    String imagepath;
    ImageView imageView;
    Star(float x,float y){
        super(x,y);
        imagepath="assets/star.png";
        imageView = new ImageView(new Image(imagepath,true));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(35);
        imageView.setPreserveRatio(true);
    }

    @Override
    public boolean onCollide(GameObjects collidingBall) {
        if(((Ball)collidingBall).getGameBall().getBoundsInParent().intersects(imageView.getBoundsInParent())){
            GameMain.increaseScore(1);
            return true;
        }
        return false;

    }

    @Override
    public void display(AnchorPane gamePane) {
        gamePane.getChildren().add(imageView);
    }

}
