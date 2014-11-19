package util;

public class Location
{
  private double corX, corY;
  public Location(double inX, double inY)
  {
    corX = inX;
    corY = inY;
  }
  
  public Location(Location _loc)
  {
    corX = _loc.getX();
    corY = _loc.getY();
  }
  
  public double getX()
  {
    return corX;
  }
  
  public double getY()
  {
    return corY;
  }
  
  public void setX(double inX)
  {
    corX = inX;
  }
  
  public void setY(double inY)
  {
    corY = inY;
  }
}
