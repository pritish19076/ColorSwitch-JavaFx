package ColorSwitch;

import javafx.scene.Group;

import java.io.*;
import java.util.ArrayList;

public class ReGenerateObstacles {
    private ArrayList<GameObjects> myList;
    private GameObjects deserializedObj = null;
    private Ball gameBall;

    private void deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        myList = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream("C:\\Users\\Keshav Gambhir\\Desktop\\ColorSwitch-JavaFx\\ColorSwitch\\src\\SavedGames\\out.txt"));
            deserializedObj = (GameObjects)in.readObject();
            gameBall = new Ball(deserializedObj.getPositionX(),deserializedObj.getPositionY(),15,1,3,1);
            gameBall.getGameBall().setCenterY(deserializedObj.getPositionY());
            gameBall.getGameBall().setCenterX(deserializedObj.getPositionX());
            while(true) {
                try{
                    GameObjects tmp = (GameObjects) in.readObject();
                    myList.add(tmp);
                }catch (EOFException e) {
                    break;
                }
            }
        }
        finally {
            in.close();
        }
    }

    public ArrayList<GameObjects> regenerateGameObjects(String fileName) throws IOException, ClassNotFoundException {
        deserialize(fileName);
        ArrayList<GameObjects> finalList = new ArrayList<>();
        for(int i=0;i<myList.size();i++) {
            GameObjects obj = myList.get(i);
            System.out.println(obj.getCenterPositionX()+"   "+obj.getCenterPositionY()+"   "+obj.getPositionX()+"   "+obj.getPositionY()+"   "+obj.getObjectType());
            if(obj.getObjectType().equals("Star")) {
                Star s = new Star(obj.getCenterPositionX(),obj.getCenterPositionY());
                s.getImageView().setLayoutX(obj.getPositionX());
                s.getImageView().setLayoutY(obj.getPositionY());
                finalList.add(s);
            }
            else if(obj.getObjectType().equals("ColorChanger")) {
                ColorChanger c = new ColorChanger(obj.getCenterPositionX(),obj.getCenterPositionY(),20.0f,20.0f,1);
                c.getArcGroup().setLayoutX(obj.getPositionX());
                c.getArcGroup().setLayoutY(obj.getPositionY());
                finalList.add(c);
            }
            else if(obj.getObjectType().equals("NormalCircle")) {
                Obstacles obs = new NormalCircle(3000,true,100.0f,100.0f,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((NormalCircle)obs).getGroup().setLayoutY(obj.getPositionY());
                ((NormalCircle)obs).getGroup().setLayoutX(obj.getPositionX());
                finalList.add(obs);
            }
            else if(obj.getObjectType().equals("ConcentricCircles")) {
                Obstacles obs = new ConcentricCircles(3000,obj.getCenterPositionX(),obj.getCenterPositionY());

                ArrayList<Group> groupList = ((ConcentricCircles)obs).getAllGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(0).setLayoutX(obj.getPositionX());
                groupList.get(1).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("Cross")) {
                Obstacles obs = new Cross(3000,true,150,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((Cross)obs).getStickGroup().setLayoutY(obj.getPositionY());
                ((Cross)obs).getStickGroup().setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("Diamond")) {
                Obstacles obs = new Diamond(3000,true,150,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((Diamond)obs).getGroup().setLayoutY(obj.getPositionY());
                ((Diamond)obs).getGroup().setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("DoubleStackCircle")) {
                Obstacles obs = new DoubleStackCircle(3000,obj.getCenterPositionX(),obj.getCenterPositionY());
                ArrayList<Group> groupList = ((DoubleStackCircle)obs).getGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(0).setLayoutX(obj.getPositionX());
                groupList.get(1).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("LongRod")) {
                Obstacles obs = new LongRod(3000,true,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((LongRod)obs).getGroup().setLayoutY(obj.getPositionY());
                ((LongRod)obs).getGroup().setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("Square")) {
                Obstacles obs = new Square(3000,true,150,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((Square)obs).getGroup().setLayoutY(obj.getPositionY());
                ((Square)obs).getGroup().setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("TripleConcentricCircles")) {
                Obstacles obs = new TripleConcentricCircles(3000,4000,obj.getCenterPositionX(),obj.getCenterPositionY());
                ArrayList<Group> groupList = ((TripleConcentricCircles)obs).getAllGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(0).setLayoutX(obj.getPositionX());
                groupList.get(1).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutX(obj.getPositionX());
                groupList.get(2).setLayoutY(obj.getPositionY());
                groupList.get(2).setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("TripleStackCircle")) {
                Obstacles obs = new TripleStackCircle(3000,obj.getCenterPositionX(),obj.getCenterPositionY());
                ArrayList<Group> groupList = ((TripleStackCircle)obs).getAllGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(0).setLayoutX(obj.getPositionX());
                groupList.get(1).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutX(obj.getPositionX());
                groupList.get(2).setLayoutY(obj.getPositionY());
                groupList.get(2).setLayoutX(obj.getPositionX());
                finalList.add(obs);

            }
        }
        return finalList;
    }
}
