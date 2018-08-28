Rectangle r = new Rectangle(0, 0, 100, 100);
public class RectangleFractal {
    drawing.drawShape(r);
    if (r.getIter() > 1){
      r.setWidth(r.getWidth()/2);
      r.setHeight(r.getHeight()/2);
      r.setXPos(r.getXPos()- r.getWidth());
      r.setYPos(r.getYPos()- r.getHeight());
      drawing.drawShape(r);

      //bottom left corner ^
  //    Rectangle smallerRect = new Rectangle(r.getXPos()/2, r.getYPos()/2, r.getWidth()/2, r.getHeight()/2);
  }
}
