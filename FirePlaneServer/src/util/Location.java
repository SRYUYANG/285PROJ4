package util;

public class Location
{
  private Integer corX, corY;
  private final static double TOLERANCE_LIMIT = 2500;
  public Location(Integer inX, Integer inY)
  {
    corX = inX;
    corY = inY;
  }
  
  public Location(Location _loc)
  {
    corX = _loc.getX();
    corY = _loc.getY();
  }
  
  public Integer getX()
  {
    return corX;
  }
  
  public Integer getY()
  {
    return corY;
  }
  
  public void setX(Integer inX)
  {
    corX = inX;
  }
  
  public void setY(Integer inY)
  {
    corY = inY;
  }
  
  public static double getDistance(Location loc1, Location loc2)
  {
    return (loc1.getX() - loc2.getX())*(loc1.getX() - loc2.getX()) + 
        (loc1.getY() - loc2.getY())*(loc1.getY() - loc2.getY());
  }
  
  public static boolean isContact(Location loc1, Location loc2)
  {
    return getDistance(loc1, loc2) < TOLERANCE_LIMIT;
  }
  
  public static boolean isOutside(Location checkloc)
  {
    if (checkloc.getX() > Constants.MAX_WIDTH || checkloc.getX() < -80)
    {
      return true;
    }
    if (checkloc.getY() > Constants.MAX_HEIGHT || checkloc.getY() < -80)
    {
      return true;
    }
    
    return false;
  }
  
  public static Location nextLocation(Location _loc, Speed _sp)
  {
    return new Location(_loc.getX() + _sp.getXSpeed(), _loc.getY() + _sp.getYSpeed());
  }
  
  public static boolean isNextOut(Location _loc, Speed _sp)
  {
    return isOutside(nextLocation(_loc, _sp));
  }
}
