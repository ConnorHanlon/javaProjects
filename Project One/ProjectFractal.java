import java.util.Scanner;
import java.awt.*;
public class ProjectFractal{
  static double totalarea = 0;

  public static void main(String[] args){
/**Scanner collects canvas dimensions and shape color from user.
*/
    Scanner is = new Scanner(System.in);
    System.out.println("Enter canvas x dimension");
    int canvasx = is.nextInt();
    System.out.println("Enter canvas y dimension");
    int canvasy = is.nextInt();
    Color myColor = Color.RED;
    System.out.println("Enter number of fractal iterations");
    int inc = is.nextInt();
//end creation of canvas.

    System.out.println("Please input shape from choices: Rectangle, Triangle, Circle.");
    String input = is.next();
    Canvas drawing = new Canvas(canvasx, canvasy);

/**Depending on user input, the user will be required to input additional dimensions
and draw the corresponding fractal.
*/
    if (input.equals("Rectangle")){
      System.out.println("Enter xposition");
      double xposition = is.nextDouble();
      System.out.println("Enter yposition");
      double yposition = is.nextDouble();
      System.out.println("Enter width");
      double width = is.nextDouble();
      System.out.println("Enter height");
      double height =is.nextDouble();
      Rectangle myRect = new Rectangle(xposition, yposition, width, height);
      myRect.setColor(myColor);
      recRect(drawing, myRect, inc);
      drawing.drawShape(myRect);
   }
   else if (input.equals("Circle")){
     System.out.println("Enter xposition");
     double xposition = is.nextDouble();
     System.out.println("Enter yposition");
     double yposition = is.nextDouble();
     System.out.println("Enter radius");
     double radius = is.nextDouble();
     Circle myCir = new Circle(xposition, yposition, radius);
     myCir.setColor(myColor);
     reCirc(drawing, myCir, inc);
     drawing.drawShape(myCir);
   }
   else if (input.equals("Triangle")){
     System.out.println("Enter x position");
     double xposition = is.nextDouble();
     System.out.println("Enter y position");
     double yposition = is.nextDouble();
     System.out.println("Enter width");
     double width = is.nextDouble();
     System.out.println("Enter height");
     double height = is.nextDouble();
     Triangle myTri = new Triangle(xposition, yposition, width, height);
     myTri.setColor(myColor);
     reTri(drawing, myTri, inc);
     drawing.drawShape(myTri);

   }
   System.out.println("The total area is: " + totalarea);

  }//end main method.




/**Recursive method that creates a rectangle fractal pattern of smaller rectangles.
@param drawing the canvas object that the rectangles will be drawn on.
@param r the base rectangle from which the fractal patter is made.
@param inc the number of repetitions of the fractal pattern.
*/
  public static void recRect(Canvas drawing, Rectangle r, int inc){
    if (inc >= 1){
      drawing.drawShape(r);
      Rectangle rll = getLowerLeft(r);
      Rectangle lr = getLowerRight(r);
      Rectangle ul = getUpperLeft(r);
      Rectangle ur = getUpperRight(r);
      totalarea += rll.calculateArea() + lr.calculateArea() + ul.calculateArea() + ur.calculateArea();
      int x = inc -1;
      recRect(drawing, rll, x);
      recRect(drawing, lr, x);
      recRect(drawing, ul, x);
      recRect(drawing, ur, x);
    }
  }

  public static Rectangle getLowerLeft(Rectangle nrect){
    double xPos = nrect.getXPos() - (nrect.getWidth() / 2);
    double yPos = nrect.getYPos() - (nrect.getHeight() /2);
    double width = nrect.getWidth() / 2;
    double height = nrect.getHeight() /2;
    return new Rectangle(xPos, yPos, width, height);
  }

  public static Rectangle getLowerRight(Rectangle nrec){
    double xpos = nrec.getXPos() + nrec.getWidth();
    double ypos = nrec.getYPos() - (nrec.getHeight()/2);
    double width = nrec.getWidth()/2;
    double height = nrec.getHeight()/2;
    return new Rectangle(xpos, ypos, width, height);
  }

  public static Rectangle getUpperLeft(Rectangle nrec){
    double xpos = nrec.getXPos() - (nrec.getWidth()/2);
    double ypos = nrec.getYPos() + nrec.getHeight();
    double width = nrec.getWidth()/2;
    double height = nrec.getHeight()/2;
    return new Rectangle(xpos, ypos, width, height);
  }

  public static Rectangle getUpperRight(Rectangle nrec){
    double xpos = nrec.getXPos() + nrec.getWidth();
    double ypos = nrec.getYPos() + nrec.getHeight();
    double width = nrec.getWidth() / 2;
    double height = nrec.getHeight()/2;
    return new Rectangle(xpos, ypos, width, height);
  }

/**Recursive method that creates a circle fractal patter of smaller circles.
@param drawing the canvas object that the circles will be drawn on.
@param c the base circle from which the fractal pattern is made.
@param inc the number of repetitions of the fractal pattern.
*/
  public static void reCirc(Canvas drawing, Circle c, int inc){
    if (inc >=1){
      drawing.drawShape(c);
      Circle ur = getUpperRad(c);
      Circle lowerr = getLowerRad(c);
      Circle rr = getRightRad(c);
      Circle leftr = getLeftRad(c);
      totalarea = ur.calculateArea()+lowerr.calculateArea()+rr.calculateArea()+leftr.calculateArea();
      int x = inc - 1;
      reCirc(drawing, ur, x);
      reCirc(drawing, lowerr, x);
      reCirc(drawing, rr, x);
      reCirc(drawing, leftr, x);
    }
  }

  public static Circle getUpperRad(Circle ncir){
    double xpos = ncir.getXPos();
    double ypos = ncir.getYPos() - ncir.getRadius();
    double radius = ncir.getRadius()/2;
    return new Circle(xpos, ypos, radius);
  }//Origin at top left corner of canvas, therefor moving up equates to subtracting from y.

  public static Circle getLowerRad(Circle ncir){
    double xpos = ncir.getXPos();
    double ypos = ncir.getYPos() + ncir.getRadius();
    double radius = ncir.getRadius()/2;
    return new Circle(xpos, ypos, radius);
  }

  public static Circle getRightRad(Circle ncir){
    double xpos = ncir.getXPos() + ncir.getRadius();
    double ypos = ncir.getYPos();
    double radius = ncir.getRadius()/2;
    return new Circle(xpos, ypos, radius);
  }

  public static Circle getLeftRad(Circle ncir){
    double xpos = ncir.getXPos() - ncir.getRadius();
    double ypos = ncir.getYPos();
    double radius = ncir.getRadius()/2;
    return new Circle(xpos, ypos, radius);
  }

/**Recursive method that creates a triangle fractal patter of smaller triangles.
@param drawing the canvas object that the triangles will be drawn on.
@param t the base triangle from which the fractal pattern is made.
@param inc the number of repetitions of the fractal pattern.
*/
  public static void reTri(Canvas drawing, Triangle t, int inc){
    if (inc>=1){
      drawing.drawShape(t);
      Triangle lt = getLeftTri(t);
      Triangle rt = getRightTri(t);
      Triangle ut = getUpperTriangle(t);
      totalarea = lt.calculateArea()+rt.calculateArea()+ut.calculateArea();
      int x = inc - 1;
      reTri(drawing, lt, x);
      reTri(drawing, rt, x);
      reTri(drawing, ut, x);
    }
  }

  public static Triangle getLeftTri(Triangle tri){
    double xpos = tri.getXPos() - (tri.getWidth()/2);
    double ypos = tri.getYPos();
    double width = tri.getWidth()/2;
    double height = tri.getHeight()/2;
    return new Triangle(xpos, ypos, width, height);
  }

  public static Triangle getRightTri(Triangle tri){
    double xpos = tri.getXPos() + tri.getWidth();
    double ypos = tri.getYPos();
    double width = tri.getWidth()/2;
    double height = tri.getHeight()/2;
    return new Triangle(xpos, ypos, width, height);
  }

  public static Triangle getUpperTriangle(Triangle tri){
    double xpos = tri.getXPos() + (tri.getWidth()/4);
    double ypos = tri.getYPos() - tri.getHeight();
    double width = tri.getWidth()/2;
    double height = tri.getHeight()/2;
    return new Triangle(xpos, ypos, width, height);
  }

}//end ProjectFractal class definition.
