package util;

public class Location
{
  private double corX, corY;
  private final static double TOLERANCE_LIMIT = 5.0;
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
  
  public static double getDistance(Location loc1, Location loc2)
  {
    return (loc1.getX() - loc2.getX())*(loc1.getX() - loc2.getX()) - 
        (loc1.getY() - loc2.getY())*(loc1.getY() - loc2.getY());
  }
  
  public static boolean isContact(Location loc1, Location loc2)
  {
    return getDistance(loc1, loc2) < TOLERANCE_LIMIT;
  }
}
