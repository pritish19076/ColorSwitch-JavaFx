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
            in = new ObjectInputStream(new FileInputStream(fileName));
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
                }catch (ClassCastException e) {
                    System.out.println("Invalid Class Cast Exception");
                }
            }
        }
        finally {
            in.close();
        }
    }

    public ArrayList<GameObjects> regenerateGameObjects(String fileName) throws IOException, ClassNotFoundException {
        deserialize(fileName);
        System.out.println(fileName);
        ArrayList<GameObjects> finalList = new ArrayList<>();
        for(int i=0;i<myList.size();i++) {
            GameObjects obj = myList.get(i);
            System.out.println(obj.getCenterPositionX()+"   "+obj.getCenterPositionY()+"   "+obj.getPositionX()+"   "+obj.getPositionY()+"   "+obj.getObjectType());
            if(obj.getObjectType().equals("Star")) {
                Star s = new Star(obj.getCenterPositionX(),obj.getCenterPositionY());
                s.getImageView().setLayoutY(obj.getPositionY());
                finalList.add(s);
            }
            else if(obj.getObjectType().equals("ColorChanger")) {
                ColorChanger c = new ColorChanger(obj.getCenterPositionX(),obj.getCenterPositionY(),20.0f,20.0f,1);
                c.getArcGroup().setLayoutY(obj.getPositionY());
                finalList.add(c);
            }
            else if(obj.getObjectType().equals("NormalCircle")) {
                Obstacles obs = new NormalCircle(3000,true,100.0f,100.0f,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((NormalCircle)obs).getGroup().setLayoutY(obj.getPositionY());
                finalList.add(obs);
            }
            else if(obj.getObjectType().equals("ConcentricCircles")) {
                Obstacles obs = new ConcentricCircles(3000,obj.getCenterPositionX(),obj.getCenterPositionY());

                ArrayList<Group> groupList = ((ConcentricCircles)obs).getAllGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("Cross")) {
                Obstacles obs = new Cross(3000,true,150,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((Cross)obs).getStickGroup().setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("Diamond")) {
                Obstacles obs = new Diamond(3000,true,150,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((Diamond)obs).getGroup().setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("DoubleStackCircle")) {
                Obstacles obs = new DoubleStackCircle(3000,obj.getCenterPositionX(),obj.getCenterPositionY());
                ArrayList<Group> groupList = ((DoubleStackCircle)obs).getGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("LongRod")) {
                Obstacles obs = new LongRod(3000,true,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((LongRod)obs).getGroup().setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("Square")) {
                Obstacles obs = new Square(3000,true,150,obj.getCenterPositionX(),obj.getCenterPositionY());
                ((Square)obs).getGroup().setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("TripleConcentricCircles")) {
                Obstacles obs = new TripleConcentricCircles(3000,4000,obj.getCenterPositionX(),obj.getCenterPositionY());
                ArrayList<Group> groupList = ((TripleConcentricCircles)obs).getAllGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutY(obj.getPositionY());
                groupList.get(2).setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if(obj.getObjectType().equals("TripleStackCircle")) {
                Obstacles obs = new TripleStackCircle(3000,obj.getCenterPositionX(),obj.getCenterPositionY());
                ArrayList<Group> groupList = ((TripleStackCircle)obs).getAllGroupList();
                groupList.get(0).setLayoutY(obj.getPositionY());
                groupList.get(1).setLayoutY(obj.getPositionY());
                groupList.get(2).setLayoutY(obj.getPositionY());
                finalList.add(obs);

            }
            else if (obj.getObjectType().equals("Ball"))
            {
                Ball currentBall = new Ball(obj.getPositionX(), obj.getPositionY(), 15, 4, 3, 1);
                finalList.add(currentBall);
            }
        }
        return finalList;
    }
}
