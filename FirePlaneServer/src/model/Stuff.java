package model;

import controller.Simulator;
import net.ServerSocket;
import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location = new Location(0, 0);
  private Speed speed;
  private Integer ID;
  private static Integer nextID = 0;
  private boolean isExist = true;
  private boolean isValid = true;

  public Stuff(Location _location, Speed _speed)
  {
    this.speed = _speed;
    ID = nextID;
    ++nextID;
    this.location = _location;

  }

  synchronized public void move()
  {
    if( isExist() )
    {
      getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
      getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
    }
  }

  synchronized public void setLocation(Location _loc)
  {
    location = new Location(_loc);
  }

  synchronized public Location getLocation()
  {
    return location;
  }

  synchronized public Integer getID()
  {
    return ID;
  }

  synchronized public Speed getSpeed()
  {
    return speed;
  }

  synchronized public void setSpeed(Speed _speed)
  {
    speed = new Speed(_speed);
  }

  synchronized public boolean getStatus()
  {
    return isExist();
  }

  synchronized public void destroy()
  {
    if( !isExist() )
    {
      String sendingPacket = "#0@22#1@" + this.getID() + "#2@";
      ServerSocket.sendPacket(sendingPacket);
      ServerSocket.sendPacket(sendingPacket);
      setInvalid();
    }
  }

  synchronized public boolean isExist()
  {
    return isExist;
  }

  synchronized public void setExist(boolean _isExist)
  {
    this.isExist = _isExist;
  }

  synchronized public boolean isValid()
  {
    return isValid;
  }

  synchronized public void setInvalid()
  {
    isValid = false;
  }


}
