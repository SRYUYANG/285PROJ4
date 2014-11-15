package util;

public class Location
{
  private int corX, corY;
  public Location(int inX, int inY)
  {
    corX = inX;
    corY = inY;
  }
  
  public int getX()
  {
    return corX;
  }
  
  public int getY()
  {
    return corY;
  }
  
  public void setX(int inX)
  {
    corX = inX;
  }
  
  public void setY(int inY)
  {
    corY = inY;
  }
}
