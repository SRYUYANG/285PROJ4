package subjects;

//import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.util.ArrayList;
import util.Location;
import util.Speed;

public abstract class Stuff
{
  private Location location;
  private Speed speed;
  private Integer ID;
  private static Integer nextID = 0;
  //private static ArrayList<Stuff> allStuffs = new ArrayList<Stuff>();
  
  public Stuff(double x, double y)
  {
    ID = nextID;
    ++nextID;   
    setLocation(x,y);
  }
  
  
  public void move()
  {
    getLocation().setX(getLocation().getX() + getSpeed().getXSpeed());
    getLocation().setY(getLocation().getY() + getSpeed().getYSpeed());
  }

  
  public void setLocation(double x, double y)
  {
    getLocation().setX(x);
    getLocation().setY(y);
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
  
  
  public void setSpeed(Speed speed_in)
  {
    speed = speed_in;
  }
  
 
  public abstract void paint(Graphics g);
  public abstract BufferedImage getImage();
}

