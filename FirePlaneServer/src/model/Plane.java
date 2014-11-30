package model;

import net.ServerSocket;
import util.Location;
import util.Speed;

public abstract class Plane extends Stuff
{
  protected int HP = 0;
  public Plane(Location _location, Speed _speed)
  {
    super(_location, _speed);
  }
  
  public abstract void shoot();
  synchronized public void getShot(Weapon w)
  {
    if (isExist())
    {
    HP = HP + w.getPower();
    if (HP < 0)
    {
      setExist(false);
    }
    w.setExist(false);
    }
  }
  synchronized public void getCollision()
  {
    HP = HP / 5;
  }
  synchronized public int getHP()
  {
    return HP;
  }
  synchronized public void update()
  {
    String sendingPacket = "#0@30#1@" + this.getID() + "#2@" + this.getLocation().getX() + "#3@"
        + this.getLocation().getY() + "#4@" + this.getSpeed().getXSpeed() + 
        "#5@" + this.getSpeed().getYSpeed() + "#6@";
    ServerSocket.sendPacket(sendingPacket);
  }
  
  @Override
  synchronized public void move()
  {
    /*
    if (isExist())
    {
      if (!Location.isNextOut(getLocation(), getSpeed()))
      {
      String sendingPacket = "#0@12#1@" + this.getID() + "#2@" + this.getLocation().getX() + "#3@"
        + this.getLocation().getY() + "#4@" + this.getSpeed().getXSpeed() + 
        "#5@" + this.getSpeed().getYSpeed() + "#6@";
      ServerSocket.sendPacket(sendingPacket);
      super.move();
      }
    }
    */
  }
  
}
