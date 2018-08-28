import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class BallScreenSaver extends AnimationFrame {
    int framewidth = 800;
    int frameheight = 800;
    int num = (int)Math.random()*30 +1;
    String appname;
    List<Ball> ballList;

    private CollisionLogger collisionLogger;
    private static final int Collision_Bucket_Width = 20;
    private int saveCounter = 0;


    public static void main(String[] args) {
        BallScreenSaver news = new BallScreenSaver(800, 800, "Testing", 30);
    }

    /**
     * Creates list of ball objects that are at random positions and random speeds. All
     * except one ball is green, and one is red.
     */
    public BallScreenSaver(){
        super();
        ballList = new ArrayList();
        setFPS(20);
        while (num>1) {
            int x = (int)(Math.random()*framewidth + 1);
            int y = (int)(Math.random()*frameheight +1);
            Ball newballs = new Ball(x,y, 10, Color.GREEN);
            ballList.add(newballs);
            num--;
        }
        int x = (int)(Math.random()*framewidth + 1);
        int y = (int)(Math.random()*frameheight +1);
        Ball newball = new Ball(x, y, 10, Color.RED);
        ballList.add(newball);
        start();
        collisionLogger = new CollisionLogger(framewidth, frameheight, Collision_Bucket_Width);
    }

    /**
     * Creates list of ball objects that are at random positions and random speeds. All
     * except one ball is green, and one is red.
     * @param fw the width of the animation frame.
     * @param fh the height of the animation frame.
     * @param aname the name of the screensaver and animation frame.
     * @param n number of balls to be generated.
     */
    public BallScreenSaver(int fw, int fh, String aname, int n){
        super(fw, fh, aname);
        ballList = new ArrayList();
        framewidth = fw;
        frameheight = fh;
        appname = aname;
        num = n;
        setFPS(20);
        while (num>1) {
            int x = (int)(Math.random()*fw + 1);
            int y = (int)(Math.random()*fh +1);
            Ball newballs = new Ball(x, y, 10, Color.GREEN);
            ballList.add(newballs);
            num--;
        }
        int x = (int)(Math.random()*fw + 1);
        int y = (int)(Math.random()*fh +1);
        Ball newball = new Ball(x, y, 10, Color.RED);
        ballList.add(newball);
        collisionLogger = new CollisionLogger(framewidth, frameheight, Collision_Bucket_Width);
        start();
    }
    public void action(){
        for (Ball indBall : ballList){
            indBall.updatePosition(getFPS());
            for(Ball secIndBall : ballList){
                if (indBall != secIndBall){
                    indBall.intersect(secIndBall);
                    collisionLogger.collide(indBall, secIndBall);
                }
            }
            if (indBall.getXPos() - (indBall.getRadius()*2) <= 0 -(indBall.getRadius()) || indBall.getXPos() + (indBall.getRadius()*2) >= framewidth -(indBall.getRadius()) ) {
                indBall.setSpeedX(-indBall.getSpeedX());
            } else if (indBall.getYPos() - (indBall.getRadius()*2) <= 0 +(indBall.getRadius()*2) || indBall.getYPos() + (indBall.getRadius()*2) >= frameheight -(indBall.getRadius())) {
                indBall.setSpeedY(-indBall.getSpeedY());
            }
        }
    }
    public void draw(Graphics or){
        or.setColor(Color.black);
        or.fillRect(0,0, framewidth, frameheight);
        or.setColor(Color.white);
        or.drawRect(0, 0, framewidth, frameheight);
        for (Ball indBall: ballList){
            or.setColor(indBall.getColor());
            or.fillOval((int) indBall.getXPos(), (int) indBall.getYPos(), (int) indBall.getRadius()*2, (int) indBall.getRadius()*2);
            or.drawOval((int)indBall.getXPos(), (int)indBall.getYPos(), (int)indBall.getRadius() * 2, (int)indBall.getRadius() * 2);
        }
//        draws graphics for each frame after the action method is called to update state.
    }
    public void processKeyEvent(KeyEvent eventobj){
        int keyenter = eventobj.getKeyCode();
        if (keyenter == KeyEvent.VK_LEFT){
            for (Ball indBall : ballList) {
                indBall.setSpeedX(indBall.getSpeedX() * 0.90);
                indBall.setSpeedY(indBall.getSpeedY() * 0.90);
            }
        }
        if (keyenter == KeyEvent.VK_RIGHT) {
            for (Ball indBall : ballList) {
                indBall.setSpeedX(indBall.getSpeedX() * 1.1);
                indBall.setSpeedY(indBall.getSpeedY() * 1.1);
            }
        }
        if (eventobj.getID() == KeyEvent.KEY_PRESSED && keyenter == KeyEvent.VK_P) {
            EasyBufferedImage image = EasyBufferedImage.createImage(collisionLogger.getNormalizedHeatMap());
            try {
                image.save("C:\\Users\\Connor\\Desktop\\CSCI\\CSCI 1933 Intro to Alg and Data Structures\\Projects\\Project One\\heatmap"+saveCounter+".png", EasyBufferedImage.PNG);
                saveCounter++;
            } catch (IOException exc) {
                exc.printStackTrace();
                System.exit(1);
            }

        }
        }


}

