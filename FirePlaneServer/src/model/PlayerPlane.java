package model;

import controller.Simulator;
import util.Location;
import util.Speed;

public class PlayerPlane extends Plane
{
  private Player player = null;

  public PlayerPlane(Location _location, Speed _speed, Player inPl)
  {
    super(_location, _speed);
    player = inPl;
    HP = 100;
  }

  @Override
  synchronized public void shoot()
  {
    if (isExist())
    {
      SmallBullet bullet = new SmallBullet(
        new Location(this.getLocation()), 
        new Speed(0, -10), 
        this);
    
      Simulator.addWeapon(bullet);
    }
  }

  synchronized public Player getPlayer()
  {
    return player;
  }
}
