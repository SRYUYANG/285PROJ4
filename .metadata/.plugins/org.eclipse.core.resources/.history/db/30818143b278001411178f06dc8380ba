package subjects;

import controller.Simulator;
import util.Location;
import util.Speed;

public class EnemyLargePlane extends EnemyPlane
{

  public EnemyLargePlane(Location _location, Speed _speed)
  {
    super(_location, _speed);
    HP = 4;
    // TODO Auto-generated constructor stub
  }
  
  synchronized public void shoot()
  {
    if (isExist())
    {
    SmallBullet bullet = new SmallBullet(
        new Location(this.getLocation()), 
        new Speed(0, 5), 
        this);
    
    Simulator.addWeapon(bullet);
    }
  }

  @Override
  synchronized public String getType()
  {
    return "large";
  }

}
