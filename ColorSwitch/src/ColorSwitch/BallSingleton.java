package ColorSwitch;

public class BallSingleton {
    private Ball newGameBall;
    private static BallSingleton ballGameInterface;

    private BallSingleton() {
        newGameBall = new Ball(263, 707, 15, 4, 3, 1);
        newGameBall.getGameBall().setCenterX(263);
        newGameBall.getGameBall().setCenterY(707);
    }

    public Ball getBall() {return newGameBall;}

    public synchronized static BallSingleton getInstance() {
        if(ballGameInterface == null) {
            ballGameInterface = new BallSingleton();
        }
        return ballGameInterface;
    }
}
