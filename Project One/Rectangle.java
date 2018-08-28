import java.awt.*;

public class Rectangle{
  Color shapeColor;
  double xPos = 0;
  double yPos = 0;
  double width = 0;
  double height = 0;

/**Constructer of the Rectangle class.
@param x x position of the bottom left corner.
@param y y position of the bottom left corner.
@param w width of the rectangle.
@param h height of the rectangle.
@return object of the type Rectangle.
*/
  public Rectangle(double x, double y, double w, double h){
    xPos = x;
    yPos = y;
    width = w;
    height = h;
  }

/**Method calculates the perimeter of the Rectangle.
@return the value of the perimeter asa double.
*/
  public double calculatePerimeter(){
    double perim = (2 * width) + (2 * height);
    return perim;
  }

  /** Method calculates the area of the Rectangle.
  @return the area of the Rectangle as a double.
  */
  public double calculateArea(){
    double area = width * height;
    return area;
  }

/**Sets the color of the rectangle.
@param inputColor input of the desired color of the rectangle.
*/
  public void setColor(Color inputColor){
    shapeColor = inputColor;
  }

  /**Sets the position of the rectangle.
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
