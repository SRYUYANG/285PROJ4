package model;

import util.Location;
import util.Speed;

public class SmallBullet extends Weapon
{
  private final Integer SMALL_BULLET_POWER = -1;

  public SmallBullet(Location _location, Speed _speed, Plane inPlane, boolean _status)
  {
    super(_location, _speed, inPlane, _status);
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
