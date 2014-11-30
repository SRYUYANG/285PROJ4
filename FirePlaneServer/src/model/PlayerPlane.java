package model;

import net.ServerSocket;
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
        this,
        true);
      Simulator.addWeapon(bullet);
    }
  }
  
  synchronized public void getShot(Weapon w)
  {
     if (isExist() && !w.getStatus())
     {
       HP = HP + w.getPower();
     if (HP < 0)
     {
        setExist(false);
     }
        w.setExist(false);
     }
  }
  
  synchronized public void move()
  {
    String sendingPacket = "#0@12#1@" + this.getID() + "#2@" + this.getLocation().getX() + "#3@"
        + this.getLocation().getY() + "#4@" + this.getSpeed().getXSpeed() + 
        "#5@" + this.getSpeed().getYSpeed() + "#6@";
      ServerSocket.sendPacket(sendingPacket);
      super.move();
  }

  synchronized public Player getPlayer()
  {
    return player;
  }
}
