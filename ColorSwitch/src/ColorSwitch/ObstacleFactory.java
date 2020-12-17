package ColorSwitch;

public class ObstacleFactory {
    public Obstacles createObstacle(int obsno, float x, float y) {
        Obstacles obs1 = null;
        if (obsno == 1) {
            obs1 = new NormalCircle(5000, true, 100.0f, 100.0f, x, y);
            obs1.setObjectType("NormalCircle");

        }
        if (obsno == 2) {
            obs1 = new Square(3000, true, 100, x, y);
            obs1.setObjectType("Square");

        }

        if (obsno == 3) {
            obs1 = new Cross(3000, true, 150, x, y);
            obs1.setObjectType("Cross");
        }

        if (obsno == 4) {
            obs1 = new Diamond(3000, true, 100, x, y);
            obs1.setObjectType("Diamond");


        }

        if (obsno == 5) {
            obs1 = new LongRod(10, true, x, y);
            obs1.setObjectType("LongRod");

        }

        if (obsno == 6) {
            obs1 = new DoubleStackCircle(3000, x, y);

            obs1.setObjectType("DoubleStackCircle");

        }

        if (obsno == 7) {
            obs1 = new ConcentricCircles(3000, x, y);

            obs1.setObjectType("ConcentricCircles");

        }

        if (obsno == 8) {
            obs1 = new TripleConcentricCircles(4000, 3000, x, y);


        }

        if (obsno == 9) {
            obs1 = new TripleStackCircle(3000, x, y);
        }
        return obs1;


    }
}
