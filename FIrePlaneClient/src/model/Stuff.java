package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controller.Simulator;
import net.ClientSocket;
import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location = new Location(0, 0);
  private Speed speed;
  private Integer ID;
  
  public Stuff(Location _location, Speed _speed, Integer inID)
  {
    this.speed = _speed;
    ID = inID;
    this.location = _location;

  }

  synchronized public void move()
  {
    getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
    getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
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
    
  }
  
  public void paint(Graphics g)
  {
    g.drawImage(getImage(), (int)getLocation().getX(), (int)getLocation().getY(), null);
  }
  
  public abstract BufferedImage getImage();
  
}
