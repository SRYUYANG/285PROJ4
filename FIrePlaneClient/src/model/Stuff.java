package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import resource.StaticImageResource;
import controller.Simulator;
import net.ClientSocket;
import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location = new Location(0, 0);
  private Speed speed;
  private Integer ID;
  protected Integer deathCounter = 0;
  private boolean isExist = true;
  private boolean isValid = true;

  public Stuff(Location _location, Speed _speed, Integer inID)
  {
    this.speed = _speed;
    ID = inID;
    this.location = _location;

  }

  public boolean isExist()
  {
    return isExist;
  }

  public void setExist(boolean _isExist)
  {
    isExist = _isExist;
  }

  public boolean isValid()
  {
    return isValid;
  }

  public void setValid(boolean _isValid)
  {
    isValid = _isValid;
  }

  synchronized public void move()
  {
    if( isExist() )
    {
      getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
      getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
    }
    else
    {
      if( deathCounter < 14 )
      {
        deathCounter++;
      }
      else
      {
        isValid = false;
      }
    }
  }

  synchronized public void setLocation(Location _loc)
  {
    location = new Location(_loc);
  }

  public Location getLocation()
  {
    return location;
  }

  public Integer getID()
  {
    return ID;
  }

  public Speed getSpeed()
  {
    return speed;
  }

  synchronized public void setSpeed(Speed _speed)
  {
    speed = new Speed(_speed);
  }

  public void destroy()
  {
    setExist(false);
  }

  public void paint(Graphics g)
  {
    if( isExist() )
    {
      g.drawImage(getImage(), (int) getLocation().getX(), (int) getLocation()
          .getY(), null);
    }
    else
    {
      if( isValid )
      {
        g.drawImage(StaticImageResource.explosionImages[deathCounter],
            getLocation().getX(), getLocation().getY(), null);
      }
    }
  }

  public abstract BufferedImage getImage();

}
