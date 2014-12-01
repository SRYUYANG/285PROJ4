package model;

import net.ServerSocket;
import util.Location;
import util.Speed;

public abstract class Weapon extends Stuff
{
  public boolean getStatus()
  {
    return status;
  }
  public abstract Integer getPower();
  private Plane owner;
  private boolean status;
  
  public Weapon(Location _location, Speed _speed, Plane inOwner, Boolean _status)
  {
    super(_location, _speed);
    owner = inOwner;
    String sendingPacket = "#0@11#1@" + this.getType() + "#2@" + this.getID() + "#3@" + getLocation().getX()
        + "#4@" + getLocation().getY() + "#5@" + getSpeed().getXSpeed() + "#6@"
        + getSpeed().getYSpeed() + "#7@";
    ServerSocket.sendPacket(sendingPacket);
    ServerSocket.sendPacket(sendingPacket);
    status = _status;
  }
  
  public abstract String getType();
  
  synchronized public void move()
  {
    if (Location.isNextOut(getLocation(), getSpeed()))
    {
      setExist(false);
    }
    else
    {
      super.move();
    }
  }
  
}
