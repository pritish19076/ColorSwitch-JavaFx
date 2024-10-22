package ColorSwitch;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CommonAnimation {
    private static double speedX = 0.04;
    private static double speedY = 0.04;

    public static FadeTransition fade(Node n, double fadeval) {
        //Fade Duration Must be 1500
        //Setting Fades to 1 for Fast Testing
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(1500));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        if(fadeval==0)n.setDisable(true);
        else if(fadeval==1)n.setDisable(false);
        return fadeload;
    }
    public static FadeTransition fade(Node n, double fadeval,double time) {
        //Fade Duration Must be 1500
        //Setting Fades to 1 for Fast Testing
        FadeTransition fadeload = new FadeTransition();
        fadeload.setDuration(Duration.millis(time));
        fadeload.setToValue(fadeval);
        fadeload.setNode(n);
        if(fadeval==0)n.setDisable(true);
        else if(fadeval==1)n.setDisable(false);
        return fadeload;
    }
    public static TranslateTransition runTranslateTransition(Node n, double x, double y, double duration) {
        TranslateTransition load = new TranslateTransition();
        load.setByY(y);
        load.setByX(x);
        load.setNode(n);
        load.setDuration(Duration.millis(duration));
        return load;
    }
    public static Timeline delay(double time)
    {
        return new Timeline(new KeyFrame(Duration.millis(time), e -> { }));
    }

    public static TranslateTransition loadPanel(boolean reverse, double duration,double distance,Node MainNode, Node Panel) {
        TranslateTransition load = new TranslateTransition();
        if (!reverse) {load.setByY(distance);CommonAnimation.fade(MainNode,0.4).play();}
        else {load.setByY(-distance);CommonAnimation.fade(MainNode,1).play();}
        load.setNode(Panel);
        load.setDuration(Duration.millis(duration));
        return load;
    }
    public static void DeathAnimation(float x, float y, AnchorPane tmpAnchorPane) {
        Timeline animation = null;
        ArrayList<Circle> deathBalls = new ArrayList<>();
        float delta = 0;
        Group deathGroup = new Group();

        for(int i=0;i<20;i++) {
            Circle tmp = new Circle();
            Circle tmp2 = new Circle();

            tmp.setCenterX(x+delta);
            tmp.setCenterY(y+delta);

            tmp2.setCenterX(x-delta);
            tmp2.setCenterY(y+delta);

            tmp.setRadius(5);
            tmp2.setRadius(5);

            tmp.setVisible(true);
            tmp2.setVisible(true);

            tmp.setOpacity(1);
            tmp2.setOpacity(1);

            if(i%4 == 0){
                tmp.setFill(GameColor.getColor(1));
                tmp2.setFill(GameColor.getColor(1));
            }

            else if(i%4 == 1) {
                tmp.setFill(GameColor.getColor(2));
                tmp2.setFill(GameColor.getColor(2));
            }

            else if(i%4 == 2){
                tmp.setFill(GameColor.getColor(3));
                tmp2.setFill(GameColor.getColor(3));
            }

            else {
                tmp.setFill(GameColor.getColor(4));
                tmp2.setFill(GameColor.getColor(4));
            }

            deathBalls.add(tmp);
            deathBalls.add(tmp2);
            deathGroup.getChildren().add(tmp);
            deathGroup.getChildren().add(tmp2);
            delta+=10;
        }
        tmpAnchorPane.getChildren().add(deathGroup);
//        for(int i=0;i<40;i++) {
//            tmpAnchorPane.getChildren().add(deathBalls.get(i));
//        }
        Random rand = new Random();
        if(animation == null) {
            animation = new Timeline();
            animation.setCycleCount(100);

            KeyFrame dAnimation = new KeyFrame(Duration.millis(75),e->{
                for(int i=0;i<deathBalls.size();i++) {
                    int tmp = rand.nextInt(4);
                    if(tmp%4 == 0){
                        deathBalls.get(i).setCenterX(deathBalls.get(i).getCenterX()+10);
                        deathBalls.get(i).setCenterY(deathBalls.get(i).getCenterY()+10);
                    }
                    else if(tmp%4 == 1) {
                        deathBalls.get(i).setCenterX(deathBalls.get(i).getCenterX()-10);
                        deathBalls.get(i).setCenterY(deathBalls.get(i).getCenterY()+10);
                    }
                    else if(tmp%4 == 2) {
                        deathBalls.get(i).setCenterX(deathBalls.get(i).getCenterX()-10);
                        deathBalls.get(i).setCenterY(deathBalls.get(i).getCenterY()-10);
                    }
                    else {
                        deathBalls.get(i).setCenterX(deathBalls.get(i).getCenterX()+10);
                        deathBalls.get(i).setCenterY(deathBalls.get(i).getCenterY()-10);
                    }
                }

            });
            animation.getKeyFrames().add(dAnimation);
            animation.play();
        }
        else {
            animation.play();
        }

    }

    public static void starAnimation(double x, double y, Star s,double labelPosX,double labelPosY) {
        TranslateTransition starTranslation = new TranslateTransition();
//        if(y>0) {

//        }
//        else {
//            starTranslation.setByX(labelPosX-x);
//            starTranslation.setByY(y-labelPosX);
//        }

        System.out.println();
        System.out.println((labelPosX-x)+"   "+(labelPosY-y));
        starTranslation.setFromX(x);
        starTranslation.setFromY(y);
        starTranslation.setByX(-200);
        starTranslation.setByY(-600);
//        starTranslation.setFromX(x);

//        starTranslation.setToX(-200);
//        starTranslation.setToY(-600);
        starTranslation.setCycleCount(1);
        starTranslation.setDuration(Duration.millis(1000));
        starTranslation.setNode(s.imageView);
        starTranslation.play();
    }


    public static void starAnimation2(Star s,AnchorPane tmpAnchorPane) {
        Label label = new Label("+1");
        label.setTextFill(Color.WHITE);
        Font f = Font.font("SANS MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
        label.setFont(f);
        label.setLayoutX(50);
        label.setLayoutY(10);
        tmpAnchorPane.getChildren().add(label);


        ScaleTransition st = new ScaleTransition(Duration.millis(100),s.imageView);
        ScaleTransition st2 = new ScaleTransition(Duration.millis(100),label);
        st.setByX(0.75f);
        st.setByY(0.75f);
        st2.setByX(0.75f);
        st2.setByY(0.75f);
        st.play();
        Timeline st3=new Timeline(new KeyFrame(Duration.millis(1),e->{
            tmpAnchorPane.getChildren().remove(label);
        }));
        new SequentialTransition(delay(100),st2,delay(100),st3).play();

    }

}

