import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CollisionLogger {
    int sWidth, sHeight, bWidth;
    int maxval = 0;
    int[][] loggers;

    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {
    	sWidth = screenWidth;
    	sHeight = screenHeight;
    	bWidth = bucketWidth;

    	loggers = new int[sWidth/bWidth+1][sHeight/bWidth+1];
    }

    /**
     * This method records the collision event between two balls. Specifically, it increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */
    public void collide(Ball one, Ball two) {
        int xCoord = (int) (one.getXPos() + two.getXPos()) / 2;
        int yCoord = (int) (one.getYPos() + two.getYPos()) / 2;
        xCoord = xCoord / bWidth;
        yCoord = yCoord / bWidth;
        System.out.println(xCoord + " + " + yCoord);
        System.out.println(loggers[xCoord][yCoord]);
        loggers[xCoord][yCoord]++;
        int x = loggers[xCoord][yCoord];
        if (x > maxval){
            maxval = x;
        }
    }

    /**
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */
    public int[][] getNormalizedHeatMap() {

        int[][] normalizedHeatMap = new int[sWidth/bWidth][sHeight/bWidth];
        for (int i = 0; i < sWidth / bWidth; i++) {
            for (int j = 0; j < sHeight / bWidth; j++) {
                int unnorm = loggers[i][j];
                int rate = 255 / maxval;
                int normVal = unnorm * rate;
                normalizedHeatMap[i][j] = normVal;
                System.out.println(normVal);
            }
        }
        return normalizedHeatMap;
    }
}