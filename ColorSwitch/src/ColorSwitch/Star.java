package ColorSwitch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Star extends GameObjects {
    private transient String imagepath;
    transient ImageView imageView;
    public ImageView getImageView() {
        return imageView;
    }

    public void setPositonY(double val){
        double temp=imageView.getLayoutY()+val;
        setPosition(getPositionX(), (float)temp);
        imageView.setLayoutY(temp);

    }

    Star(float x, float y) {
        super(x, y);
        imagepath = "assets/star.png";
        imageView = new ImageView(new Image(imagepath, true));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(35);
        imageView.setPreserveRatio(true);
    }

    @Override
    public boolean onCollide(GameObjects collidingBall) {
        AnchorPane anch = (AnchorPane) imageView.getParent();
        if (anch != null) {
            if (((Ball) collidingBall).getGameBall().getBoundsInParent().intersects(imageView.getBoundsInParent())) {
                System.out.println("star hit");
                GameMain.increaseScore(1);
                anch.getChildren().remove(imageView);
                return true;
            }
        }
        return false;

    }

    @Override
    public void display(AnchorPane gamePane) {
        gamePane.getChildren().add(imageView);
    }

}
