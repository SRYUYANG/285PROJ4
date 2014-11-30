package model;

import util.Location;
import util.Speed;

public class SmallBullet extends Weapon
{
  private final Integer SMALL_BULLET_POWER = -1;

  public SmallBullet(Location _location, Speed _speed, Plane inPlane)
  {
    super(_location, _speed, inPlane);
  }

  synchronized public boolean getStatus()
  {
    return true;
  }

  synchronized public Integer getPower()
  {
    return SMALL_BULLET_POWER;
  }

  synchronized public String getType()
  {
    return "small";
  }
}
