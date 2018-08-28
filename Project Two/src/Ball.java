import java.awt.*;
import java.util.Random;

public class Ball extends Circle{
    private double xspeed = 100;
    private double yspeed = 100;



    public Ball(double x, double y, double radius, Color c){
        this.setRadius(radius);
        this.setPos(x, y);
        this.setColor(c);
        Random r = new Random();
        xspeed = (Math.random() * 50) * (r.nextBoolean() ? -1 : 1);
        yspeed = (Math.random() * 50) * (r.nextBoolean() ? -1 : 1);
    }
    public void setSpeedX(double inputxspeed){
        xspeed = inputxspeed;
    }
    public void setSpeedY(double inputyspeed){
        yspeed = inputyspeed;
    }
    public double getSpeedX(){
        return xspeed;
    }
    public double getSpeedY(){
        return yspeed;
    }
    public void updatePosition(double timepassed){
//        use fps to calculate new position based on time elapsed.
        x += xspeed / timepassed;
        y += yspeed / timepassed;
 //       this.setPos(x, y);
    }
    public boolean intersect(Ball b){
        boolean x = false;
        double d = Math.sqrt((this.getXPos() - b.getXPos()) * 2 + (this.getYPos() - b.getYPos()) * 2);
        double thisX = this.getXPos();
        double thisY = this.getYPos();
        double thisR = this.getRadius();
        double bx = b.getXPos();
        double by = b.getYPos();
        double br = b.getRadius();

        if ((thisX - thisR < bx + br && bx - br < thisX + thisR) && (thisY - thisR < by + br && by - br < thisY + thisR)) {
            x = true;
            collide(b);
        }
        return x;
    }

    /**
     *     Collide is called only when two balls intersect
     */
    public void collide(Ball b) {
        double d = this.getRadius()+b.getRadius();
        double deltaX = (this.getXPos()-b.getYPos())/d;
        double deltaY = (this.getYPos()-b.getYPos())/d;
        double thisX = this.getSpeedX();
        double thisY = this.getSpeedY();
        double bx = b.getSpeedX();
        double by = b.getSpeedY();
        if (this.getColor().equals(Color.GREEN)&& b.getColor().equals(Color.RED)){
            this.setColor(Color.RED);
        }
        double thisXVel = ((bx * deltaX + by * deltaY) * deltaX) - (((-thisX) * deltaY + thisY * deltaX) * deltaY);
        double thisYVel = ((bx * deltaX + by * deltaY) * deltaY) - (((-thisX) * deltaY + thisY * deltaX) * deltaX);
        double bXVel = ((thisX * deltaX + thisY * deltaY) * deltaX) - (((-bx) * deltaY + by * deltaX) * deltaY);
        double bYVel = ((thisX * deltaX + thisY * deltaY) * deltaY) - (((-bx) * deltaY + by * deltaX) * deltaX);
        this.setSpeedX(thisXVel);
        this.setSpeedY(thisYVel);
        b.setSpeedX(bXVel);
        b.setSpeedY(bYVel);
    }

}
