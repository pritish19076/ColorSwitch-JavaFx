package ColorSwitch;

import javafx.scene.layout.AnchorPane;

public class ThemeDecorator extends AnchorPane{
    AnchorPane a;
    public ThemeDecorator(AnchorPane p_a,int finalTheme){
        this.a=p_a;
        if(finalTheme==1){
            String image = GameMain.class.getClassLoader().getResource("assets/Back1.png").toExternalForm();
            a.setStyle("-fx-background-image: url('" + image + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-repeat: stretch;");

        }
        else if(finalTheme==2){
            String image = GameMain.class.getClassLoader().getResource("assets/Back2.png").toExternalForm();
            a.setStyle("-fx-background-image: url('" + image + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-repeat: stretch;");

        }
        else if(finalTheme==3){
            String image = GameMain.class.getClassLoader().getResource("assets/Back3.png").toExternalForm();
            a.setStyle("-fx-background-image: url('" + image + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-repeat: stretch;");


        }
        else if(finalTheme==4){
            String image = GameMain.class.getClassLoader().getResource("assets/Back4.png").toExternalForm();
            a.setStyle("-fx-background-image: url('" + image + "'); " +
                    "-fx-background-position: center center; " +
                    "-fx-background-repeat: stretch;");


        }


    }

}
