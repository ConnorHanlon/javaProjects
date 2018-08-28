import java.awt.*;

public class Circle{
  Color shapeColor;
  double xPos = 0;
  double yPos = 0;
  double cRad = 0;

/**Constructer of the Circle class.
@param xPosition sets the x position;
@param yPosition sets the y position.
@param circleRadius sets the radius of the circle.
@return object of the type Circle.
*/
  public Circle(double xPosition, double yPosition, double circleRadius){
    xPos = xPosition;
    yPos = yPosition;
    cRad = circleRadius;
  }

/**Method calculates the perimeter of the circle.
@return the perimeter of the circle as a double.
*/
  public double calculatePerimeter(){
    return (2 * cRad * Math.PI);
  }

/**Method calculates the area of the circle.
@return the area of the circle as a double.
*/
  public double calculateArea(){
    double cArea = cRad * cRad * Math.PI;
    return cArea;
  }

/**Sets the color of the circle
@param inputColor input of the desired color of the circle.
*/
  public void setColor(Color inputcolor){
    shapeColor = inputcolor;
  }

/**Sets the position of the circle.
@param x sets the x position.
@param y sets the y position.
*/
  public void setPos(double x, double y){
    xPos = x;
    yPos = y;
  }

/**Method sets the radius of the circle.
@param circleRadius the circle's desired radius.
*/
  public void setRadius(double circleRadius){
    cRad = circleRadius;
  }

  public Color getColor(){
    return this.shapeColor;
  }

  public double getXPos(){
    return this.xPos;
  }

  public double getYPos(){
    return this.yPos;
  }

  public double getRadius(){
    return this.cRad;
  }
}
