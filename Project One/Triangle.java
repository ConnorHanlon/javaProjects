import java.awt.*;

public class Triangle{
  Color shapeColor;
  double xPos = 0;
  double yPos = 0;
  double width = 0;
  double height = 0;

/**Constructer of Triangle class.
@param xPosition x position of the bottom left corner.
@param yPosition y position of the bottom left corner.
@param w with of the base of the triangle.
@param h height of the isosceles triangle.
@return object of type Triangle.
 */
  public Triangle(double xPosition, double yPosition, double w, double h){
    xPos = xPosition;
    yPos = yPosition;
    width = w;
    height = h;
  }

/**Method calculates the perimeter of the triangle.
@return the perimeter of the triangle.
*/
  public double calculatePerimeter(){
    double perim = width;
    double sides = 2 * Math.sqrt(Math.pow(height, 2) + Math.pow((width/2), 2));
    perim += sides;
    return perim;
  }

/** Calculates the area of the triangle.
@return the area of the Triangle.
*/
  public double calculateArea(){
    double area = (width * height) / 2;
    return area;
  }

/**Sets the color of the triangle.
@param inputColor input of the desired color of the triangle.
*/
  public void setColor(Color inputColor){
    shapeColor = inputColor;
  }

/**Sets the position of the triangle.
@param x sets the x position of the bottom left corner.
@param y sets the y position of the bottom left corner.
*/
  public void setPos(double x, double y){
    xPos = x;
    yPos = y;
  }

  public void setHeight(double h){
    height = h;
  }

  public void setWidth(double w){
    width = w;
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

  public double getHeight(){
    return this.height;
  }

  public double getWidth(){
    return this.width;
  }











}
