package util;

public class Location
{
  private double corX, corY;
  public Location(double inX, double inY)
  {
    corX = inX;
    corY = inY;
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
  
  public static Location createLocation(double _xCor, double _yCor)
  {
    return new Location(_xCor, _yCor);
  }
}
